package com.scaler.thirdpartyapi.InheritanceDemo.MappedSuperClass;

import jakarta.persistence.Entity;

@Entity(name = "ms_mentor")
public class Mentor extends User {

    private double avgRating;
}
