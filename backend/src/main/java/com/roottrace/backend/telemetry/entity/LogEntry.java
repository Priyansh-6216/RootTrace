package com.roottrace.backend.telemetry.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "log_entries")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LogEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String eventId;

    private String tenantId;

    private LocalDateTime timestamp;

    private String serviceName;

    private String logLevel;

    private String traceId;

    private String spanId;

    @Column(columnDefinition = "TEXT")
    private String message;

    private String exceptionType;

    @Column(columnDefinition = "TEXT")
    private String stackTrace;

    private String environment;
}
