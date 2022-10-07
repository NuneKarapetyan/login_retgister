package com.example.login_retgister;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;

@SpringBootApplication
public class LoginRetgisterApplication /*implements CommandLineRunner*/ {

	@Value("${user.image.path}")
	private String userImagesFolder;
	public static void main(String[] args) {
		SpringApplication.run(LoginRetgisterApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		System.out.println("***** Creating app resources folders *****");
//		File file = new File(userImagesFolder);
//		if(file.mkdirs()){
//			System.out.println("Folder successfully created");
//		}
//	}
}
