package com.roottrace.backend.controller;

import com.roottrace.backend.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import com.roottrace.backend.entity.LogEntry;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogController {

    private final LogService logService;

    @PostMapping("/upload")
    public ResponseEntity<List<LogEntry>> uploadLogs(@RequestBody List<LogEntry> logs) {
        return ResponseEntity.ok(logService.saveLogs(logs));
    }

    @GetMapping("/search")
    public ResponseEntity<List<LogEntry>> searchLogs(
            @RequestParam(required = false) String serviceName,
            @RequestParam(required = false) String logLevel,
            @RequestParam(required = false) String message,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return ResponseEntity.ok(logService.searchLogs(serviceName, logLevel, message, startTime, endTime));
    }

    @GetMapping
    public ResponseEntity<List<LogEntry>> getAllLogs() {
        return ResponseEntity.ok(logService.searchLogs(null, null, null, null, null));
    }
}
