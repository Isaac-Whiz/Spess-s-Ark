package com.whizstudios.spessark.subject;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService implements SubjectDAO {

    private final  SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    public boolean addSubject(String name) {
        var subject = new Subject(name);
        subjectRepository.save(subject);
        var saved = findSubjectByName(name);

        return saved.getName().equals(name);
    }

    public Subject findSubjectByName(String name) {
        return getSubjects()
                .stream()
                .filter(sub -> sub.getName().equals(name))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @Override
    public boolean updateSubject(String oldName, String newName) {
        var subject = findSubjectByName(oldName);
        subject.setName(newName);
        subjectRepository.save(subject);

        return findSubjectByName(newName) != null;
    }
}
