package com.roottrace.backend.controller;

import com.roottrace.backend.entity.LogEntry;
import com.roottrace.backend.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogController {

    private final LogRepository logRepository;

    @PostMapping("/upload")
    public ResponseEntity<List<LogEntry>> uploadLogs(@RequestBody List<LogEntry> logs) {
        List<LogEntry> savedLogs = logRepository.saveAll(logs);
        return ResponseEntity.ok(savedLogs);
    }

    @GetMapping
    public ResponseEntity<List<LogEntry>> getAllLogs() {
        return ResponseEntity.ok(logRepository.findAll());
    }

    @GetMapping("/service/{name}")
    public ResponseEntity<List<LogEntry>> getLogsByService(@PathVariable String name) {
        return ResponseEntity.ok(logRepository.findByServiceName(name));
    }
}
