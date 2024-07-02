package com.whizstudios.spessark.student;

import com.whizstudios.spessark.GenericTestsClass;
import com.whizstudios.spessark.utils.ClassLevel;
import com.whizstudios.spessark.utils.Gender;
import com.whizstudios.spessark.utils.Stream;
import com.whizstudios.spessark.utils.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryTest extends GenericTestsClass {

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    void itShouldSaveStudent() {
        //Given

        //When
        studentRepository.save(createStudent());
        //Then
        assertEquals(1, studentRepository.findAll().size());
    }

    @Test
    void itShouldUpdateStudent() {
        //Given
        var name = "James";
        var id = 10L;
        var updateName = "John";
        //When
        studentRepository.save(createStudent(id, name));
        var savedStudent = studentRepository.findAll().stream().findFirst().get();
        studentRepository.save(createStudent(savedStudent.getId(), updateName));
        var updatedStudent = studentRepository.findAll().stream()
                .filter(std -> std.getUser().getName().equals(updateName)).findFirst().get();
        //Then
        assertEquals(updatedStudent.getUser().getName(), updateName);
        assertEquals(1, studentRepository.findAll().size());
    }

    @Test
    void itShouldFindStudentById() {
        //Given
        var name = "Kamulegeya";
        //When
        studentRepository.save(createStudent(name));
        var savedStudent = studentRepository.findAll().stream().findFirst().get();
        var foundStudent = studentRepository.findById(savedStudent.getId()).get();
        //Then
        assertNotNull(foundStudent);
        assertEquals(foundStudent.getId(), savedStudent.getId());
        assertEquals(foundStudent.getUser().getName(), name);
        assertEquals(1, studentRepository.findAll().size());
    }

    @Test
    void itShouldDeletedById() {
        //Given
        var name = "Kamulegeya";
        //When
        studentRepository.save(createStudent(name));
        var savedStudent = studentRepository.findAll().stream().findFirst().get();
        studentRepository.deleteById(savedStudent.getId());
        //Then
        assertEquals(0, studentRepository.findAll().size());
    }

    private Student createStudent() {
        return new Student( new User(faker.name().name(),
                Gender.FEMALE, LocalDateTime.now()),
                new ClassLevel("S4", Stream.NORTH));
    }

    private Student createStudent(String name) {
        return new Student( new User(name,
                Gender.FEMALE, LocalDateTime.now()),
                new ClassLevel("S4", Stream.NORTH));
    }

    private Student createStudent(Long id, String name) {
        return new Student(id, new User(name,
                Gender.FEMALE, LocalDateTime.now()),
                new ClassLevel("S4", Stream.NORTH));
    }
}