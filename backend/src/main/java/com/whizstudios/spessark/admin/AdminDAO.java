package com.whizstudios.spessark.admin;

import java.util.List;
import java.util.Optional;

public interface AdminDAO  {
    boolean saveAdmin(Admin admin);
    Optional<Admin> findAdminById(long id);
    Optional<Admin> findAdminByName(String name);
    Admin updateAdmin(Admin adminUpdate);
    boolean deleteAdminById(long id);
    boolean deleteAdminByName(String name);
    List<Admin> getAllAdmins();
}
