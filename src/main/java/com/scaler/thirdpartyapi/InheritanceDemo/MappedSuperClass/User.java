package com.scaler.thirdpartyapi.InheritanceDemo.MappedSuperClass;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

//In MappedSuperClass it copies all parent attributes to all of its child classes but parent table will not be created.

@Getter
@Setter
@MappedSuperclass
public class User {
    @Id
    private int id;
    private String name;
    private String email;
}
