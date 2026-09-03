package com.roottrace.backend.incident.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;

@Entity
@Table(name = "incidents")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Incident {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String incidentId;
    
    private String title;

    @Enumerated(EnumType.STRING)
    private IncidentSeverity severity;
    
    private String serviceName;
    
    private String status;

    private LocalDateTime createdAt;
    private LocalDateTime detectedAt;
    private LocalDateTime resolvedAt;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Object timeline;
}
