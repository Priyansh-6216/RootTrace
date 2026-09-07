package com.roottrace.backend.remediation.repository;

import com.roottrace.backend.remediation.model.RemediationPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RemediationRepository extends JpaRepository<RemediationPlan, Long> {
    Optional<RemediationPlan> findByIncidentId(String incidentId);
}
