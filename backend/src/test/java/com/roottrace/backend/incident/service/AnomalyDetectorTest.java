package com.roottrace.backend.incident.service;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

class AnomalyDetectorTest {

    private final AnomalyDetector anomalyDetector = new AnomalyDetector();

    @Test
    void shouldDetectAnomalyIfZScoreExceedsThreshold() {
        List<Double> history = List.of(100.0, 105.0, 95.0, 102.0, 98.0);
        
        // Mean is 100, SD is approx 3.16
        // A value of 200 is heavily anomalous (z-score approx 31)
        boolean isAnomalous = anomalyDetector.isAnomalous(200.0, history, 3.0);
        
        assertThat(isAnomalous).isTrue();
    }

    @Test
    void shouldNotDetectAnomalyForNormalValues() {
        List<Double> history = List.of(100.0, 105.0, 95.0, 102.0, 98.0);
        
        // Mean is 100, SD is approx 3.16
        // A value of 104 is well within normal range
        boolean isAnomalous = anomalyDetector.isAnomalous(104.0, history, 3.0);
        
        assertThat(isAnomalous).isFalse();
    }

    @Test
    void shouldHandleEmptyHistory() {
        boolean isAnomalous = anomalyDetector.isAnomalous(100.0, List.of(), 3.0);
        assertThat(isAnomalous).isFalse();
    }
}
