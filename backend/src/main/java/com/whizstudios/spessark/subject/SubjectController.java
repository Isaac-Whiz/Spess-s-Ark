package com.whizstudios.spessark.subject;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/subject")
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping(path = "addSubject")
    public boolean addSubject(@RequestBody Subject subject) {
        return subjectService.addSubject(subject.getName());
    }

    @PutMapping(path = "subjects/{oldName}/{newName}")
    public boolean updateSubject(@PathVariable("oldName") String oldName, @PathVariable("newName") String newName) {
        return subjectService.updateSubject(oldName, newName);
    }

    @GetMapping(path = "subjects")
    public List<Subject> getSubjects() {
        return subjectService.getSubjects();
    }
}
