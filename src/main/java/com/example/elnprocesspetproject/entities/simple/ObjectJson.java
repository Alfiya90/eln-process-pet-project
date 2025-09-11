package com.example.elnprocesspetproject.entities.simple;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjectJson<T> implements Serializable {
    private T content;
    private Class<?> aClass;
    public ObjectJson(T content) {
        this.content = content;
        this.aClass = content.getClass();
    }
}
