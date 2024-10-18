package com.scaler.thirdpartyapi.InheritanceDemo.JoinedTable;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@PrimaryKeyJoinColumn(name = "user_id")
@Entity(name="jt_instructor")
public class Instructor extends User {

    private String favStudent;
}
