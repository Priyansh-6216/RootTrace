package com.roottrace.backend.rca.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RootCauseScore {
    private double temporal;
    private double trace;
    private double metric;
    private double log;
    private double deployment;
    private double dependency;
    private double historical;

    public double getFinalScore() {
        // Weighted average for demonstration
        return (temporal * 0.15) + (trace * 0.20) + (metric * 0.20) + 
               (log * 0.15) + (deployment * 0.10) + (dependency * 0.10) + (historical * 0.10);
    }
}
