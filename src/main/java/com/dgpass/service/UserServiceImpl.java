package com.dgpass.service;

import com.dgpass.Entity.Rol;
import com.dgpass.Entity.User;
import com.dgpass.dao.CambiarPass;
import com.dgpass.dao.UserDTO;
import com.dgpass.repository.RolRepository;
import com.dgpass.repository.UserRepository;
import com.dgpass.utils.RolEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements  UserService{

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private UserRepository userRepository;
    @Override
    public User registerUser(User user) {
        User newUser = new User();
        Rol rol = rolRepository.findById(RolEnum.USER.getId());
        newUser.setRol(rol);
        newUser.setEmail(user.getEmail());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(newUser);

        return newUser;
    }

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User newUser(UserDTO userDTO) {
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        newUser.setEmail(userDTO.getEmail());
        newUser.setUsername(userDTO.getUserName());
        Rol rol = rolRepository.findById(userDTO.getRol());
        newUser.setRol(rol);
        userRepository.save(newUser);
        return newUser;
    }

    @Override
    public User findById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User performEdit(User user, long id) {
        User actUser = userRepository.findById(id).get();
        actUser.setUsername(user.getUsername());
        actUser.setEmail(user.getEmail());
        if(user.getPassword() != null || !user.getPassword().isBlank()){
            actUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        userRepository.save(actUser);
        return actUser;
    }

    @Override
    public void performDelete(long id) {
        User user = userRepository.findById(id).get();
        userRepository.delete(user);
    }

    @Override
    public User cambiarPass(CambiarPass cambiarPass) {
        User user = userRepository.findByEmail(cambiarPass.getEmail());
        if(user == null){
            return null;
        }
        if(!cambiarPass.getPassword().equals(cambiarPass.getConfPassword())){
            return null;
        }
        user.setPassword(passwordEncoder.encode(cambiarPass.getPassword()));
        return user;
    }

}
