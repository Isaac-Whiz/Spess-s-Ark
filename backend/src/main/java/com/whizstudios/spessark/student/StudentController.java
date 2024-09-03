package com.whizstudios.spessark.student;

import com.whizstudios.spessark.utils.StudentScore;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @GetMapping(path = "api/v1/students")
    List<Student> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = "api/v1/students/findById/{id}")
    Student getStudentById(@PathVariable("id") long id) {
        return studentService.findStudentById(id).orElseThrow();
    }

    @GetMapping(path = "api/v1/students/findByName/{name}")
    Student getStudentByName(@PathVariable("name") String name) {
        return studentService.findStudentByName(name).orElseThrow();
    }

    @PostMapping("api/v1/student/register")
    boolean registerStudent(@RequestBody StudentScore studentScore) {
        return studentService.saveStudent(studentScore.getStudent(), studentScore.getScore());
    }

    @PutMapping(path = "api/v1/students/update")
    Student updateStudent(@RequestBody StudentUpdateRequest request) {
        return studentService.updateStudent(request.getOldStudent(), request.getUpdate());
    }

    @DeleteMapping(path = "api/v1/students/delete/{name}")
    boolean deleteStudentByName(@PathVariable("name") String name) {
        return studentService.deleteStudentByName(name);
    }

    @DeleteMapping(path = "api/v1/students/delete/id/{id}")
    boolean deleteStudentById(@PathVariable("id") Long id) {
        return studentService.deleteStudentById(id);
    }

}
