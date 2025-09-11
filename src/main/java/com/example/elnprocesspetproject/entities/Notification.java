package com.example.elnprocesspetproject.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "notifications", schema = "eln_process_demo")
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "process_id")
    private ProcessEntity process;

    @Column(name = "send_date")
    private Date sendDate;

    @Column(name = "message")
    private String message;

}
