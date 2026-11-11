package com.example.serviceapi.service;

import com.example.serviceapi.dto.ServiceEntityDto;
import com.example.serviceapi.model.ServiceEntity;
import com.example.serviceapi.repository.ServiceEntityRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ServiceEntityService {

    private final ServiceEntityRepository repository;

    public ServiceEntityService(ServiceEntityRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ServiceEntityDto> getAllServices() {
        return repository.findAll().stream()
                .map(this::toDto)
                .toList();
    }

    @Transactional(readOnly = true)
    public ServiceEntityDto getServiceById(Long id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new NoSuchElementException("Service not found with ID: " + id));
    }

    @Transactional
    public ServiceEntityDto createService(ServiceEntityDto dto) {
        ServiceEntity entity = new ServiceEntity(
                dto.name(),
                dto.description(),
                dto.price(),
                dto.active()
        );
        return toDto(repository.save(entity));
    }

    @Transactional
    public ServiceEntityDto updateService(Long id, ServiceEntityDto dto) {
        ServiceEntity entity = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Service not found with ID: " + id));

        entity.setName(dto.name());
        entity.setDescription(dto.description());
        entity.setPrice(dto.price());
        entity.setActive(dto.active());

        return toDto(repository.save(entity));
    }

    @Transactional
    public void deleteService(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("Service not found with ID: " + id);
        }
        repository.deleteById(id);
    }

    private ServiceEntityDto toDto(ServiceEntity entity) {
        return new ServiceEntityDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getPrice(),
                entity.isActive()
        );
    }
}