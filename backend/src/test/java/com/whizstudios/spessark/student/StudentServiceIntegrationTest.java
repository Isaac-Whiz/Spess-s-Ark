package com.whizstudios.spessark.student;

import com.whizstudios.spessark.Utils.ClassLevel;
import com.whizstudios.spessark.Utils.Gender;
import com.whizstudios.spessark.Utils.Stream;
import com.whizstudios.spessark.Utils.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class StudentServiceIntegrationTest {

    @Autowired
    private StudentService service;

    @Autowired
    private StudentRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void itShouldSaveStudent() {
        //Given
        var name = "Juma";
        var id = 2L;
        //When
        var result = service.saveStudent(createStudent(id, name));
        //Then
        assertTrue(result);
        assertEquals(1, service.getStudents().size());

    }

    @Test
    void itShouldFindStudentById() {
        //Given
        var name = "Juma";
        //When
        service.saveStudent(createStudent(name));
        var saved = service.getStudents().stream().findFirst().get();
        var result = service.findStudentById(saved.getId()).get();
        //Then
        assertNotNull(result);
        assertEquals(1, service.getStudents().size());
        assertEquals(result.getId(), saved.getId());
    }

    @Test
    void itShouldFindStudentByName() {
        //Given
        var name = "Juma";
        var id = 2L;
        //When
        service.saveStudent(createStudent(id, name));
        service.findStudentById(id);
        var result = service.findStudentByName(name).get();
        //Then
        assertNotNull(result);
        assertEquals(1, service.getStudents().size());
        assertEquals(name, result.getUser().getName());

    }

    @Test
    void itShouldUpdateStudent() {
        //Given
        var name = "Juma";
        var updateName = "Kaluya";
        var oldStudent = createStudent(name);
        var update = createStudent(updateName);
        //When
        service.saveStudent(oldStudent);
        var updated = service.updateStudent(oldStudent, update);
        //Then
        assertEquals(1,  service.getStudents().size());
        assertEquals(oldStudent.getId(), updated.getId());
        assertEquals(updated.getUser().getName(), updateName);
    }

    @Test
    void itShouldDeleteStudentById() {
        //Given
        var name = "Juma";
        var id = 2L;
        service.saveStudent(createStudent(id, name));
        //When
        var result = service.deleteStudentById(id);
        //Then
        assertTrue(result);
    }

    @Test
    void itShouldDeleteStudentByName() {
        //Given
        var name = "Juma";
        var id = 2L;
        service.saveStudent(createStudent(id, name));
        //When
        var result = service.deleteStudentByName(name);
        //Then
        assertTrue(result);
    }

    @Test
    void itShouldGetStudents() {
        //Given
        service.saveStudent(createStudent(1L, "John"));
        service.saveStudent(createStudent(2L, "Jonaz"));
        service.saveStudent(createStudent(3L, "James"));
        //When
        var results = service.getStudents();
        //Then
        assertEquals(3, results.size());
        assertFalse(results.isEmpty());
    }

    private Student createStudent(Long id, String name) {
        return new Student(id, new User(name,
                Gender.FEMALE, LocalDateTime.now()),
                new ClassLevel("S4", Stream.NORTH));
    }

    private Student createStudent(String name) {
        return new Student(new User(name,
                Gender.FEMALE, LocalDateTime.now()),
                new ClassLevel("S4", Stream.NORTH));
    }
}