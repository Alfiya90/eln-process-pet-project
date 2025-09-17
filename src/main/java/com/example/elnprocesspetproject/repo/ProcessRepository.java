package com.example.elnprocesspetproject.repo;

import com.example.elnprocesspetproject.entities.ProcessEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProcessRepository extends JpaRepository<ProcessEntity, Long> {
    Optional<ProcessEntity> findById(Long id);
}
