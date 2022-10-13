package com.example.login_retgister;

import com.example.login_retgister.models.User;
import com.example.login_retgister.models.enums.Role;
import com.example.login_retgister.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.io.File;

@SpringBootApplication
public class LoginRetgisterApplication implements CommandLineRunner {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(LoginRetgisterApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        if (userRepository.findByEmail("admin@gmail.com").isEmpty()) {
            userRepository.save(User.builder()
                    .name("Admin")
                    .surname("Adminyan")
                    .email("admin@gmail.com")
                    .role(Role.ADMIN)
                    .password(passwordEncoder.encode("123"))
                    .build());
        }
    }
}
