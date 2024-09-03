package com.whizstudios.spessark.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminRepository extends JpaRepository<Admin, Long> {
    boolean existsAdminByEmail(String email);

    @Modifying
    @Query("UPDATE Admin a SET a.password = :password WHERE a.email = :email")
    void resetPassword(@Param("password") String password,
                     @Param("email") String email
    );
}
