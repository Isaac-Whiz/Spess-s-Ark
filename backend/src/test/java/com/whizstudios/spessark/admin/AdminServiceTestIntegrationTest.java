package com.whizstudios.spessark.admin;

import com.whizstudios.spessark.GenericTestsClass;
import com.whizstudios.spessark.Utils.Gender;
import com.whizstudios.spessark.Utils.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminServiceTestIntegrationTest extends GenericTestsClass {

    @Autowired
    private AdminService service;

    @Autowired
    private AdminRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void itShouldSaveAdmin() {
        //Given
        var name = faker.name().name();
        var admin = new Admin(
                1L, new User(name, Gender.MALE, LocalDateTime.now()),
                faker.internet().password());

        // When
        service.saveAdmin(admin);

        // Then
        var admins = service.getAllAdmins();
        assertThat(admins).hasSize(1);
        assertThat(admins.get(0).getUser().getName()).isEqualTo(name);
    }

    @Test
    void itShouldUpdateAdmin() {

        //Given
        var name = faker.name().name();
        var admin = new Admin(
                1L, new User(name, Gender.MALE, LocalDateTime.now()),
                faker.internet().password());
        var update = new Admin(
                 1L, new User("Luti", Gender.FEMALE, LocalDateTime.now()),
                "faker.internet().newPassword()");

        //When
        service.saveAdmin(admin);

        service.updateAdmin(admin, update);
        //Then
        var admins = service.getAllAdmins();
        assertThat(admins).hasSize(1);
        assertThat(admins.get(0).getUser().getName()).isEqualTo("Luti");
        assertThat(admins.get(0).getUser().getGender()).isEqualTo(Gender.FEMALE);
    }

    @Test
    void itShouldDeleteAdminById() {
        //Given
        var id = 1L;
        var admin = new Admin(
                id, new User("Kapiito Alemye", Gender.MALE, LocalDateTime.now()),
                faker.internet().password());
        //When
        var saved = service.saveAdmin(admin);
        var result = service.deleteAdminById(saved.getId());
        var admins = service.getAllAdmins();
        System.out.println("Size: " + admins.size());
        admins.forEach(System.out::println);
        //Then
        assertTrue(result);
        assertEquals(0, admins.size());
    }

    @Test
    void itShouldDeleteAdminByName() {

        //Given
        var admin = new Admin(
                1L, new User(faker.name().name(), Gender.MALE, LocalDateTime.now()),
                faker.internet().password());

        //When
        service.saveAdmin(admin);
        service.deleteAdminByName(admin.getUser().getName());
        var admins = service.getAllAdmins();
        //Then
        assertThat(admins).hasSize(0);
        assertThat(admins).isEmpty();
    }

    @Test
    void itShouldFindAdminById() {
        //Given
        var id = 1L;
        var admin = new Admin(
                id, new User(faker.name().name(), Gender.MALE, LocalDateTime.now()),
                faker.internet().password());

        //When
        service.saveAdmin(admin);
        var admins = service.getAllAdmins();

        //Then
        assertThat(admins).hasSize(1);
    }

    @Test
    void itShouldFindAdminByName() {
        //Given
        var name = faker.name().name();
        var admin = new Admin(
                1L, new User(name, Gender.MALE, LocalDateTime.now()),
                faker.internet().password());

        //When
        service.saveAdmin(admin);
        var foundAdmin = service.getAllAdmins().stream().
                filter(adm -> adm.getUser().getName().equals(name)).findFirst().get();

        //Then
        assertThat(foundAdmin).isNotNull();
        assertEquals(name, foundAdmin.getUser().getName());
    }

    @Test
    void itShouldGetAllAdmins() {

        //Given
        var admin = new Admin(
                1L, new User(faker.name().name(), Gender.MALE, LocalDateTime.now()),
                faker.internet().password());

        var admin2 = new Admin(
                1L, new User(faker.name().name(), Gender.MALE, LocalDateTime.now()),
                faker.internet().password());

        var admin3 = new Admin(
                1L, new User(faker.name().name(), Gender.MALE, LocalDateTime.now()),
                faker.internet().password());

        var admin4 = new Admin(
                1L, new User(faker.name().name(), Gender.MALE, LocalDateTime.now()),
                faker.internet().password());

        //When
        service.saveAdmin(admin);
        service.saveAdmin(admin2);
        service.saveAdmin(admin3);
        service.saveAdmin(admin4);
        var admins = service.getAllAdmins();

        //Then
        assertEquals(4, admins.size());
    }
}