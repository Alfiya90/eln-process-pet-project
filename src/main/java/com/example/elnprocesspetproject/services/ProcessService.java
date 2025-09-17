package com.example.elnprocesspetproject.services;

import com.example.elnprocesspetproject.entities.ProcessEntity;

public interface ProcessService {
    void save(ProcessEntity process);
    ProcessEntity getProcess (Long processId);
}
