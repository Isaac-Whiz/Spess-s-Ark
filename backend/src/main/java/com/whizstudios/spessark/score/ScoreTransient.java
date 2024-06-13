package com.whizstudios.spessark.score;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ScoreTransient {
    private int scores;
    private String subjectName;
    private String studentName;
}
