package com.scaler.thirdpartyapi.InheritanceDemo.TablePerClass;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;

//In Table per class is exactly same as MappedSuperClass but here parent also has table created.

@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Entity(name = "tbc_user")
public class User {
    @Id
    private int id;
    private String name;
    private String email;
}
