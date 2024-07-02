package com.whizstudios.spessark.auth;

import com.whizstudios.spessark.admin.AdminService;
import com.whizstudios.spessark.teacher.TeacherService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SpessUserDetailsService implements UserDetailsService {

    private final AdminService adminService;
    private final TeacherService teacherService;

    public SpessUserDetailsService(AdminService adminService, TeacherService teacherService) {
        this.adminService = adminService;
        this.teacherService = teacherService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var adminUser = adminService.findAdminByName(username).get();
        // TODO: 7/2/2024 Ensure that the teacher user can also be fetched

       if (adminUser != null) {
                     return new org.springframework.security.core.userdetails
                   .User(adminUser.getUser().getName(), adminUser.getPassword(),
                   new ArrayList<>());
       } else {
           var teacherUser = teacherService.findTeacherByName(username).get();
           return new org.springframework.security.core.userdetails.
                   User(teacherUser.getUser().getName(), teacherUser.getPassword(),
                   new ArrayList<>());
       }
    }
}
