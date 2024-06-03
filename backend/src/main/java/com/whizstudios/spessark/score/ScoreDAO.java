package com.whizstudios.spessark.score;

public interface ScoreDAO {

    boolean addScore(String studentName, String subjectName, Integer scoreValue);
    boolean updateScore(Score update);
    Score findScoreByName(String name);

}
