package com.roottrace.backend.service;

import com.roottrace.backend.config.KafkaConfig;
import com.roottrace.backend.entity.LogEntry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducerService {

    private final KafkaTemplate<String, LogEntry> kafkaTemplate;

    public void sendLog(LogEntry logEntry) {
        log.info("Sending log to Kafka: {}", logEntry.getMessage());
        kafkaTemplate.send(KafkaConfig.LOGS_TOPIC, logEntry.getServiceName(), logEntry);
    }
}
