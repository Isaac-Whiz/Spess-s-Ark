package com.whizstudios.spessark;

import com.github.javafaker.Faker;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
public abstract class GenericTestsClass {

    @Container
    protected static final PostgreSQLContainer<?> postgresContainer = new
            PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("spess-test-db")
            .withUsername("whiz")
            .withPassword("whiz");


    @DynamicPropertySource
    private static void datasourceProperties(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    protected static final Faker faker = new Faker();
}
