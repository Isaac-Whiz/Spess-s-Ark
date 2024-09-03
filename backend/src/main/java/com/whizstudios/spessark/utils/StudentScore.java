package com.whizstudios.spessark.utils;

import com.whizstudios.spessark.student.Student;
import lombok.Data;

@Data
public class StudentScore {
    private Student student;
    private ScoreJson score;
}
