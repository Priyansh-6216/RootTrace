package com.roottrace.backend.remediation.service;

import com.roottrace.backend.remediation.model.ApprovalDecision;
import com.roottrace.backend.remediation.model.RemediationPlan;
import com.roottrace.backend.remediation.model.RemediationStatus;
import com.roottrace.backend.remediation.repository.RemediationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RemediationOrchestrator {

    private final RemediationRepository remediationRepository;
    private final GitHubIntegrationService gitHubIntegrationService;

    public RemediationPlan processApprovalDecision(String incidentId, ApprovalDecision decision) {
        log.info("Processing webhook decision for incident {}: approved={}", incidentId, decision.isApproved());
        
        Optional<RemediationPlan> optionalPlan = remediationRepository.findByIncidentId(incidentId);
        
        if (optionalPlan.isEmpty()) {
            throw new IllegalArgumentException("No remediation plan found for incident: " + incidentId);
        }

        RemediationPlan plan = optionalPlan.get();
        
        if (plan.getStatus() != RemediationStatus.PENDING) {
            throw new IllegalStateException("Remediation plan is not in a PENDING state. Current state: " + plan.getStatus());
        }

        if (decision.isApproved()) {
            plan.setStatus(RemediationStatus.APPROVED);
            
            try {
                // Generate the PR via GitHub integration
                String prUrl = gitHubIntegrationService.createPullRequestForFix(plan);
                plan.setGithubPrUrl(prUrl);
                plan.setStatus(RemediationStatus.APPLIED);
                log.info("Successfully applied fix and created PR: {}", prUrl);
            } catch (Exception e) {
                log.error("Failed to apply fix to GitHub for incident {}", incidentId, e);
                plan.setStatus(RemediationStatus.FAILED);
                plan.setRejectionReason("GitHub Integration Failed: " + e.getMessage());
            }
        } else {
            plan.setStatus(RemediationStatus.REJECTED);
            plan.setRejectionReason(decision.getReason());
            log.info("Remediation rejected by human operator. Reason: {}", decision.getReason());
        }

        return remediationRepository.save(plan);
    }
}
