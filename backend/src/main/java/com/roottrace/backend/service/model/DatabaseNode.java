package com.roottrace.backend.service.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Database")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DatabaseNode {

    @Id
    private String id;
    
    private String name;
    private String type; // e.g., POSTGRES, REDIS
    private String environment;
}
