package com.scaler.thirdpartyapi.InheritanceDemo.MappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name="ms_instructor")
public class Instructor extends User {

    private String favStudent;
}
