package com.roottrace.backend.incident.service;

import com.roottrace.backend.incident.model.Incident;
import com.roottrace.backend.incident.model.IncidentSeverity;
import com.roottrace.backend.incident.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class IncidentDetector {

    private final IncidentRepository incidentRepository;
    private final BlastRadiusService blastRadiusService;

    public Incident detectAndCreateIncident(String serviceName, String title, IncidentSeverity severity) {
        log.info("Creating incident for service: {} with severity: {}", serviceName, severity);

        // In a real implementation, this JSON timeline would be structured.
        String timelineJson = String.format("{\"events\": [{\"time\": \"%s\", \"action\": \"Incident Detected\"}]}", LocalDateTime.now());

        Incident incident = Incident.builder()
                .incidentId(UUID.randomUUID().toString())
                .serviceName(serviceName)
                .title(title)
                .severity(severity)
                .status("OPEN")
                .createdAt(LocalDateTime.now())
                .detectedAt(LocalDateTime.now())
                .timeline(timelineJson)
                .build();

        Incident saved = incidentRepository.save(incident);
        
        // Calculate blast radius proactively
        blastRadiusService.calculateBlastRadius(serviceName);

        return saved;
    }
}
