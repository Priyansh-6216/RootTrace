package com.roottrace.backend.service.service;

import com.roottrace.backend.service.model.ServiceNode;
import com.roottrace.backend.service.repository.ServiceNodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceCatalogService {

    private final ServiceNodeRepository serviceNodeRepository;

    public ServiceNode saveService(ServiceNode serviceNode) {
        return serviceNodeRepository.save(serviceNode);
    }

    public List<ServiceNode> getAllServices() {
        return serviceNodeRepository.findAll();
    }

    public ServiceNode getServiceById(String id) {
        return serviceNodeRepository.findById(id).orElse(null);
    }

    public List<ServiceNode> getDownstreamDependencies(String serviceId) {
        return serviceNodeRepository.findDownstreamDependencies(serviceId);
    }

    public List<ServiceNode> getUpstreamDependencies(String serviceId) {
        return serviceNodeRepository.findUpstreamDependencies(serviceId);
    }
}
