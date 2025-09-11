package com.example.elnprocesspetproject.entities;


import com.example.elnprocesspetproject.entities.simple.ObjectJson;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "process_json_attr", schema = "eln_process_demo")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class ProcessJsonAttr<T> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @ManyToOne
    @JoinColumn(name = "process_id")
    private ProcessEntity process;

    @Type(type = "jsonb")
    @Column(name = "json_attrs", columnDefinition = "jsonb")
    private ObjectJson<T> jsonAttr;
}
