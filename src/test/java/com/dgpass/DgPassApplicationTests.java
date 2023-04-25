package com.dgpass;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@SpringBootTest
class DgPassApplicationTests {

	@Test
	void contextLoads() {
		double fiveDigits = 10000 + Math.random() * 90000;
		// Crear una imagen en blanco de 200x100 píxeles
		BufferedImage image = new BufferedImage(200, 100, BufferedImage.TYPE_INT_RGB);

		// Dibujar el texto "Hola, mundo!" en la imagen
		Graphics2D g = image.createGraphics();
		g.setFont(new Font("Arial", Font.BOLD, 20));
		g.setColor(Color.BLACK);
		g.drawString(String.valueOf((int)fiveDigits), 50, 50);
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
		String base64 = Base64.getEncoder().encodeToString(bytes);

		// Mostrar la cadena Base64 en la consola
		System.out.println(base64);
	}

}
