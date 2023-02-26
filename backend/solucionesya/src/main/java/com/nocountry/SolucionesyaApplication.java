package com.nocountry;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@SpringBootApplication
public class SolucionesyaApplication {


	public static void main(String[] args) throws Exception {
		SpringApplication.run(SolucionesyaApplication.class, args);

		/*// Set your Cloudinary credentials
		Dotenv dotenv = Dotenv.load();
		Cloudinary cloudinary = new Cloudinary(dotenv.get("CLOUDINARY_URL"));
		cloudinary.config.secure = true;
		System.err.println(cloudinary.config.cloudName);

		File file = new File("C:/Users/HFC/Documents/HFC/CV/Imagen cuentas/perro.jpg");

		Map<String, Object> params = ObjectUtils.asMap("public_id", "perro_salchicha", "overwrite", true, "folder", "upload_image");

		cloudinary.uploader().upload(file, params);*/
	}
}