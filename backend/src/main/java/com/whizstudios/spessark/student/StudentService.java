package com.whizstudios.spessark.student;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements StudentDAO{

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public boolean saveStudent(Student student) {
        studentRepository.save(student);
        var savedStudent = this.findStudentByName(student.getUser().getName());
        return savedStudent.isPresent();
    }

    @Override
    public Optional<Student> findStudentById(long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Optional<Student> findStudentByName(String name) {
        var studentId = getId(name);
        return this.findStudentById(studentId);
    }

    @Override
    public Student updateStudent(Student update) {
        var oldStu = studentRepository.findById(update.getId());

        if (oldStu.isPresent()) {
            if (oldStu.get().getId() == update.getId()) {
                studentRepository.save(update);
            }
        }
        return this.findStudentById(update.getId()).orElseThrow();
    }

    @Override
    public boolean deleteStudentById(long id) {
        studentRepository.deleteById(id);
        return !studentRepository.existsById(id);
    }

    @Override
    public boolean deleteStudentByName(String name) {
        var deletableStuId = getId(name);
        return this.deleteStudentById(deletableStuId);
    }

    @Override
    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    private long getId(String name) {
        return this.getStudents().stream().filter(student -> student.getUser().getName().matches(name)).findFirst().orElseThrow().getId();
    }
}
