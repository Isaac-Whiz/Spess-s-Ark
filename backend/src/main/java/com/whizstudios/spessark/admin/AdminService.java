package com.whizstudios.spessark.admin;

import com.whizstudios.spessark.security.SecurityConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AdminService implements AdminDAO {
    private final AdminRepository adminRepository;
    private final SecurityConfig config;

    public AdminService(AdminRepository adminRepository, SecurityConfig config) {
        this.adminRepository = adminRepository;
        this.config = config;
    }

    @Override
    public Admin saveAdmin(Admin admin) {
        String encodedPassword = config.passwordEncoder().encode(admin.getPassword());
        var adminName = admin.getUser().getName();
        var adminGender = admin.getUser().getGender();
        admin.setPassword(encodedPassword);
        adminRepository.save(admin);

        var savedAdmin = adminRepository.findAll().stream().findFirst().orElseThrow();

        var result =  Objects.equals(savedAdmin.getUser().getName(), adminName)
                && Objects.equals(savedAdmin.getUser().getGender(), adminGender);
        if (result) {
            return savedAdmin;
        }
        return new Admin();
    }

    @Override
    public Admin updateAdmin(Admin oldAdmin, Admin adminUpdate) {
        var retrievedAdmin = adminRepository.findAll().stream().filter(
                admin -> admin.getUser().getName().equals(oldAdmin.getUser().getName()) &&
                        admin.getUser().getGender().equals(oldAdmin.getUser().getGender())).findFirst().orElseThrow();
        adminUpdate.setId(retrievedAdmin.getId());
        adminUpdate.setPassword(retrievedAdmin.getPassword());
        adminRepository.save(adminUpdate);

        return this.findAdminByName(adminUpdate.getUser().getName()).orElseThrow();
    }

    @Override
    public boolean deleteAdminById(long id) {
        adminRepository.deleteById(id);
        return !adminRepository.existsById(id);
    }

    @Override
    public boolean deleteAdminByName(String name) {
        var deletableAdminId = getId(name);
        return this.deleteAdminById(deletableAdminId);
    }

    @Override
    public Optional<Admin> findAdminById(long id) {
        return adminRepository.findById(id);
    }

    @Override
    public Optional<Admin> findAdminByName(String name) {
        var adminId = getId(name);
        return this.findAdminById(adminId);
    }

    private long getId(String name) {
        return this.getAllAdmins().stream().filter(
                admin -> admin.getUser().getName().matches(name)
        ).findFirst().orElseThrow().getId();
    }

    @Override
    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

}
