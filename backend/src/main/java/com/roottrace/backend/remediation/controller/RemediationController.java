package com.roottrace.backend.remediation.controller;

import com.roottrace.backend.remediation.model.ApprovalDecision;
import com.roottrace.backend.remediation.model.RemediationPlan;
import com.roottrace.backend.remediation.repository.RemediationRepository;
import com.roottrace.backend.remediation.service.RemediationOrchestrator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/remediations")
@RequiredArgsConstructor
public class RemediationController {

    private final RemediationRepository remediationRepository;
    private final RemediationOrchestrator remediationOrchestrator;

    @GetMapping
    public ResponseEntity<List<RemediationPlan>> getAllPlans() {
        return ResponseEntity.ok(remediationRepository.findAll());
    }

    @PostMapping("/{incidentId}/decision")
    public ResponseEntity<RemediationPlan> submitDecision(
            @PathVariable String incidentId,
            @RequestBody ApprovalDecision decision) {
        
        try {
            RemediationPlan updatedPlan = remediationOrchestrator.processApprovalDecision(incidentId, decision);
            return ResponseEntity.ok(updatedPlan);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
