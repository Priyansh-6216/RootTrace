package com.roottrace.backend.rca.service;

import com.roottrace.backend.incident.model.Incident;
import com.roottrace.backend.rca.model.Evidence;
import com.roottrace.backend.rca.model.EvidenceType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CorrelationEngine {

    /**
     * Gathers telemetry around the time of the incident to act as evidence.
     * In a real implementation, this would query Elasticsearch, Neo4j, and Prometheus.
     */
    public List<Evidence> gatherEvidence(Incident incident) {
        List<Evidence> evidenceList = new ArrayList<>();
        
        // Mocking evidence gathering for demonstration
        evidenceList.add(Evidence.builder()
                .id(UUID.randomUUID().toString())
                .type(EvidenceType.METRIC)
                .description("P95 latency spike of +412% detected on " + incident.getServiceName())
                .sourceId("metric-9123")
                .strength(0.95)
                .build());

        evidenceList.add(Evidence.builder()
                .id(UUID.randomUUID().toString())
                .type(EvidenceType.DEPLOYMENT)
                .description("Deployment v2.4.7 occurred 4 minutes prior to anomaly")
                .sourceId("deploy-111")
                .strength(0.85)
                .build());

        return evidenceList;
    }
}
