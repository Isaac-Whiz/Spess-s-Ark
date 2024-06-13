package com.whizstudios.spessark.admin;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping(path = "api/v1/admins")
    List<Admin> getAdmins() {
        return adminService.getAllAdmins();
    }

    @GetMapping(path = "api/v1/admins/findById/{id}")
    Admin getAdminById(@PathVariable("id") long id) {
        return adminService.findAdminById(id).orElseThrow();
    }

    @GetMapping(path = "api/v1/admins/findByName/{name}")
    Admin getAdminByName(@PathVariable("name") String name) {
        return adminService.findAdminByName(name).orElseThrow();
    }

    @PostMapping("api/v1/admin")
    Admin registerAdmin(@RequestBody Admin admin) {
        return adminService.saveAdmin(admin);
    }

    @PutMapping(path = "api/v1/admins/update")
    Admin updateAdmin(@RequestBody AdminUpdateRequest request) {
        return adminService.updateAdmin(request.getOldAdmin(), request.getUpdate());
    }

    @DeleteMapping(path = "api/v1/admins/delete/{name}")
    boolean deleteAdminByName(@PathVariable("name") String name) {
        return adminService.deleteAdminByName(name);
    }


}
