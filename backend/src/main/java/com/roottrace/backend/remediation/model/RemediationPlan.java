package com.roottrace.backend.remediation.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Entity
@Table(name = "remediation_plans")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RemediationPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String incidentId;
    
    @Column(columnDefinition = "TEXT")
    private String humanNarrative;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> steps;

    @Enumerated(EnumType.STRING)
    private RemediationStatus status;
    
    private String githubPrUrl;
    private String rejectionReason;
}
