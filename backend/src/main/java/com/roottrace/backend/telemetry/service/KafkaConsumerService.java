package com.roottrace.backend.telemetry.service;

import com.roottrace.backend.common.config.KafkaConfig;
import com.roottrace.backend.telemetry.entity.LogEntry;
import com.roottrace.backend.telemetry.model.TelemetryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final LogService logService;
    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = KafkaConfig.TOPIC_TELEMETRY_LOGS, groupId = "roottrace-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumeLog(TelemetryEvent event) {
        log.info("Consumed telemetry event from Kafka: {}", event.getEventId());
        
        LogEntry logEntry = LogEntry.builder()
                .eventId(event.getEventId())
                .tenantId(event.getTenantId())
                .serviceName(event.getService())
                .environment(event.getEnvironment())
                .timestamp(event.getTimestamp() != null ? java.time.LocalDateTime.ofInstant(event.getTimestamp(), java.time.ZoneId.systemDefault()) : java.time.LocalDateTime.now())
                .traceId(event.getTraceId())
                .spanId(event.getSpanId())
                .logLevel(event.getSeverity() != null ? event.getSeverity().name() : null)
                .message(event.getMessage())
                .build();
        
        try {
            List<LogEntry> saved = logService.saveLogs(List.of(logEntry));
            if (!saved.isEmpty()) {
                messagingTemplate.convertAndSend("/topic/logs", saved.get(0));
            }
        } catch (Exception e) {
            log.error("Failed to process eventId: " + event.getEventId() + ". Sending to DLQ.", e);
            throw e; // Throwing will trigger Kafka retry/DLQ mechanism if configured in KafkaConfig
        }
    }
}
