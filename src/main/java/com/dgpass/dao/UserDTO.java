package com.dgpass.dao;

import com.dgpass.utils.Captcha;
import lombok.Data;

@Data
public class UserDTO {
    private String userName;
    private String email;
    private String password;
    private String phoneNumber;
    private int rol;

    private String captcha;
}
