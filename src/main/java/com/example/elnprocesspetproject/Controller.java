package com.example.elnprocesspetproject;

import com.example.elnprocesspetproject.entities.Notification;
import com.example.elnprocesspetproject.entities.ProcessEntity;
import com.example.elnprocesspetproject.entities.ProcessJsonAttr;
import com.example.elnprocesspetproject.entities.ProcessStageEntity;
import com.example.elnprocesspetproject.entities.simple.ObjectJson;
import com.example.elnprocesspetproject.entities.simple.ProcessStatus;
import com.example.elnprocesspetproject.model.ElnModel;
import com.example.elnprocesspetproject.model.base.Eln;
import com.example.elnprocesspetproject.model.base.Person;
import com.example.elnprocesspetproject.services.ProcessService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.runtime.ProcessInstantiationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class Controller {
    @Autowired
    private ProcessService processService;

    @GetMapping("save_process")
    private void saveProcess() {
        ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
        ProcessInstantiationBuilder instance = engine.getRuntimeService().createProcessInstanceByKey("eln_process_v1");


        ProcessEntity process = new ProcessEntity();

        process.setStatus(ProcessStatus.PROCESSING);

        processService.save(process);

        List<ProcessStageEntity> processStageEntityList = new ArrayList<>();
        processStageEntityList.add(ProcessStageEntity.builder()
                        .process(process)
                        .stageBegin(new Date())
                        .mnemonic("1 этап")
                        .stageEnd(new Date())
                .build());
        processStageEntityList.add(ProcessStageEntity.builder()
                .process(process)
                .stageBegin(new Date())
                .mnemonic("2 этап")
                .stageEnd(new Date())
                .build());
        process.setStages(processStageEntityList);

        List<Notification> notifications = new ArrayList<>();
        notifications.add(Notification.builder()
                        .process(process)
                        .sendDate(new Date())
                        .build());
        notifications.add(Notification.builder()
                .process(process)
                .sendDate(new Date())
                .build());
        process.setNotifications(notifications);

        List<ProcessJsonAttr> processJsonAttrs = new ArrayList<>();
        ElnModel elnModel = new ElnModel();
        elnModel.setEln(Eln.builder()
                        .code("123")
                        .reason("10")
                        .state("010")
                .build());
        elnModel.setPerson(Person.builder()
                        .firstName("Альфия")
                        .lastName("Исмагилова")
                        .middleName("Рафаульeвна")
                .build());

        ProcessJsonAttr<ElnModel> processJsonAttr = new ProcessJsonAttr<>();
        processJsonAttr.setProcess(process);

        processJsonAttr.setJsonAttr(new ObjectJson(elnModel));
        processJsonAttrs.add(processJsonAttr);

        process.setProcessJsonAttrs(processJsonAttrs);




        processService.save(process);
        instance.setVariable("process_id", process.getId());
        instance.executeWithVariablesInReturn();

    }
}
