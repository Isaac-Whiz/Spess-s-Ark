package com.whizstudios.spessark.subject;

import java.util.List;

public interface SubjectDAO {
    boolean addSubject(String name);
    boolean updateSubject(String oldName, String newName);
    Subject findSubjectByName(String name);
    List<Subject> getSubjects();
}
