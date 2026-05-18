package com.roottrace.backend.service;

import com.roottrace.backend.config.KafkaConfig;
import com.roottrace.backend.entity.LogEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final SimpMessagingTemplate messagingTemplate;

    @KafkaListener(topics = KafkaConfig.LOGS_TOPIC, groupId = "roottrace-group")
    public void consumeLog(LogEntry logEntry) {
        log.info("Consumed log from Kafka: {}", logEntry.getMessage());
        // Broadcast to WebSocket topic
        messagingTemplate.convertAndSend("/topic/logs", logEntry);
    }
}
