package com.roottrace.backend.controller;

import com.roottrace.backend.entity.TraceNode;
import com.roottrace.backend.service.TraceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/traces")
@RequiredArgsConstructor
public class TraceController {

    private final TraceService traceService;

    @PostMapping
    public ResponseEntity<TraceNode> createTrace(@RequestBody TraceNode traceNode) {
        return ResponseEntity.ok(traceService.saveTrace(traceNode));
    }

    @PostMapping("/batch")
    public ResponseEntity<List<TraceNode>> createTraces(@RequestBody List<TraceNode> traceNodes) {
        return ResponseEntity.ok(traceService.saveTraces(traceNodes));
    }

    @GetMapping
    public ResponseEntity<List<TraceNode>> getAllTraces() {
        return ResponseEntity.ok(traceService.getAllTraces());
    }

    @GetMapping("/{traceId}")
    public ResponseEntity<TraceNode> getTraceById(@PathVariable String traceId) {
        return traceService.getTraceById(traceId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
