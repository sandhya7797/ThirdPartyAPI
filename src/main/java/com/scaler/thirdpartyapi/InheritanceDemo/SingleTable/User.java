package com.scaler.thirdpartyapi.InheritanceDemo.SingleTable;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

//In Table per class is exactly same as MappedSuperClass but here parent also has table created.

@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "userType",
discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue(value = "0")
@Entity(name = "st_user")
public class User {
    @Id
    private int id;
    private String name;
    private String email;
}
