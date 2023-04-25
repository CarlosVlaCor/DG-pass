package com.dgpass.controllers;

import com.dgpass.Entity.User;
import com.dgpass.dao.CambiarPass;
import com.dgpass.dao.UserDTO;
import com.dgpass.service.UserService;
import com.dgpass.utils.Captcha;
import com.dgpass.utils.Email;
import com.dgpass.utils.EmailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailSender emailSender;
    @GetMapping("/")
    public String inicio(Model model, HttpSession session){

        model.addAttribute("users", userService.findAllUsers());
        Captcha captcha = new Captcha();
        captcha.generateImage();
        session.setAttribute("captcha", captcha);
        model.addAttribute("userInsert", new UserDTO());
        return "index.html";
    }
    @GetMapping("/login")
    public String login(Authentication auth){
        if(auth == null || !auth.isAuthenticated()){
            return "login";
        }

        return "redirect:/";
    }
    @GetMapping("/registro")
    public String registro(Model model, Authentication auth){
        if(auth == null || !auth.isAuthenticated()){
            model.addAttribute("user", new User());
            return "registro.html";
        }
        return "redirect:/";

    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/editar/{id}")
    public String editUser(@PathVariable(name = "id") long id, Model model){
        User user = userService.findById(id);
        model.addAttribute("user", user);
        return "editarUsuario";
    }

    @GetMapping("/recuperar")
    public String recuperarPassword(Authentication auth, Model model){
        if(auth != null){
            return "redirect:/";
        }
        model.addAttribute("email", new Email());
        return "recuperar";
    }
    @PostMapping("/recuperar/")
    public String enviarEmail(Authentication auth, Email email) throws MessagingException {
        if(auth != null){
            return "redirect:/";
        }
        emailSender.sendEmail(email);
        return "recuperar";
    }

    @GetMapping("/cambiarPass")
    public String cambiarPass(Authentication auth, Model model){
        if(auth != null){
            return "redirect:/";
        }
        model.addAttribute("cambiarPass", new CambiarPass());
        return "cambiarPass";
    }
    @PostMapping("/cambiarPass/")
    public String cambiarPass(Authentication auth, CambiarPass cambiarPass){
        if(auth != null){
            return "redirect:/";
        }
        User user = userService.cambiarPass(cambiarPass);
        if(user == null){
            return "redirect:/cambiarPass/";
        }
        return "redirect:/";
    }
    @GetMapping("/integrantes/")
    public String integrantes(Authentication auth, Model model){

        return "integrantes";
    }


}
