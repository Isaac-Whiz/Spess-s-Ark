package com.whizstudios.spessark.score;

import com.whizstudios.spessark.student.StudentService;
import com.whizstudios.spessark.subject.SubjectService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

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
    public boolean updateScore(Score update) {
        var oldScore = findScoreByName(update.getSubject().getName());
        oldScore.setScores(update.getScores());
        scoreRepository.save(oldScore);
        return findScoreByName(update.getSubject().getName()) != null;
    }

    @Override
    public Score findScoreByName(String name) {
        return scoreRepository.findAll().stream().filter(score -> score.getSubject().getName().equals(name)).findFirst().orElseThrow();
    }
}

