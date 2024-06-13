package com.whizstudios.spessark.subject;

import com.whizstudios.spessark.GenericTestsClass;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SubjectRepositoryTest extends GenericTestsClass {

    @Autowired
    private SubjectRepository subjectRepository;

    @BeforeEach
    void setUp() {
        subjectRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        subjectRepository.deleteAll();
    }

    @Test
    void itShouldSaveSubject() {
        //Given
        var subject = new Subject("Maths");
        //When
        subjectRepository.save(subject);
        //Then
        assertFalse(subjectRepository.findAll().isEmpty());
        assertEquals(1, subjectRepository.findAll().size());
    }

    @Test
    void itShouldUpdateSubject() {
        //Given
        var subject = new Subject("English");
        var update = new Subject("Biology");
        //When
        subjectRepository.save(subject);
        var retrieved = subjectRepository.findAll().stream().findFirst().get();
        retrieved.setName(update.getName());
        subjectRepository.save(retrieved);
        var result = subjectRepository.findAll().stream().findFirst().get();
        //Then
        assertEquals(1, subjectRepository.findAll().size());
        assertEquals(update.getName(), result.getName());
    }

    @Test
    void itShouldDeleteSubjectById() {
        //Given
        var subject = new Subject(1L, "English");
        //When
        subjectRepository.save(subject);
        subjectRepository.deleteById(1L);
        //Then
        assertEquals(0, subjectRepository.findAll().size());
    }
}