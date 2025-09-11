package com.example.elnprocesspetproject.repo;

import com.example.elnprocesspetproject.entities.ProcessStageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessStageRepository extends JpaRepository<ProcessStageEntity, Long> {
}
