package com.whizstudios.spessark.utils;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
        private String name;
        @Enumerated(EnumType.STRING)
        private Gender gender;
        private LocalDateTime dateTime;
}