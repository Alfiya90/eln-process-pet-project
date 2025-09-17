package com.example.elnprocesspetproject.entities;


import com.example.elnprocesspetproject.entities.simple.ProcessStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "process", schema = "eln_process_demo")
public class ProcessEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    ProcessStatus status;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_date")
    private Date createdDate;

    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updated;

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProcessStageEntity> stages;

    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Notification> notifications;
    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, orphanRemoval = true)
    List<ProcessJsonAttr> processJsonAttrs;

    public ProcessJsonAttr getLastProcessJsonAttr() {
        if(getProcessJsonAttrs() != null && !getProcessJsonAttrs().isEmpty()) {
            return getProcessJsonAttrs().get(getProcessJsonAttrs().size() - 1);
        }
        return null;
    }

    public void addStage(String stage) {
        if(getStages() == null) {
            setStages(new ArrayList<>());
        }
        getStages().add(ProcessStageEntity.builder()
                        .process(this)
                        .stageBegin(new Date())
                        .mnemonic(stage)
                .build());
    }
}
