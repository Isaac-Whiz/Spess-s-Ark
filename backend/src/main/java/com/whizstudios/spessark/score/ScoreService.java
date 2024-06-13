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
    public void updateScore(ScoreTransient oldScore, ScoreTransient update) {
        var retrievedSubject = subjectService.getSubjects().stream().filter(subject ->
                Objects.equals(subject.getName(), oldScore.getSubjectName())).findFirst().orElseThrow();

        var retrievedStudent = studentService.getStudents().stream().filter(student ->
                Objects.equals(student.getUser().getName(), oldScore.getStudentName())).findFirst().orElseThrow();

        var retrievedScore = scoreRepository.findAll().stream().filter(
                score -> Objects.equals(score.getScores(), oldScore.getScores())
                         && Objects.equals(score.getSubject().getId(), retrievedSubject.getId())
                        && Objects.equals(score.getStudent().getId(), retrievedStudent.getId()))
                .findFirst().orElseThrow();

        retrievedScore.setScores(update.getScores());
       scoreRepository.save(retrievedScore);
    }
}

