package com.roottrace.backend.service;

import com.roottrace.backend.entity.TraceNode;
import com.roottrace.backend.repository.TraceNodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TraceService {

    private final TraceNodeRepository traceNodeRepository;

    public TraceNode saveTrace(TraceNode traceNode) {
        return traceNodeRepository.save(traceNode);
    }

    public List<TraceNode> saveTraces(List<TraceNode> traceNodes) {
        return traceNodeRepository.saveAll(traceNodes);
    }

    public List<TraceNode> getAllTraces() {
        return traceNodeRepository.findAll();
    }

    public Optional<TraceNode> getTraceById(String traceId) {
        return traceNodeRepository.findById(traceId);
    }
}
