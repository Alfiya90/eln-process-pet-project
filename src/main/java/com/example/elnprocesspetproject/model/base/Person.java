package com.example.elnprocesspetproject.model.base;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Person implements Serializable {
    String snils;
    String firstName;
    String lastName;
    String middleName;

}
