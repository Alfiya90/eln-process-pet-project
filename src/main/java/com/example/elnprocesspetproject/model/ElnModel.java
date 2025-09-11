package com.example.elnprocesspetproject.model;

import com.example.elnprocesspetproject.model.base.Eln;
import com.example.elnprocesspetproject.model.base.Person;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ElnModel implements Serializable {
    Eln eln;
    Person person;
}
