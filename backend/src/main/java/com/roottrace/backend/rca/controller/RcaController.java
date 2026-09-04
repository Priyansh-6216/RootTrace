package com.roottrace.backend.rca.controller;

import com.roottrace.backend.incident.model.Incident;
import com.roottrace.backend.incident.repository.IncidentRepository;
import com.roottrace.backend.rca.model.Evidence;
import com.roottrace.backend.rca.model.RootCauseCandidate;
import com.roottrace.backend.rca.service.CandidateGenerator;
import com.roottrace.backend.rca.service.CorrelationEngine;
import com.roottrace.backend.rca.service.RcaScoringEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incidents")
@RequiredArgsConstructor
public class RcaController {

    private final IncidentRepository incidentRepository;
    private final CorrelationEngine correlationEngine;
    private final CandidateGenerator candidateGenerator;
    private final RcaScoringEngine rcaScoringEngine;

    @GetMapping("/{id}/evidence")
    public ResponseEntity<List<Evidence>> getIncidentEvidence(@PathVariable String id) {
        return incidentRepository.findByIncidentId(id)
                .map(correlationEngine::gatherEvidence)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/rca")
    public ResponseEntity<List<RootCauseCandidate>> getIncidentRca(@PathVariable String id) {
        return incidentRepository.findByIncidentId(id)
                .map(incident -> {
                    List<Evidence> evidence = correlationEngine.gatherEvidence(incident);
                    List<RootCauseCandidate> candidates = candidateGenerator.generateCandidates(evidence);
                    return rcaScoringEngine.scoreCandidates(candidates);
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
