package com.example.elnprocesspetproject.services.impl;

import com.example.elnprocesspetproject.entities.ProcessEntity;
import com.example.elnprocesspetproject.repo.ProcessRepository;
import com.example.elnprocesspetproject.services.ProcessService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component

public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private ProcessRepository processRepository;

    public void save(ProcessEntity process) {
        processRepository.save(process);
    }

}
