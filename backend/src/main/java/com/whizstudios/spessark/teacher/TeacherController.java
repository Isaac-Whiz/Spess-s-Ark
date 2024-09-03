package com.whizstudios.spessark.teacher;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping(path = "api/v1/teachers")
    List<Teacher> getTeachers() {
        return teacherService.getAllTeachers();
    }

    @GetMapping(path = "api/v1/teachers/findById/{id}")
    Teacher getTeacherById(@PathVariable("id") long id) {
        return teacherService.findTeacherById(id).orElseThrow();
    }

    @GetMapping(path = "api/v1/teachers/findByEmail/{email}/{subjectTaught}")
    Teacher getTeacherByEmail(@PathVariable("email") String email, @PathVariable("subjectTaught") String subjectTaught) {
        return teacherService.findTeacherTransByEmail(new TeacherTransient(email, subjectTaught)).orElseThrow();
    }

    @PostMapping(path = "api/v1/teacher")
    boolean registerTeacher(@RequestBody Teacher teacher) {
        return teacherService.saveTeacher(teacher);
    }

    @PutMapping(path = "api/v1/teachers/update")
    Teacher updateTeacher(@RequestBody TeacherUpdateRequest request) {
        return teacherService.updateTeacher(request.getOldTeacher(), request.getUpdate());
    }

    @DeleteMapping(path = "api/v1/teachers/delete/{name}")
    boolean deleteTeacherByName(@PathVariable("name") String name) {
        return teacherService.deleteTeacherByName(name);
    }

}
