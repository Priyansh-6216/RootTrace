package com.roottrace.backend.telemetry;

import com.roottrace.backend.AbstractIntegrationTest;
import com.roottrace.backend.common.config.KafkaConfig;
import com.roottrace.backend.telemetry.entity.LogEntry;
import com.roottrace.backend.telemetry.model.Severity;
import com.roottrace.backend.telemetry.model.TelemetryEvent;
import com.roottrace.backend.telemetry.repository.LogRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class KafkaPipelineIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    private LogRepository logRepository;

    @Test
    void shouldIngestAndRedactTelemetryEvent() {
        String eventId = UUID.randomUUID().toString();
        
        TelemetryEvent event = TelemetryEvent.builder()
                .eventId(eventId)
                .tenantId("tenant-1")
                .service("checkout-service")
                .environment("production")
                .severity(Severity.ERROR)
                .message("Failed payment with API key: api_key: \"secret123\"")
                .timestamp(Instant.now())
                .build();

        // Send to Kafka
        kafkaTemplate.send(KafkaConfig.TOPIC_TELEMETRY_LOGS, event);

        // Await processing
        await().atMost(10, TimeUnit.SECONDS).untilAsserted(() -> {
            boolean exists = logRepository.existsByEventId(eventId);
            assertThat(exists).isTrue();
        });

        // Verify redaction and persistence
        LogEntry savedLog = logRepository.findAll().stream()
                .filter(l -> eventId.equals(l.getEventId()))
                .findFirst().orElseThrow();

        assertThat(savedLog.getServiceName()).isEqualTo("checkout-service");
        assertThat(savedLog.getMessage()).doesNotContain("secret123");
        assertThat(savedLog.getMessage()).contains("[REDACTED]");
    }
}
