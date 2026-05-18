package com.roottrace.backend.repository;

import com.roottrace.backend.entity.TraceNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraceNodeRepository extends Neo4jRepository<TraceNode, String> {
}
