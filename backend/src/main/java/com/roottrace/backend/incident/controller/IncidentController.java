package com.roottrace.backend.incident.controller;

import com.roottrace.backend.incident.model.Incident;
import com.roottrace.backend.incident.repository.IncidentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/incidents")
@RequiredArgsConstructor
public class IncidentController {

    private final IncidentRepository incidentRepository;

    @GetMapping
    public ResponseEntity<List<Incident>> getAllIncidents() {
        return ResponseEntity.ok(incidentRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incident> getIncidentById(@PathVariable String id) {
        return incidentRepository.findByIncidentId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/timeline")
    public ResponseEntity<Object> getIncidentTimeline(@PathVariable String id) {
        return incidentRepository.findByIncidentId(id)
                .map(incident -> ResponseEntity.ok(incident.getTimeline()))
                .orElse(ResponseEntity.notFound().build());
    }
}
