package com.whizstudios.spessark.subject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.testcontainers.shaded.org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class SubjectServiceIntegrationTest {

    @Autowired
    private SubjectService service;

    @Autowired
    private SubjectRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void itShouldAddSubject() {
        //Given
        var subject = new Subject(1L, "English");
        //When
        var results = service.addSubject(subject.getName());
        //Then
        assertTrue(results);
    }

    @Test
    void itShouldFindSubjectByName() {
        //Given
        var name = "English";
        service.addSubject(name);
        //When
        var result = service.findSubjectByName(name);
        //Then
        assertNotNull(result);
        assertEquals(result.getName(), name);
    }

    @Test
    void itShouldGetSubjects() {
        //Given
        var name = "English";
        var name2 = "Biology";
        var name3 = "Chemistry";
        //When
        service.addSubject(name);
        service.addSubject(name2);
        service.addSubject(name3);
        //Then
        assertEquals(3, service.getSubjects().size());
    }

    @Test
    void itShouldUpdateSubject() {
        //Given
        var oldName = "Biology";
        var newName = "Maths";
        service.addSubject(oldName);
        //When
        var result = service.updateSubject(oldName, newName);
        //Then
        assertTrue(result);
        assertEquals(1, service.getSubjects().size());
    }
}