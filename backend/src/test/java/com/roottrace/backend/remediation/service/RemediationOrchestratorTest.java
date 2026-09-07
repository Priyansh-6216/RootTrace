package com.roottrace.backend.remediation.service;

import com.roottrace.backend.remediation.model.ApprovalDecision;
import com.roottrace.backend.remediation.model.RemediationPlan;
import com.roottrace.backend.remediation.model.RemediationStatus;
import com.roottrace.backend.remediation.repository.RemediationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RemediationOrchestratorTest {

    @Mock
    private RemediationRepository remediationRepository;

    @Mock
    private GitHubIntegrationService gitHubIntegrationService;

    private RemediationOrchestrator orchestrator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orchestrator = new RemediationOrchestrator(remediationRepository, gitHubIntegrationService);
    }

    @Test
    void shouldCreatePrWhenApproved() {
        String incidentId = "inc-123";
        RemediationPlan plan = RemediationPlan.builder()
                .incidentId(incidentId)
                .status(RemediationStatus.PENDING)
                .build();

        when(remediationRepository.findByIncidentId(incidentId)).thenReturn(Optional.of(plan));
        when(gitHubIntegrationService.createPullRequestForFix(plan)).thenReturn("https://github.com/mock/pull/1");
        when(remediationRepository.save(any(RemediationPlan.class))).thenAnswer(i -> i.getArgument(0));

        ApprovalDecision decision = new ApprovalDecision(true, "Looks good");
        
        RemediationPlan result = orchestrator.processApprovalDecision(incidentId, decision);

        assertThat(result.getStatus()).isEqualTo(RemediationStatus.APPLIED);
        assertThat(result.getGithubPrUrl()).isEqualTo("https://github.com/mock/pull/1");
        verify(gitHubIntegrationService, times(1)).createPullRequestForFix(plan);
    }

    @Test
    void shouldNotCallGitHubWhenRejected() {
        String incidentId = "inc-456";
        RemediationPlan plan = RemediationPlan.builder()
                .incidentId(incidentId)
                .status(RemediationStatus.PENDING)
                .build();

        when(remediationRepository.findByIncidentId(incidentId)).thenReturn(Optional.of(plan));
        when(remediationRepository.save(any(RemediationPlan.class))).thenAnswer(i -> i.getArgument(0));

        ApprovalDecision decision = new ApprovalDecision(false, "Risky change");
        
        RemediationPlan result = orchestrator.processApprovalDecision(incidentId, decision);

        assertThat(result.getStatus()).isEqualTo(RemediationStatus.REJECTED);
        assertThat(result.getRejectionReason()).isEqualTo("Risky change");
        verify(gitHubIntegrationService, never()).createPullRequestForFix(any());
    }
}
