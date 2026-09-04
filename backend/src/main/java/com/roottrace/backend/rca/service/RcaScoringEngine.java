package com.roottrace.backend.rca.service;

import com.roottrace.backend.rca.model.ConfidenceLevel;
import com.roottrace.backend.rca.model.RootCauseCandidate;
import com.roottrace.backend.rca.model.RootCauseScore;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RcaScoringEngine {

    /**
     * Scores a list of candidates and returns the top 3 sorted by confidence.
     */
    public List<RootCauseCandidate> scoreCandidates(List<RootCauseCandidate> candidates) {
        return candidates.stream()
                .peek(this::calculateScore)
                .sorted(Comparator.comparing(c -> c.getScoreBreakdown().getFinalScore(), Comparator.reverseOrder()))
                .limit(3)
                .collect(Collectors.toList());
    }

    private void calculateScore(RootCauseCandidate candidate) {
        // Mocking a score calculation based on the evidence attached to the candidate
        RootCauseScore score = RootCauseScore.builder()
                .temporal(0.91)
                .trace(0.88)
                .metric(0.94)
                .log(0.76)
                .deployment(0.85) // High because of the mocked deployment evidence
                .dependency(0.40)
                .historical(0.81)
                .build();
                
        candidate.setScoreBreakdown(score);
        candidate.setConfidenceLevel(mapToConfidence(score.getFinalScore()));
    }

    public ConfidenceLevel mapToConfidence(double score) {
        if (score < 0.50) return ConfidenceLevel.LOW;
        if (score < 0.75) return ConfidenceLevel.MEDIUM;
        if (score < 0.90) return ConfidenceLevel.HIGH;
        return ConfidenceLevel.VERY_HIGH;
    }
}
