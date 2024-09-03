package com.whizstudios.spessark.teacher;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsTeacherByEmail(String email);

    @Modifying
    @Query("UPDATE Teacher t SET t.password = :password WHERE t.email = :email")
    void resetPassword(@Param("password") String password,
                     @Param("email") String email
    );
}
