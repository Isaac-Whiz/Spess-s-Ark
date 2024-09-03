package com.whizstudios.spessark.score;

import java.time.LocalDateTime;

public interface ScoreDAO {

    boolean addScore(String studentName, String subjectName, LocalDateTime dateTime, double t1, double t2, double t3);
    void updateScore(ScoreTransient oldScore, ScoreTransient update);
    void deleteScore(String subject);

}
