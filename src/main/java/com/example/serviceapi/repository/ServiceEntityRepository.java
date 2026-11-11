package com.example.serviceapi.repository;

import com.example.serviceapi.model.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, Long> {
}