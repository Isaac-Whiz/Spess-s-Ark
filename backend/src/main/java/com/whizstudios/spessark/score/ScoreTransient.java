package com.whizstudios.spessark.score;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ScoreTransient {
    private double t1;
    private double t2;
    private double t3;
    private LocalDateTime dateTime;
    private Long studentId;
}
