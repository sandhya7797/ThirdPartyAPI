package com.scaler.thirdpartyapi.Models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

    private long id;
    private String title;
    private double price;
    private Category category;//made it separate class bec if we want to test new category we can simply add it to database and test without restarting the application.
    private String description;
    private String image;

}
