package com.whizstudios.spessark.student;

import java.util.List;
import java.util.Optional;

public interface StudentDAO {
    boolean saveStudent(Student student);
    Optional<Student> findStudentById(long id);
    Optional<Student> findStudentByName(String name);
    Student updateStudent(Student update);
    boolean deleteStudentById(long id);
    boolean deleteStudentByName(String name);
    List<Student> getStudents();
}
