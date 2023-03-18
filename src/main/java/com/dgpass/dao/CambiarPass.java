package com.dgpass.dao;

import lombok.Data;

@Data
public class CambiarPass {
    private String email;
    private String password;
    private String confPassword;
}
