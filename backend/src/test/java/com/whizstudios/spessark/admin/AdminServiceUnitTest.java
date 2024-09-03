package com.whizstudios.spessark.admin;

import com.github.javafaker.Faker;
import com.whizstudios.spessark.utils.Gender;
import com.whizstudios.spessark.utils.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AdminServiceUnitTest {

    @InjectMocks
    private AdminService service;

    @Mock
    private AdminRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        repository.deleteAll();
    }

    Faker faker = new Faker();

    @Test
    void itShouldSaveAdmin() {

        //Given
        var name = faker.name().name();
        var email = faker.internet().emailAddress();
        var admin = new Admin(
                1L, new User(name, Gender.MALE, LocalDateTime.now()),
                email,
                faker.internet().password());

        //When
        when(repository.save(any(Admin.class))).thenReturn(admin);
        when(repository.findAll()).thenReturn(List.of(admin));

        // Then
        var saved = service.saveAdmin(admin);


        assertEquals(1L, saved.getId());
        verify(repository, times(1)).save(any(Admin.class));
        verify(repository, times(1)).findAll();
    }
}