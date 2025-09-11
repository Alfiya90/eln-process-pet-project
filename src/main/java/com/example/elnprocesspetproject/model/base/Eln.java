package com.example.elnprocesspetproject.model.base;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Eln implements Serializable {
    String code;
    String reason;
    String state;

}
