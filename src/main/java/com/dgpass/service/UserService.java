package com.dgpass.service;

import com.dgpass.Entity.User;
import com.dgpass.dao.CambiarPass;
import com.dgpass.dao.UserDTO;

import java.util.List;

public interface UserService {

    public User registerUser(User user);

    public List<User> findAllUsers();

    public User newUser(UserDTO user);

    public User findById(long id);

    public User performEdit(User user, long id);

    public  void performDelete(long id);

    public User cambiarPass(CambiarPass cambiarPass);
}
