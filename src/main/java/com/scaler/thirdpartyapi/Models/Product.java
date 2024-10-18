package com.scaler.thirdpartyapi.Models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {

    private String title;
    private double price;
    @ManyToOne
    private Category category;//made it separate class bec if we want to test new category we can simply add it to database and test without restarting the application.
    private String description;
    private String image;

}
