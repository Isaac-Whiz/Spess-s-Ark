package com.whizstudios.spessark;

import com.whizstudios.spessark.admin.Admin;
import com.whizstudios.spessark.Utils.Gender;
import com.whizstudios.spessark.Utils.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpessArkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpessArkApplication.class, args);

        var admin = new Admin(10, new User("Kato", Gender.MALE, LocalDateTime.now()), "password");
    }

}
