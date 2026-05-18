package com.roottrace.backend.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public static final String LOGS_TOPIC = "roottrace-logs";

    @Bean
    public NewTopic logsTopic() {
        return TopicBuilder.name(LOGS_TOPIC)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
