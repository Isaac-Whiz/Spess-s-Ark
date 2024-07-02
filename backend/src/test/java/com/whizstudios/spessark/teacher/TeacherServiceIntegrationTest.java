package com.whizstudios.spessark.teacher;

import com.github.javafaker.Faker;
import com.whizstudios.spessark.utils.Gender;
import com.whizstudios.spessark.utils.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TeacherServiceIntegrationTest {

    @Autowired
    private TeacherRepository repository;

    @Autowired
    private TeacherService service;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    Faker faker = new Faker();

    @Test
    void itShouldSaveTeacher() {
        //Given
        var teacher = new Teacher(new User(faker.name().name(), Gender.FEMALE, LocalDateTime.now()),
                faker.internet().password());
        //When
        service.saveTeacher(teacher);
        //Then
        assertEquals(1, service.getAllTeachers().size());
    }

    @Test
    void itShouldFindTeacherById() {
        //Given
        var name = "Isaac";
        var teacher = new Teacher(new User(name, Gender.FEMALE, LocalDateTime.now()),
                faker.internet().password());
        //When
        service.saveTeacher(teacher);
        var saved = service.getAllTeachers().getFirst();
        var result = service.findTeacherById(saved.getId()).get();
        //Then
        assertEquals(teacher.getUser().getName(), result.getUser().getName());
    }

    @Test
    void itShouldFindTeacherByName() {
        var name = faker.name().name();
        var teacher = new Teacher(new User(name, Gender.FEMALE, LocalDateTime.now()),
                faker.internet().password());
        service.saveTeacher(teacher);
        var teachers = service.getAllTeachers();
        //When
        var result = service.findTeacherByName(name).get();
        //Then
        assertEquals(name, result.getUser().getName());
        assertEquals(1, teachers.size());
    }

    @Test
    void itShouldUpdateTeacher() {
        var name = faker.name().name();
        var updateName = faker.name().name();
        var teacher = new Teacher(new User(name, Gender.FEMALE, LocalDateTime.now()),
                faker.internet().password());
        service.saveTeacher(teacher);
        var retrieved = service.getAllTeachers().getFirst();
        retrieved.setUser(new User(updateName, teacher.getUser().getGender(), teacher.getUser().getDateTime()));
        //When
        var result = service.updateTeacher(teacher, retrieved);
        //Then
        assertEquals(updateName, result.getUser().getName());
        assertEquals(1, service.getAllTeachers().size());
    }

    @Test
    void itShouldDeleteTeacherById() {
        //Given
        var name = faker.name().name();
        var teacher = new Teacher(new User(name, Gender.FEMALE, LocalDateTime.now()),
                faker.internet().password());
        service.saveTeacher(teacher);
        var saved = service.getAllTeachers().getFirst();
        //When
        var result = service.deleteTeacherById(saved.getId());
        //Then
        assertEquals(0, service.getAllTeachers().size());
        assertTrue(result);
    }

    @Test
    void itShouldDeleteTeacherByName() {
        //Given
        var id = 1L;
        var name = faker.name().name();
        var teacher = new Teacher(id, new User(name, Gender.FEMALE, LocalDateTime.now()),
                faker.internet().password());
        service.saveTeacher(teacher);
        //When
        var result = service.deleteTeacherByName(name);
        //Then
        assertTrue(result);
        assertEquals(0, service.getAllTeachers().size());
    }

    @Test
    void itShouldGetAllTeachers() {
        //Given
        var teacher = new Teacher(new User(faker.name().name(), Gender.FEMALE, LocalDateTime.now()),
                faker.internet().password());
        var teacher2 = new Teacher(new User(faker.name().name(), Gender.FEMALE, LocalDateTime.now()),
                faker.internet().password());
        var teacher3 = new Teacher(new User(faker.name().name(), Gender.FEMALE, LocalDateTime.now()),
                faker.internet().password());
        //When
        service.saveTeacher(teacher);
        service.saveTeacher(teacher2);
        service.saveTeacher(teacher3);
        //Then
        assertEquals(3, service.getAllTeachers().size());

    }
}