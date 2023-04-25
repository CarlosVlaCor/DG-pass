package com.dgpass.utils;

import lombok.Data;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Data
public class Captcha {

    private String generatedCaptcha;
    private String image;

    public void generateImage() {
        //Genera el "captcha"
        double fiveDigits = 10000 + Math.random() * 90000;
        this.generatedCaptcha = String.valueOf((int) fiveDigits);

        // Crear una imagen
        BufferedImage image = new BufferedImage(175, 75, BufferedImage.TYPE_INT_RGB);

        // Escribir el captcha en la imagen
        Graphics2D g = image.createGraphics();
        g.setFont(new Font("Arial", Font.BOLD, 30));
        g.setColor(Color.lightGray);
        g.drawString(this.generatedCaptcha, 50, 50);
        g.dispose();

        // Convertir la imagen en un array de bytes en formato PNG
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ImageIO.write(image, "png", baos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = baos.toByteArray();

        // Convertir el array de bytes en Base64
        this.image = Base64.getEncoder().encodeToString(bytes);
    }
}
