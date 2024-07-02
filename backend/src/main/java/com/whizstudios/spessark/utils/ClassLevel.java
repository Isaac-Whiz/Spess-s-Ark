package com.whizstudios.spessark.utils;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassLevel {
    private String name;
    @Enumerated(EnumType.STRING)
    private Stream stream;

}
