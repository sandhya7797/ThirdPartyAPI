package com.scaler.thirdpartyapi.Models;

import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@MappedSuperclass//This doesn't create table for Parent class and adds attributes from parent class to all child classes.
public class BaseModel {
    @Id
    private long id;
    private Date createdAt;
    private Date updatedAt;
    private boolean isDeleted;
}