package com.whizstudios.spessark.teacher;

import java.util.List;
import java.util.Optional;

public interface TeacherDAO {
    boolean saveTeacher(Teacher teacher);
    Optional<Teacher> findTeacherById(long id);
    Optional<Teacher> findTeacherByName(String name);
    Teacher updateTeacher(Teacher oldTeacher, Teacher teacherUpdate);
    boolean deleteTeacherById(long id);
    boolean deleteTeacherByName(String name);
    List<Teacher> getAllTeachers();
}
