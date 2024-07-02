package com.whizstudios.spessark.teacher;

import com.whizstudios.spessark.GenericTestsClass;
import com.whizstudios.spessark.utils.Gender;
import com.whizstudios.spessark.utils.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TeacherRepositoryTest extends GenericTestsClass {

    @Autowired
    private TeacherRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void itShouldSaveTeacher() {
        //Given
        var teacher = new Teacher(new User(faker.name().name(), Gender.FEMALE, LocalDateTime.now()),
                faker.internet().password());
        //When
        repository.save(teacher);
        //Then
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void itShouldDeleteTeacherById() {
        //Given
        var teacher = new Teacher(new User(faker.name().name(), Gender.FEMALE, LocalDateTime.now()),
                faker.internet().password());
        repository.save(teacher);
        var retrieved = repository.findAll().getFirst();
        //When
        repository.deleteById(retrieved.getId());
        //Then
        assertEquals(0, repository.findAll().size());
    }

    @Test
    void itShouldUpdateTeacher() {
        //Given
        var name = "Joram";
        var teacher = new Teacher(new User(faker.name().name(), Gender.FEMALE, LocalDateTime.now()),
                faker.internet().password());
        repository.save(teacher);
        var retrieved = repository.findAll().getFirst();
        retrieved.setUser(new User(name, Gender.FEMALE, LocalDateTime.now()));
        //When
        repository.save(retrieved);
        //Then
        assertEquals(1, repository.findAll().size());
        assertEquals(name, retrieved.getUser().getName());
    }
}