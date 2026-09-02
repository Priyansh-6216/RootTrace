package com.roottrace.backend.service.controller;

import com.roottrace.backend.service.model.ServiceNode;
import com.roottrace.backend.service.service.ServiceCatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceController {

    private final ServiceCatalogService serviceCatalogService;

    @PostMapping
    public ResponseEntity<ServiceNode> createService(@RequestBody ServiceNode serviceNode) {
        return ResponseEntity.ok(serviceCatalogService.saveService(serviceNode));
    }

    @GetMapping
    public ResponseEntity<List<ServiceNode>> getAllServices() {
        return ResponseEntity.ok(serviceCatalogService.getAllServices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceNode> getServiceById(@PathVariable String id) {
        ServiceNode service = serviceCatalogService.getServiceById(id);
        return service != null ? ResponseEntity.ok(service) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}/dependencies")
    public ResponseEntity<Map<String, List<ServiceNode>>> getDependencies(@PathVariable String id) {
        return ResponseEntity.ok(Map.of(
            "downstream", serviceCatalogService.getDownstreamDependencies(id),
            "upstream", serviceCatalogService.getUpstreamDependencies(id)
        ));
    }
}
