package com.dgpass.controllers;

import com.dgpass.Entity.Rol;
import com.dgpass.Entity.User;
import com.dgpass.dao.UserDTO;
import com.dgpass.service.UserService;
import com.dgpass.utils.Captcha;
import com.dgpass.utils.RolEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/register")
    public String registrarUsuario(com.dgpass.Entity.User user){

        userService.registerUser(user);
        return "redirect:/login";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/newUser")
    public String newUser(UserDTO userDTO, HttpSession session, RedirectAttributes redirectAttrs){
        Captcha captcha = (Captcha) session.getAttribute("captcha");
        if(!captcha.getGeneratedCaptcha().equals(userDTO.getCaptcha())){
            redirectAttrs.addFlashAttribute("errCaptcha", "Error al introducir el Captcha");
            return "redirect:/";
        }
        userService.newUser(userDTO);

        return "redirect:/";
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping ("/editar/{id}")
    public String performEditUser(User user, @PathVariable(name = "id") long id){
        userService.performEdit(user, id);
        return "redirect:/";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete/{id}")
    public String performDelete(@PathVariable(name = "id") long id){
        userService.performDelete(id);
        return "redirect:/";
    }
}
