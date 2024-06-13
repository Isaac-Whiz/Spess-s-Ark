package com.whizstudios.spessark;

import com.whizstudios.spessark.Utils.*;
import com.whizstudios.spessark.admin.Admin;
import com.whizstudios.spessark.admin.AdminService;
import com.whizstudios.spessark.score.Score;
import com.whizstudios.spessark.score.ScoreService;
import com.whizstudios.spessark.student.Student;
import com.whizstudios.spessark.student.StudentService;
import com.whizstudios.spessark.subject.Subject;
import com.whizstudios.spessark.subject.SubjectService;
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
//    @Bean
//    CommandLineRunner runner(ScoreService scoreService, SubjectService subjectService, AdminService adminService, StudentService studentService, TeacherService teacherService) {
//        return args -> {
////            var admin = new Admin( new User("Kato Joel", Gender.MALE, LocalDateTime.now()), "password1");
////            var admin2 = new Admin( new User("Anna", Gender.FEMALE, LocalDateTime.now()), "password2");
////            var admin3 = new Admin( new User("Bella", Gender.FEMALE, LocalDateTime.now()), "password3");
////            var admin4 = new Admin( new User("Ozzy", Gender.FEMALE, LocalDateTime.now()), "password4");
////
////            adminService.saveAdmin(admin);
////            adminService.saveAdmin(admin2);
////            adminService.saveAdmin(admin3);
////            adminService.saveAdmin(admin4);
//////
//            var stu = new Student(new User("Isa", Gender.MALE, LocalDateTime.now()),
//                    new ClassLevel("s1", Stream.NORTH));
//            var stu2 = new Student(new User("Isaac", Gender.MALE, LocalDateTime.now()),
//                    new ClassLevel("s4", Stream.NORTH));
//            var stu3 = new Student(new User("John", Gender.MALE, LocalDateTime.now()),
//                    new ClassLevel("s3", Stream.SOUTH));
////
////            studentService.saveStudent(stu2);
////            studentService.saveStudent(stu);
////            studentService.saveStudent(stu3);
////
////            var teacher = new Teacher(new User("Luti", Gender.MALE, LocalDateTime.now()), "passowrd");
////            var teacher2 = new Teacher(new User("Jane", Gender.FEMALE, LocalDateTime.now()), "passowrd2");
////            var teacher3 = new Teacher(new User("Joan", Gender.FEMALE, LocalDateTime.now()), "passowrd3");
////
////            teacherService.saveTeacher(teacher);
////            teacherService.saveTeacher(teacher2);
////            teacherService.saveTeacher(teacher3);
//
//            var sub = new Subject("Maths");
//            var sub2 = new Subject("English");
//            var sub3 = new Subject("Biology");
//            var sub4 = new Subject("Chemistry");
//            var sub5 = new Subject("Physics");
////            subjectService.addSubject(sub5.getName());
////            subjectService.addSubject(sub.getName());
////            subjectService.addSubject(sub2.getName());
////            subjectService.addSubject(sub3.getName());
////            subjectService.addSubject(sub4.getName());
//
//            var score = new Score(45, stu2, sub);
//            var score1 = new Score(56, stu2, sub2);
//            var score2 = new Score(80, stu2, sub3);
//            var score3 = new Score(49, stu3, sub);
//            var score4 = new Score(65, stu3, sub4);
//            var score5 = new Score(40, stu3, sub5);
//
////            scoreService.addScore(score.getStudent().getUser().getName(), score.getSubject().getName(), score.getScores());
////            scoreService.addScore(score1.getStudent().getUser().getName(), score1.getSubject().getName(), score1.getScores());
////            scoreService.addScore(score2.getStudent().getUser().getName(), score2.getSubject().getName(), score2.getScores());
////            scoreService.addScore(score3.getStudent().getUser().getName(), score3.getSubject().getName(), score3.getScores());
////            scoreService.addScore(score4.getStudent().getUser().getName(), score4.getSubject().getName(), score4.getScores());
////            scoreService.addScore(score5.getStudent().getUser().getName(), score5.getSubject().getName(), score5.getScores());
//
//            System.out.println("Executed");
//
//        };
//    }

}
