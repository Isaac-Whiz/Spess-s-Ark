package com.whizstudios.spessark.student;

import com.whizstudios.spessark.score.Score;
import com.whizstudios.spessark.score.ScoreRepository;
import com.whizstudios.spessark.subject.Subject;
import com.whizstudios.spessark.subject.SubjectRepository;
import com.whizstudios.spessark.utils.ClassLevel;
import com.whizstudios.spessark.utils.ScoreJson;
import com.whizstudios.spessark.utils.User;
import com.whizstudios.spessark.utils.Utils;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService implements StudentDAO{

    private final StudentRepository studentRepository;
    private final ScoreRepository scoreRepository;
    private final SubjectRepository subjectRepository;

    public StudentService(StudentRepository studentRepository, ScoreRepository scoreRepository, SubjectRepository subjectRepository) {
        this.studentRepository = studentRepository;
        this.scoreRepository = scoreRepository;
        this.subjectRepository = subjectRepository;
    }


    private boolean isStudentAvailableWithOutScores(Student student) {
        return studentRepository.existsStudentByClassLevel_NameAndClassLevel_StreamAndUser_GenderAndUser_Name(
                student.getClassLevel().getName(),
                student.getClassLevel().getStream(),
                student.getUser().getGender(),
                student.getUser().getName()
        );
    }

    @Override
    @Transactional
    public boolean saveStudent(Student student) {
        if (isAvailableStudent(student)) {
            scoreRepository.save(student.getScores().get(Utils.INDEX));
            return true;
        }
            studentRepository.save(student);
            var savedStudent = this.findStudentByName(student.getUser().getName());
            return savedStudent.isPresent();
    }

    boolean isScoreAvailable(Long studentId, Long scoreId) {
        return scoreRepository.existsByStudentIdAndSubjectId(studentId, scoreId);
    }

    @Override
    @Transactional
    public boolean saveStudent(Student student, ScoreJson scoreJson) {


        if (this.isStudentAvailableWithOutScores(student)) {

            var savedStudent = findStudentByName(scoreJson.getStudentName()).get();
            var subject = this.findSubject(scoreJson.getSubjectName());

                if (this.isScoreAvailable(savedStudent.getId(), subject.getId())) {

                    scoreRepository.updateScoreByStudent_IdAndSubject_Id(
                            savedStudent.getId(),
                            subject.getId(),
                            scoreJson.getT1(),
                            scoreJson.getT2(),
                            scoreJson.getT3()
                    );
                } else {

                    scoreRepository.customSave(
                            savedStudent.getId(),
                            subject.getId(),
                            scoreJson.getT1(),
                            scoreJson.getT2(),
                            scoreJson.getT3()
                            );
                }
            return this.findStudentByName(student.getUser().getName()).isPresent();
        }
        studentRepository.save(student);
        var studentId = this.findStudentByName(scoreJson.getStudentName()).get().getId();
        var subjectId = subjectRepository.findSubjectByName(scoreJson.getSubjectName()).get().getId();
        scoreRepository.customSave(studentId, subjectId, scoreJson.getT1(), scoreJson.getT2(), scoreJson.getT3());
            return this.findStudentByName(student.getUser().getName()).isPresent();
    }

    private Subject findSubject(String name) {
        return subjectRepository.findSubjectsByName(name).get(Utils.INDEX);
    }

    @Override
    public Optional<Student> findStudentById(long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Optional<Student> findStudentByName(String name) {
        return studentRepository.findByUser_Name(name);
    }

    private boolean isAvailableStudent(Student student) {
        try {
            return studentRepository.findAll().stream().filter(stu ->
                    Objects.equals(stu.getUser().getName(),
                            student.getUser().getName())).toList().size() >= Utils.MIN_SIZE;
        } catch (NoSuchElementException e) {
            return true;
        }
    }

    @Override
    @Transactional
    public Student updateStudent(Student oldStudent, Student update) {

        if (!this.isAvailableStudent(update)) {

            var student = new Student(new User(update.getUser().getName(),
                    update.getUser().getGender(), update.getUser().getDateTime()),
                    new ClassLevel(update.getClassLevel().getName(), update.getClassLevel().getStream()));

            this.saveStudent(student);

            var studentUpdateId = this.findStudentByName(update.getUser().getName()).get().getId();

            this.updateScore(
                    oldStudent.getScores().get(Utils.INDEX).getSubject().getName(),
                    oldStudent.getId(),
                    oldStudent.getScores().get(Utils.INDEX).getT1(),
                    oldStudent.getScores().get(Utils.INDEX).getT2(),
                    oldStudent.getScores().get(Utils.INDEX).getT3(),
                    studentUpdateId,
                    update.getScores().get(Utils.INDEX).getT1(),
                    update.getScores().get(Utils.INDEX).getT2(),
                    update.getScores().get(Utils.INDEX).getT3()
                    );
            return this.findStudentByName(update.getUser().getName()).orElseThrow();
        }

        var studentUpdate = new Student(oldStudent.getId(),
                new User(update.getUser().getName(), update.getUser().getGender(), update.getUser().getDateTime()),
                new ClassLevel(update.getClassLevel().getName(), update.getClassLevel().getStream()));


        var retrivedScore = scoreRepository.findAll().stream().filter(score ->
                Objects.equals(score.getSubject().getName(),
                        oldStudent.getScores().get(Utils.INDEX).getSubject().getName())
                        && Objects.equals(score.getT1(), oldStudent.getScores().get(Utils.INDEX).getT1())
                        && Objects.equals(score.getT2(), oldStudent.getScores().get(Utils.INDEX).getT2())
                        && Objects.equals(score.getT3(), oldStudent.getScores().get(Utils.INDEX).getT3())
        ).findFirst().get();

        retrivedScore.setT1(update.getScores().get(Utils.INDEX).getT1());
        retrivedScore.setT2(update.getScores().get(Utils.INDEX).getT2());
        retrivedScore.setT3(update.getScores().get(Utils.INDEX).getT3());

        studentRepository.save(studentUpdate);
        scoreRepository.save(retrivedScore);

        return this.findStudentByName(update.getUser().getName()).orElseThrow();
    }

    private void deleteScore(String subjectName, Long studentId, Double t1, Double t2, Double t3) {
        Optional<Score> scoreOptional = scoreRepository.findScore(subjectName, studentId, t1, t2, t3);


        if (scoreOptional.isPresent()) {
            var subjectId = scoreOptional.get().getId();
            System.err.println("Id" + subjectId);
            scoreRepository.deleteScoreById(subjectId);
            System.err.println("Executed");
        }
    }

    private void deleteScore(String subjectName) {
        Optional<Subject> subjectOptional =
                subjectRepository.findAll().stream().filter(subject ->
                        Objects.equals(subject.getName(), subjectName)).findFirst();

        if (subjectOptional.isPresent()) {
            var subjectId = subjectOptional.get().getId();
            scoreRepository.deleteScoreById(subjectId);
        }
    }

    private void updateScore(String subjectName, Long studentId, Double t1, Double t2, Double t3,
                             Long studentUpdateId, Double updateT1, Double updateT2, Double updateT3) {
        Optional<Score> scoreOptional = scoreRepository.findScore(subjectName, studentId, t1, t2, t3);


        if (scoreOptional.isPresent()) {
            var scoreId = scoreOptional.get().getId();
            scoreRepository.updateScore(scoreId, studentUpdateId, updateT1, updateT2, updateT3);
        }
    }

    @Override
    public boolean deleteStudentById(long id, String subjectName) {
        if(studentRepository.findById(id).get().getScores().size() > Utils.STUDENT_SCORES_THRESHOLD) {
            deleteScore(subjectName);
            return true;
        }
        studentRepository.deleteById(id);
        return !studentRepository.existsById(id);
    }

    @Override
    public boolean deleteStudentById(long id) {
        studentRepository.deleteById(id);
        return !studentRepository.existsById(id);
    }

    @Override
    @Transactional
    public boolean deleteStudentByName(String name) {
        var deletableStuId = getId(name);
        return this.deleteStudentById(deletableStuId);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    private long getId(String name) {
        return studentRepository.findByUser_Name(name).get().getId();
    }
}
