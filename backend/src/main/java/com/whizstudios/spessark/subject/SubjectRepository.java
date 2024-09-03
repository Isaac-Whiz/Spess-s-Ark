package com.whizstudios.spessark.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

    List<Subject> findSubjectsByName(String name);

    boolean existsByName(String name);

    Optional<Subject> findSubjectByName(String name);
}
