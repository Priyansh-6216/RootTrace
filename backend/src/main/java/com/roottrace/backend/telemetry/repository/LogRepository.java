package com.roottrace.backend.telemetry.repository;

import com.roottrace.backend.telemetry.entity.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogEntry, Long>, JpaSpecificationExecutor<LogEntry> {
    List<LogEntry> findByServiceName(String serviceName);
    List<LogEntry> findByLogLevel(String logLevel);
    List<LogEntry> findByTraceId(String traceId);
    boolean existsByEventId(String eventId);
}
