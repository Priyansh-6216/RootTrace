package com.roottrace.backend.rca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RootCauseCandidate {
    private String id;
    private String hypothesis;
    private RootCauseScore scoreBreakdown;
    private ConfidenceLevel confidenceLevel;

    @Builder.Default
    private List<Evidence> evidence = new ArrayList<>();
}
