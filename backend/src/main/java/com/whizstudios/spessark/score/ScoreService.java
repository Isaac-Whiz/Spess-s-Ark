package com.whizstudios.spessark.score;

import com.whizstudios.spessark.student.StudentService;
import com.whizstudios.spessark.subject.SubjectService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ScoreService implements ScoreDAO {

    private final ScoreRepository scoreRepository;
    private final StudentService studentService;
    private final SubjectService subjectService;

    public ScoreService(ScoreRepository scoreRepository, StudentService studentService, SubjectService subjectService) {
        this.scoreRepository = scoreRepository;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }


    @Transactional
    @Override
    public boolean addScore(String studentName, String subjectName, Integer scores) {
        var student = studentService.findStudentByName(studentName).orElseThrow();
        var subject = subjectService.findSubjectByName(subjectName);

        scoreRepository.save(new Score(scores, student, subject));
        var savedScore = scoreRepository.findAll().stream()
                .filter(score ->
                        score.getStudent().getUser().getName().equals(studentName) &&
                                score.getSubject().getName().equals(subjectName))
                .findFirst().get();
        return savedScore != null;
    }

    @Override
    public void updateScore(Score oldScore, Score update) {
        var retrieved = scoreRepository.findAll().stream().filter(score ->
                Objects.equals(score.getSubject().getName(), oldScore.getSubject().getName())
                && Objects.equals(score.getScores(), oldScore.getScores())
                && Objects.equals(score.getStudent().getUser().getName(), oldScore.getStudent().getUser().getName())).findFirst().get();
        if (retrieved != null) {
            scoreRepository.save(update);
        }
    }

}

