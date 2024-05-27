package com.whizstudios.spessark.admin;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService implements AdminDAO{
    private final AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public boolean saveAdmin(Admin admin) {
        adminRepository.save(admin);
        var savedAdmin = this.findAdminByName(admin.getUser().getName());

        return savedAdmin.isPresent();
    }

    @Override
    public Admin updateAdmin(Admin adminUpdate) {
        var oldAdmin = adminRepository.findById(adminUpdate.getId());

        if (oldAdmin.isPresent()) {
            if (oldAdmin.get().getId() == adminUpdate.getId()) {
                adminRepository.save(adminUpdate);
            }
        }
        return this.findAdminById(adminUpdate.getId()).orElseThrow();
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
