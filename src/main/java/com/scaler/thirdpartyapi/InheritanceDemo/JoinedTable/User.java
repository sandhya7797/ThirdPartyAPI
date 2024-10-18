package com.scaler.thirdpartyapi.InheritanceDemo.JoinedTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//In Joined Table, parent and child tables are created and child tables uses foreign key to refer parent table attributes.

@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "jt_user")
public class User {
    @Id
    private int id;
    private String name;
    private String email;
}
