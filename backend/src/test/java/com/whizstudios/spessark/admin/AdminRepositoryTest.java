package com.whizstudios.spessark.admin;

import com.whizstudios.spessark.GenericTestsClass;
import com.whizstudios.spessark.utils.Gender;
import com.whizstudios.spessark.utils.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdminRepositoryTest extends GenericTestsClass {

    @Autowired
    private AdminRepository repository;

    @BeforeEach
    void refreshDb() {
        repository.deleteAll();
    }

    @Test
    void itShouldSaveAnAdmin() {
        //Given
        var admin = new Admin(new User(
                faker.name().firstName(),
                 Gender.MALE,
                LocalDateTime.now()),
                faker.internet().emailAddress(),
                faker.name().name());
        //When
        repository.save(admin);
        //Then
        assertThat(repository.findAll()).isNotEmpty();
    }

    @Test
    void itShouldFindAnAdminById() {
        //Given
        var admin = new Admin(new User(
                faker.name().firstName(),
                Gender.MALE,
                LocalDateTime.now()),
                faker.internet().emailAddress(),
                faker.name().name());
        //When
        repository.save(admin);
        var retrieved = repository.findById(1L);
        //Then
        assertEquals(retrieved.get().getId(), 1);
    }

    @Test
    void itShouldUpdateAnAdmin() {
        //Given
        var oldName = "Lumu";
        var newName = "Lule";
        var oldAdmin = new Admin(new User(
                oldName,
                Gender.MALE,
                LocalDateTime.now()),
                faker.internet().emailAddress(),
                oldName);
        //When
        repository.save(oldAdmin);
        oldAdmin.getUser().setName(newName);
        repository.save(oldAdmin);
        var updated = repository.findAll().stream().findFirst().get();
        System.out.println("Admin: " + updated);

        //Then
        assertEquals(updated.getUser().getName(), newName);
    }

    @Test
    void itShouldFindAllAdmins() {
        //Given
        createAndSaveAdmins();
        //When
        var size = repository.findAll().size();
        //Then
        assertEquals(size, 5);
    }

    @Test
    void itShouldDeleteAnAdmin() {
        //Given
        createAndSaveAdmin();
        //When
        var id = repository.findAll().stream().findFirst().get().getId();
        repository.deleteById(id);
        //Then
        assertThat(repository.findAll()).isEmpty();
    }

    @Test
    void itShouldDeleteAllAdmins() {
        //Given
        createAndSaveAdmins();
        //When
        repository.deleteAll();
        //Then
        assertThat(repository.findAll()).isEmpty();
    }

    void createAndSaveAdmins() {
        var oldAdmin = new Admin(new User(
                faker.name().firstName(),
                Gender.MALE,
                LocalDateTime.now()),
                faker.internet().emailAddress(),
                faker.internet().password());
        var oldAdmin2 = new Admin(new User(
                faker.name().firstName(),
                Gender.MALE,
                LocalDateTime.now()),
                faker.internet().emailAddress(),
                faker.internet().password());
        var oldAdmin3 = new Admin(new User(
                faker.name().firstName(),
                Gender.MALE,
                LocalDateTime.now()),
                faker.internet().emailAddress(),
                faker.internet().password());
        var oldAdmin4 = new Admin(new User(
                faker.name().firstName(),
                Gender.MALE,
                LocalDateTime.now()),
                faker.internet().emailAddress(),
                faker.internet().password());
        var oldAdmin5 = new Admin(new User(
                faker.name().firstName(),
                Gender.MALE,
                LocalDateTime.now()),
                faker.internet().emailAddress(),
                faker.internet().password());
        repository.save(oldAdmin);
        repository.save(oldAdmin2);
        repository.save(oldAdmin3);
        repository.save(oldAdmin4);
        repository.save(oldAdmin5);
    }

    void createAndSaveAdmin() {
        var admin = new Admin(new User(
                faker.name().firstName(),
                Gender.MALE,
                LocalDateTime.now()),
                faker.internet().emailAddress(),
                faker.name().name());
        repository.save(admin);
    }
}
