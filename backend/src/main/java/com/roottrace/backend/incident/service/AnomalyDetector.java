package com.roottrace.backend.incident.service;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnomalyDetector {

    /**
     * Calculates the z-score for a new value against a list of historical values.
     * Z-score = (x - mean) / standard_deviation
     */
    public double calculateZScore(double value, List<Double> history) {
        if (history == null || history.isEmpty()) {
            return 0.0;
        }

        double sum = 0;
        for (double v : history) {
            sum += v;
        }
        double mean = sum / history.size();

        double varianceSum = 0;
        for (double v : history) {
            varianceSum += Math.pow(v - mean, 2);
        }
        double variance = varianceSum / history.size();
        double standardDeviation = Math.sqrt(variance);

        if (standardDeviation == 0) {
            return 0.0;
        }

        return (value - mean) / standardDeviation;
    }

    /**
     * Checks if a value is anomalous based on a Z-score threshold.
     */
    public boolean isAnomalous(double value, List<Double> history, double zScoreThreshold) {
        double zScore = calculateZScore(value, history);
        return Math.abs(zScore) > zScoreThreshold;
    }
}
