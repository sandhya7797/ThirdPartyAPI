package com.scaler.thirdpartyapi.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Category extends BaseModel {

    @OneToMany(fetch=FetchType.LAZY, mappedBy = "category", cascade = CascadeType.REMOVE)
//being already mapped by an attribute called "category". If we don't use mappedBy in a bi-directional mapping spring will create two tables.
    // If someone wants to remove category then all products related to that category will be deleted.
    private List<Product> products;

    private String name;

}
