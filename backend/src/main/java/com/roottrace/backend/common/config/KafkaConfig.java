package com.roottrace.backend.common.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    public static final String TOPIC_TELEMETRY_LOGS = "roottrace.telemetry.logs";
    public static final String TOPIC_TELEMETRY_METRICS = "roottrace.telemetry.metrics";
    public static final String TOPIC_TELEMETRY_TRACES = "roottrace.telemetry.traces";
    public static final String TOPIC_DEPLOYMENTS = "roottrace.deployments";
    public static final String TOPIC_INCIDENTS = "roottrace.incidents";
    public static final String TOPIC_RCA = "roottrace.rca";
    public static final String TOPIC_DLQ = "roottrace.dlq";

    @Bean
    public NewTopic logsTopic() {
        return TopicBuilder.name(TOPIC_TELEMETRY_LOGS).partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic metricsTopic() {
        return TopicBuilder.name(TOPIC_TELEMETRY_METRICS).partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic tracesTopic() {
        return TopicBuilder.name(TOPIC_TELEMETRY_TRACES).partitions(3).replicas(1).build();
    }

    @Bean
    public NewTopic deploymentsTopic() {
        return TopicBuilder.name(TOPIC_DEPLOYMENTS).partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic incidentsTopic() {
        return TopicBuilder.name(TOPIC_INCIDENTS).partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic rcaTopic() {
        return TopicBuilder.name(TOPIC_RCA).partitions(1).replicas(1).build();
    }

    @Bean
    public NewTopic dlqTopic() {
        return TopicBuilder.name(TOPIC_DLQ).partitions(1).replicas(1).build();
    }
}
