package com.whizstudios.spessark.teacher;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService implements TeacherDAO{

    private final TeacherRepository teacherRepository;

    public TeacherService(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public boolean saveTeacher(Teacher teacher) {
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
    public Teacher updateTeacher(Teacher teacherUpdate) {
        var oldTeacher = teacherRepository.findById(teacherUpdate.getId());

        if (oldTeacher.isPresent()) {
            if (oldTeacher.get().getId() == teacherUpdate.getId()) {
                teacherRepository.save(teacherUpdate);
            }
        }
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
