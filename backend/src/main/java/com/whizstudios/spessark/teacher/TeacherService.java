package com.whizstudios.spessark.teacher;

import com.whizstudios.spessark.security.SecurityConfig;
import com.whizstudios.spessark.utils.User;
import jakarta.transaction.Transactional;
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
    @Transactional
    public boolean saveTeacher(Teacher teacher) {
        var encodedPassword = config.passwordEncoder().encode(teacher.getPassword());
        var completeTeacher = new Teacher(new User(teacher.getUser().getName(), teacher.getUser().getGender(), teacher.getUser().getDateTime()),
                teacher.getEmail(), encodedPassword, teacher.getSubjectTaught());
        teacherRepository.save(completeTeacher);
        var savedTeacher = this.findTeacherByEmail(teacher.getEmail());
        return savedTeacher.isPresent();
    }

    @Override
    public Optional<Teacher> findTeacherById(long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public Optional<Teacher> findTeacherTransByEmail(TeacherTransient teacherTrans) {
        return teacherRepository.findAll().stream().filter(teacher ->
            Objects.equals(teacher.getEmail(), teacherTrans.getEmail()) &&
                    Objects.equals(teacher.getSubjectTaught(), teacherTrans.getSubjectTaught())
        ).findFirst();
    }

    @Override
    public Optional<Teacher> findTeacherByEmail(String email) {
        var teacherId = getId(email);
        return this.findTeacherById(teacherId);
    }

    @Override
    @Transactional
    public Teacher updateTeacher(Teacher orgTeacher, Teacher teacherUpdate) {
        var oldTeacher = teacherRepository.findAll().stream().filter(
                teacher -> Objects.equals(teacher.getUser().getName(), orgTeacher.getUser().getName())
                        && Objects.equals(teacher.getEmail(), orgTeacher.getEmail())
                && Objects.equals(teacher.getUser().getGender().name(),
                        orgTeacher.getUser().getGender().name())).findFirst().get();
        teacherUpdate.setId(oldTeacher.getId());
        teacherUpdate.setPassword(oldTeacher.getPassword());
        teacherRepository.save(teacherUpdate);

        return this.findTeacherById(teacherUpdate.getId()).orElseThrow();
    }

    @Override
    @Transactional
    public boolean deleteTeacherById(long id) {
        teacherRepository.deleteById(id);
        return !teacherRepository.existsById(id);
    }

    @Override
    @Transactional
    public boolean deleteTeacherByName(String name) {
        var deletableTeacherId = getId(name);
        return this.deleteTeacherById(deletableTeacherId);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    private long getId(String email) {
        return this.getAllTeachers().stream().
                filter(teacher ->
                        teacher.getEmail().matches(email)).findFirst().orElseThrow().getId();
    }
}
