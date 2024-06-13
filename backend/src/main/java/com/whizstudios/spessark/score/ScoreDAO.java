package com.whizstudios.spessark.score;

public interface ScoreDAO {

    boolean addScore(String studentName, String subjectName, Integer scoreValue);
    void updateScore(ScoreTransient oldScore, ScoreTransient update);

}
