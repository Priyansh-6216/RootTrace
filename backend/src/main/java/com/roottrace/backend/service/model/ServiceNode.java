package com.roottrace.backend.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.ArrayList;
import java.util.List;

@Node("Service")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceNode {

    @Id
    private String id;
    
    private String name;
    private String environment;
    private String version;
    private String owner;
    private String repository;
    private String health;

    @Relationship(type = "CALLS")
    @Builder.Default
    private List<ServiceNode> calls = new ArrayList<>();

    @Relationship(type = "DEPENDS_ON")
    @Builder.Default
    private List<DatabaseNode> databases = new ArrayList<>();
}
