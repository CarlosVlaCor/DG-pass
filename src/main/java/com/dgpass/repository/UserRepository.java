package com.dgpass.repository;

import com.dgpass.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepository extends JpaRepository<User, Long>{
    public User findByUsername(String username);

    public User findByEmail(String email);
}
