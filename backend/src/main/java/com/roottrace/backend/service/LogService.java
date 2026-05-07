package com.roottrace.backend.service;

import com.roottrace.backend.entity.LogEntry;
import com.roottrace.backend.repository.LogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LogService {

    private final LogRepository logRepository;

    public List<LogEntry> saveLogs(List<LogEntry> logs) {
        return logRepository.saveAll(logs);
    }

    public List<LogEntry> searchLogs(String serviceName, String logLevel, String message, 
                                     LocalDateTime startTime, LocalDateTime endTime) {
        Specification<LogEntry> spec = Specification.where(null);

        if (StringUtils.hasText(serviceName)) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("serviceName"), serviceName));
        }

        if (StringUtils.hasText(logLevel)) {
            spec = spec.and((root, query, cb) -> cb.equal(root.get("logLevel"), logLevel));
        }

        if (StringUtils.hasText(message)) {
            spec = spec.and((root, query, cb) -> cb.like(cb.lower(root.get("message")), "%" + message.toLowerCase() + "%"));
        }

        if (startTime != null) {
            spec = spec.and((root, query, cb) -> cb.greaterThanOrEqualTo(root.get("timestamp"), startTime));
        }

        if (endTime != null) {
            spec = spec.and((root, query, cb) -> cb.lessThanOrEqualTo(root.get("timestamp"), endTime));
        }

        return logRepository.findAll(spec);
    }
}
