package com.roottrace.backend.incident.service;

import com.roottrace.backend.service.model.ServiceNode;
import com.roottrace.backend.service.service.ServiceCatalogService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BlastRadiusService {

    private final ServiceCatalogService serviceCatalogService;

    public List<String> calculateBlastRadius(String serviceName) {
        log.info("Calculating blast radius for service: {}", serviceName);
        
        // This relies on the serviceId being the same as serviceName for now,
        // or we'd need a lookup by name. Assuming serviceName is the ID.
        List<ServiceNode> downstream = serviceCatalogService.getDownstreamDependencies(serviceName);
        
        List<String> affectedServices = downstream.stream()
                .map(ServiceNode::getName)
                .collect(Collectors.toList());
                
        log.info("Blast radius for {}: {}", serviceName, affectedServices);
        return affectedServices;
    }
}
