package com.roottrace.backend.rca.service;

import com.roottrace.backend.rca.model.ConfidenceLevel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RcaScoringEngineTest {

    private final RcaScoringEngine rcaScoringEngine = new RcaScoringEngine();

    @Test
    void shouldMapScoreToConfidenceLevelCorrectly() {
        assertThat(rcaScoringEngine.mapToConfidence(0.20)).isEqualTo(ConfidenceLevel.LOW);
        assertThat(rcaScoringEngine.mapToConfidence(0.49)).isEqualTo(ConfidenceLevel.LOW);
        assertThat(rcaScoringEngine.mapToConfidence(0.65)).isEqualTo(ConfidenceLevel.MEDIUM);
        assertThat(rcaScoringEngine.mapToConfidence(0.85)).isEqualTo(ConfidenceLevel.HIGH);
        assertThat(rcaScoringEngine.mapToConfidence(0.95)).isEqualTo(ConfidenceLevel.VERY_HIGH);
    }
}
