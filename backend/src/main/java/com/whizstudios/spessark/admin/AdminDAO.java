package com.whizstudios.spessark.admin;

import java.util.List;
import java.util.Optional;

public interface AdminDAO  {
    Admin saveAdmin(Admin admin);
    Optional<Admin> findAdminById(long id);
    Optional<Admin> findAdminByEmail(String email);
    Admin updateAdmin(Admin oldAdmin, Admin adminUpdate);
    boolean deleteAdminById(long id);
    boolean deleteAdminByName(String name);
    List<Admin> getAllAdmins();
}
