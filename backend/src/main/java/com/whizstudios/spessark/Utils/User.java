package com.whizstudios.spessark.Utils;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
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
        @Embedded
        private Gender gender;
        private LocalDateTime dateTime;
}