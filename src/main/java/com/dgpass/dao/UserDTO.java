package com.dgpass.dao;

import lombok.Data;

@Data
public class UserDTO {
    private String userName;
    private String email;
    private String password;
    private int rol;
}
