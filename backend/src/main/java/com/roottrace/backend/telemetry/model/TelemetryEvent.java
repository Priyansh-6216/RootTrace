package com.roottrace.backend.telemetry.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TelemetryEvent {
    private String eventId;
    private String tenantId;
    private String service;
    private String environment;
    private Instant timestamp;
    private String traceId;
    private String spanId;
    private EventType eventType;
    private Severity severity;
    private String message;
    private Map<String, Object> attributes;
}
