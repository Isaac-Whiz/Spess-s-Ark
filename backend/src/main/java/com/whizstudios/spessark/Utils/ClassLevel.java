package com.whizstudios.spessark.Utils;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

@Embeddable
@Data
public class ClassLevel {
    private String name;
    @Embedded
    private Stream stream;
}
