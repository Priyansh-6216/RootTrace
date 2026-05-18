package com.roottrace.backend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.HashSet;
import java.util.Set;

@Node
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TraceNode {

    @Id
    private String traceId;

    private String serviceName;
    private String endpoint;
    private Long durationMs;
    private Boolean hasError;

    @Relationship(type = "CALLS")
    @Builder.Default
    private Set<TraceNode> calls = new HashSet<>();
}
