package com.example.elnprocesspetproject.entities;



import lombok.*;

import javax.persistence.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "process_stage", schema = "eln_process_demo")
@Builder
public class ProcessStageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "process_id", nullable = false)
    private ProcessEntity process;


    @Column(name = "mnemonic")
    private String mnemonic;

    @Column(name = "stage_begin")
    private Date stageBegin;
    @Column(name = "stage_end")
    private Date stageEnd;
}
