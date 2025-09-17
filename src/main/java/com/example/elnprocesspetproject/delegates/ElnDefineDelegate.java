package com.example.elnprocesspetproject.delegates;

import com.example.elnprocesspetproject.entities.ProcessEntity;
import com.example.elnprocesspetproject.entities.simple.ProcessStatus;
import com.example.elnprocesspetproject.model.ElnModel;
import com.example.elnprocesspetproject.model.base.Eln;
import com.example.elnprocesspetproject.model.base.Person;
import com.example.elnprocesspetproject.services.ProcessService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

import static com.example.elnprocesspetproject.constants.Stages.DEFINE_ELN_STAGE;

@Component
public class ElnDefineDelegate implements JavaDelegate {
    @Autowired
    ProcessService processService;
    @Autowired
    RuntimeService runtimeService;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        Long processId = (Long) delegateExecution.getVariable("process_id");
        ProcessEntity processEntity = processService.getProcess(processId);
        //начинаем этап
        processEntity.addStage(DEFINE_ELN_STAGE);
        processService.save(processEntity);
        //запрос по ЭЛН обогощаем данными

        ElnModel elnModel = (ElnModel)processEntity.getLastProcessJsonAttr().getJsonAttr().getContent();
        elnModel.setPerson(Person.builder()
                .firstName("Альфия")
                .lastName("Исмагилова")
                .middleName("Рафаульeвна")
                .build());
        Eln eln = elnModel.getEln();
        eln.setReason("010");
        eln.setState("030");
        eln.setLnDate(LocalDate.now().plusDays(1));


        long waitTime = 0;
        if(!"30".equals(eln.getState())) {
            processEntity.setStatus(ProcessStatus.SUSPENDED);
        }
        if(eln.getLnDate().isAfter(LocalDate.now())) {
            processEntity.setStatus(ProcessStatus.SUSPENDED);
            waitTime = eln.getLnDate().toEpochDay() - LocalDate.now().toEpochDay();
        }
        //processEntity.setStatus(ProcessStatus.CLOSED);
        delegateExecution.setVariable("process_id", processEntity.getId());
        delegateExecution.setVariable("status", processEntity.getStatus().name());
        delegateExecution.setVariable("state", eln.getState());
        delegateExecution.setVariable("wait_time", waitTime);
    }
}
