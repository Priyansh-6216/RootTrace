package com.roottrace.backend.rca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Evidence {
    private String id;
    private EvidenceType type;
    private String description;
    private String sourceId;
    private double strength;
}
