package com.whizstudios.spessark.score;

import com.whizstudios.spessark.student.StudentService;
import com.whizstudios.spessark.subject.SubjectService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public boolean addScore(String studentName, String subjectName, LocalDateTime dateTime, double t1, double t2, double t3) {
        var student = studentService.findStudentByName(studentName).orElseThrow();
        var subject = subjectService.findSubjectByName(subjectName);

            var alreadySavedScore = scoreRepository.findScore(subject.getId(), student.getId());

            if (alreadySavedScore.isPresent()) {
                var score = alreadySavedScore.get();
                scoreRepository.updateScore(score.getId(), student.getId(), t1, t2, t3);
                return scoreRepository.existsByStudentIdAndSubjectId(student.getId(), subject.getId());
            }
            scoreRepository.save(new Score(t1, t2, t3, student, subject));
            return scoreRepository.existsByStudentIdAndSubjectId(student.getId(), subject.getId());
    }

    @Override
    @Transactional
    public void updateScore(ScoreTransient oldScore, ScoreTransient update) {

       var stuId = oldScore.getStudentId();
       var retrivedScore = scoreRepository.findAll().stream().filter(score ->
               Objects.equals(score.getStudent().getId(), stuId)).findFirst().get();
       retrivedScore.setT1(update.getT1());
       retrivedScore.setT2(update.getT2());
       retrivedScore.setT3(update.getT3());
       scoreRepository.save(retrivedScore);

    }

    @Transactional
    @Override
    public void deleteScore(String subject) {
        var id = scoreRepository.findAll().stream().filter(score ->
                Objects.equals(score.getSubject().getName(), subject)).findFirst().get().getId();
        scoreRepository.deleteById(id);
    }
}

