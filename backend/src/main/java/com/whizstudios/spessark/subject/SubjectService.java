package com.whizstudios.spessark.subject;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SubjectService implements SubjectDAO {

    private final  SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    @Override
    @Transactional
    public boolean addSubject(String name) {
        var subject = new Subject(name);

        if (!subjectRepository.existsByName(name)) {
            subjectRepository.save(subject);
        }
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
    @Transactional
    public boolean updateSubject(String oldName, String newName) {
        var retrievedOld = subjectRepository.findAll().stream().filter( subject -> Objects.equals(subject.getName(), oldName)).findFirst().get();
        retrievedOld.setName(newName);
        subjectRepository.save(retrievedOld);

        return findSubjectByName(newName) != null;
    }
}
