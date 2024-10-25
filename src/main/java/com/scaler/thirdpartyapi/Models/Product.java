package com.scaler.thirdpartyapi.Models;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {

    private String title;
    private double price;
    @ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Category category;//made it separate class bec if we want to test new category we can simply add it to database and test without restarting the application.
    private String description;
    private String image;
}
