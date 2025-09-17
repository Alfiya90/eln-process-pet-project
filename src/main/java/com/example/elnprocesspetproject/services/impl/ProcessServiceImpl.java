package com.example.elnprocesspetproject.services.impl;

import com.example.elnprocesspetproject.entities.ProcessEntity;
import com.example.elnprocesspetproject.repo.ProcessRepository;
import com.example.elnprocesspetproject.services.ProcessService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProcessServiceImpl implements ProcessService {
    @Autowired
    private ProcessRepository processRepository;
    @Autowired
    private ObjectMapper objectMapper;

    public void save(ProcessEntity process) {
        processRepository.save(process);
    }
    public ProcessEntity getProcess (Long processId) {
        Optional<ProcessEntity> processOp = processRepository.findById(processId);
        if(processOp.isPresent()) {
            ProcessEntity process = processOp.get();
            if(process.getProcessJsonAttrs() != null && !process.getProcessJsonAttrs().isEmpty()) {
                objectMapper.convertValue(process.getLastProcessJsonAttr().getJsonAttr().getContent(), process.getLastProcessJsonAttr().getJsonAttr().getAClass());
            }
            return process;
        }
        return null;
    }



}
