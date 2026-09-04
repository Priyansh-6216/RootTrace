package com.roottrace.backend.rca.service;

import com.roottrace.backend.rca.model.Evidence;
import com.roottrace.backend.rca.model.RootCauseCandidate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CandidateGenerator {

    /**
     * Given a list of evidence, generates plausible hypotheses for the root cause.
     */
    public List<RootCauseCandidate> generateCandidates(List<Evidence> evidenceList) {
        List<RootCauseCandidate> candidates = new ArrayList<>();
        
        // Mock generation logic based on evidence
        boolean hasDeployment = evidenceList.stream().anyMatch(e -> e.getType().name().equals("DEPLOYMENT"));
        
        if (hasDeployment) {
            candidates.add(RootCauseCandidate.builder()
                    .id(UUID.randomUUID().toString())
                    .hypothesis("Recent deployment introduced a regression causing latency.")
                    .evidence(evidenceList)
                    .build());
        } else {
            candidates.add(RootCauseCandidate.builder()
                    .id(UUID.randomUUID().toString())
                    .hypothesis("Database connection exhaustion or network timeout.")
                    .evidence(evidenceList)
                    .build());
        }
        
        return candidates;
    }
}
