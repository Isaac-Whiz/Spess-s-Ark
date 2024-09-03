package com.whizstudios.spessark.student;

import com.whizstudios.spessark.score.Score;
import com.whizstudios.spessark.utils.ScoreJson;

import java.util.List;
import java.util.Optional;

public interface StudentDAO {
    boolean saveStudent(Student student);
    boolean saveStudent(Student student, ScoreJson scoreJson);
    Optional<Student> findStudentById(long id);
    Optional<Student> findStudentByName(String name);
    Student updateStudent(Student oldStudent, Student update);
    boolean deleteStudentById(long id, String subjectName);
    boolean deleteStudentById(long id);
    boolean deleteStudentByName(String name);
    List<Student> getStudents();
}
