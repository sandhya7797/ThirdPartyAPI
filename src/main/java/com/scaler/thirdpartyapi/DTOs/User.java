package com.scaler.thirdpartyapi.DTOs;


import com.scaler.thirdpartyapi.Models.BaseModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class User extends BaseModel {

    private String name;

    private String email;

    private String hashPassword;

    private List<Role> roles;

    private boolean emailVerified;

}
