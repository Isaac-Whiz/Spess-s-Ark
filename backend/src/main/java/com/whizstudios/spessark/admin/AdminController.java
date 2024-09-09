package com.whizstudios.spessark.admin;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/admin")
public class AdminController {

    private final AdminService adminService;

    @Value("${spring.mail.username}")
    private String email;

    @Value("${admin-name}")
    String adminName;

    @Value("${admin-password}")
    String adminPassword;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(path = "getAdmins")
    List<Admin> getAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping(path = "tick")
    String getAdmin() {
        return ("Admin: " + adminService.findAdminByEmail(email) + " Original data, name: "
                + adminName + " Password: " + adminPassword + " Email: " + email);
    }

    @GetMapping(path = "findAdminById/{id}")
    Admin getAdminById(@PathVariable("id") long id) {
        return adminService.findAdminById(id).orElseThrow();
    }
 
    @GetMapping(path = "findByName/{name}")
    Admin getAdminByName(@PathVariable("name") String name) {
        return adminService.findAdminByEmail(name).orElseThrow();
    }

    @PostMapping("registerAdmin")
    Admin registerAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    @PutMapping(path = "update")
    Admin updateAdmin(@RequestBody AdminUpdateRequest request) {
        return adminService.updateAdmin(request.getOldAdmin(), request.getUpdate());
    }

    @DeleteMapping(path = "delete/{name}")
    boolean deleteAdminByName(@PathVariable("name") String name) {
        return adminService.deleteAdminByName(name);
    }


}
