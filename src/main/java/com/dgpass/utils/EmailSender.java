package com.dgpass.utils;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmail(Email email) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        System.out.println(mailSender);
        System.out.println(email);
        helper.setFrom("carlos28trus@gmail.com");
        helper.setTo(email.getEmail());
        helper.setSubject("DG-pass company ps");
        helper.setText("Cambio de contraseña <a href='http://localhost:8080/cambiarPass'> Haz clic para el cambio de contraseña</a>",true);
        mailSender.send(message);
    }
}
