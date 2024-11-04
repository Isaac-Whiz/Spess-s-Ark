package com.whizstudios.spessark.auth;

import com.whizstudios.spessark.admin.Admin;
import com.whizstudios.spessark.admin.AdminService;
import com.whizstudios.spessark.teacher.Teacher;
import com.whizstudios.spessark.teacher.TeacherService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SpessUserDetailsService implements UserDetailsService {

    private final AdminService adminService;
    private final TeacherService teacherService;

    public SpessUserDetailsService(AdminService adminService, TeacherService teacherService) {
        this.adminService = adminService;
        this.teacherService = teacherService;
    }

@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    Optional<Admin> adminUserOpt = Optional.empty();
    try {
        adminUserOpt = adminService.findAdminByEmail(email);
    } catch (Exception ignored) {}

    if (adminUserOpt.isPresent()) {
        Admin adminUser = adminUserOpt.get();
        return new org.springframework.security.core.userdetails.User(
                adminUser.getEmail(),
                adminUser.getPassword(),
                new ArrayList<>()
        );
    }

    Optional<Teacher> teacherUserOpt = Optional.empty();
    try {
        teacherUserOpt = teacherService.findTeacherByEmail(email);
    } catch (Exception ignored) {}

    if (teacherUserOpt.isPresent()) {
        Teacher teacherUser = teacherUserOpt.get();
        return new org.springframework.security.core.userdetails.User(
                teacherUser.getEmail(),
                teacherUser.getPassword(),
                new ArrayList<>()
        );
    }
    throw new UsernameNotFoundException("User not found.");
}

}
