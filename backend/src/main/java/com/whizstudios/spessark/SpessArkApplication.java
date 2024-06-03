package com.whizstudios.spessark;

import com.whizstudios.spessark.Utils.*;
import com.whizstudios.spessark.admin.Admin;
import com.whizstudios.spessark.admin.AdminService;
import com.whizstudios.spessark.student.Student;
import com.whizstudios.spessark.student.StudentService;
import com.whizstudios.spessark.teacher.Teacher;
import com.whizstudios.spessark.teacher.TeacherService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class SpessArkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpessArkApplication.class, args);

    }
    @Bean
    CommandLineRunner runner(AdminService adminService, StudentService studentService, TeacherService teacherService) {
        return args -> {
//            var admin = new Admin( new User("Kato Joel", Gender.MALE, LocalDateTime.now()), "password1");
//            var admin2 = new Admin( new User("Anna", Gender.FEMALE, LocalDateTime.now()), "password2");
//            var admin3 = new Admin( new User("Bella", Gender.FEMALE, LocalDateTime.now()), "password3");
//            var admin4 = new Admin( new User("Ozzy", Gender.FEMALE, LocalDateTime.now()), "password4");
//
//            adminService.saveAdmin(admin);
//            adminService.saveAdmin(admin2);
//            adminService.saveAdmin(admin3);
//            adminService.saveAdmin(admin4);
//
//            var stu = new Student(new User("Isa", Gender.MALE, LocalDateTime.now()), new ClassLevel("s1", Stream.NORTH),
//                    new Subject("English", 42));
//            var stu2 = new Student(new User("Isaac", Gender.MALE, LocalDateTime.now()), new ClassLevel("s4", Stream.NORTH),
//                    new Subject("Chemistry", 56));
//            var stu3 = new Student(new User("John", Gender.MALE, LocalDateTime.now()), new ClassLevel("s3", Stream.SOUTH),
//                    new Subject("Kiswahili", 55));
//
//            studentService.saveStudent(stu2);
//            studentService.saveStudent(stu);
//            studentService.saveStudent(stu3);
//
//
//            var teacher = new Teacher(new User("Luti", Gender.MALE, LocalDateTime.now()), "passowrd");
//            var teacher2 = new Teacher(new User("Jane", Gender.FEMALE, LocalDateTime.now()), "passowrd2");
//            var teacher3 = new Teacher(new User("Joan", Gender.FEMALE, LocalDateTime.now()), "passowrd3");
//
//            teacherService.saveTeacher(teacher);
//            teacherService.saveTeacher(teacher2);
//            teacherService.saveTeacher(teacher3);

            System.out.println("Executed");

        };
    }

}
