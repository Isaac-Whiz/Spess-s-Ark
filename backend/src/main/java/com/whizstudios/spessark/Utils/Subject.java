package com.whizstudios.spessark.Utils;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Subject {
    private String name;
    private long score;
}
