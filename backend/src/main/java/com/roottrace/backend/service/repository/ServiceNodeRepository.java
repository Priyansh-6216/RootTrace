package com.roottrace.backend.service.repository;

import com.roottrace.backend.service.model.ServiceNode;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServiceNodeRepository extends Neo4jRepository<ServiceNode, String> {

    @Query("MATCH (s:Service {id: $serviceId})-[:CALLS*1..5]->(downstream:Service) RETURN downstream")
    List<ServiceNode> findDownstreamDependencies(String serviceId);

    @Query("MATCH (upstream:Service)-[:CALLS*1..5]->(s:Service {id: $serviceId}) RETURN upstream")
    List<ServiceNode> findUpstreamDependencies(String serviceId);
}
