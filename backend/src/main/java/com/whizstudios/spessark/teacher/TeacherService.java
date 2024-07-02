package com.whizstudios.spessark.teacher;

import com.whizstudios.spessark.security.SecurityConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TeacherService implements TeacherDAO{

    private final TeacherRepository teacherRepository;
    private final SecurityConfig config;

    public TeacherService(TeacherRepository teacherRepository, SecurityConfig config) {
        this.teacherRepository = teacherRepository;
        this.config = config;
    }

    @Override
    public boolean saveTeacher(Teacher teacher) {
        var encodedPassword = config.passwordEncoder().encode(teacher.getPassword());
        teacher.setPassword(encodedPassword);
        teacherRepository.save(teacher);
        var savedTeacher = this.findTeacherByName(teacher.getUser().getName());
        return savedTeacher.isPresent();
    }

    @Override
    public Optional<Teacher> findTeacherById(long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Optional<Teacher> findTeacherByName(String name) {
        var teacherId = getId(name);
        return this.findTeacherById(teacherId);
    }

    @Override
    public Teacher updateTeacher(Teacher orgTeacher, Teacher teacherUpdate) {
        var oldTeacher = teacherRepository.findAll().stream().filter(
                teacher -> Objects.equals(teacher.getUser().getName(), orgTeacher.getUser().getName())
                && Objects.equals(teacher.getUser().getGender().name(), orgTeacher.getUser().getGender().name())).findFirst().get();
        teacherUpdate.setId(oldTeacher.getId());
        teacherUpdate.setPassword(oldTeacher.getPassword());
        teacherRepository.save(teacherUpdate);

        return this.findTeacherById(teacherUpdate.getId()).orElseThrow();
    }

    @Override
    public boolean deleteTeacherById(long id) {
        teacherRepository.deleteById(id);
        return !teacherRepository.existsById(id);
    }

    @Override
    public boolean deleteTeacherByName(String name) {
        var deletableTeacherId = getId(name);
        return this.deleteTeacherById(deletableTeacherId);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    private long getId(String name) {
        return this.getAllTeachers().stream().filter(teacher -> teacher.getUser().getName().matches(name)).findFirst().orElseThrow().getId();
    }
}
