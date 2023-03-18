package com.dgpass.security;

import com.dgpass.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository usuarioRepositorio;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.dgpass.Entity.User usuario =  usuarioRepositorio.findByUsername(username);
        if(usuario == null){
            throw new UsernameNotFoundException("No se encontró el usuario");
        }

        GrantedAuthority ga = new SimpleGrantedAuthority(usuario.getRol().getNombre());
        return new User(usuario.getUsername(), usuario.getPassword(), Collections.singleton(ga));
    }

}
