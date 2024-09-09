package com.whizstudios.spessark;

import com.whizstudios.spessark.admin.Admin;
import com.whizstudios.spessark.admin.AdminService;
import com.whizstudios.spessark.utils.Gender;
import com.whizstudios.spessark.utils.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@SpringBootApplication
public class SpessArkApplication {

    @Value("${spring.mail.username}")
    private String email;

    @Value("${admin-name}")
    String adminName;

    @Value("${admin-password}")
    String adminPassword;

    @Value("${frontend-url}")
    private String frontendUrl;


    public static void main(String[] args) {
        SpringApplication.run(SpessArkApplication.class, args);
    }

    @Bean
    CommandLineRunner runner(AdminService adminService) {
        return args -> {


            try {
                adminService.findAdminByEmail(email).isEmpty();
            } catch (NoSuchElementException e) {
                adminService.saveAdmin(new Admin(
                        new User(adminName, Gender.MALE, LocalDateTime.now()),
                        email,
                        adminPassword
                ));

            }
        };
    }
}
