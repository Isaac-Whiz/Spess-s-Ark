package com.whizstudios.spessark.subject;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping(path = "api/v1/subject")
    public boolean addSubject(@RequestBody Subject subject) {
        return subjectService.addSubject(subject.getName());
    }

    @PutMapping(path = "api/v1/subjects/{oldName}/{newName}")
    public boolean updateSubject(@PathVariable("oldName") String oldName, @PathVariable("newName") String newName) {
        return subjectService.updateSubject(oldName, newName);
    }

    @GetMapping(path = "api/v1/subjects")
    public List<Subject> getSubjects() {
        return subjectService.getSubjects();
    }
}
