package com.example.serviceapi.controller;

import com.example.serviceapi.dto.ServiceEntityDto;
import com.example.serviceapi.service.ServiceEntityService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceEntityController {

    private final ServiceEntityService service;

    public ServiceEntityController(ServiceEntityService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ServiceEntityDto>> getAllServices() {
        return ResponseEntity.ok(service.getAllServices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceEntityDto> getServiceById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getServiceById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceEntityDto> createService(@Valid @RequestBody ServiceEntityDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createService(dto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ServiceEntityDto> updateService(@PathVariable Long id, @Valid @RequestBody ServiceEntityDto dto) {
        return ResponseEntity.ok(service.updateService(id, dto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        service.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}