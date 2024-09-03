package com.whizstudios.spessark.student;

import com.whizstudios.spessark.utils.Gender;
import com.whizstudios.spessark.utils.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    boolean existsStudentByClassLevel_NameAndClassLevel_StreamAndUser_GenderAndUser_Name(
            String classLevel_name,
            Stream classLevel_stream,
            Gender user_gender,
            String user_name);

    Optional<Student> findByUser_Name(String name);

//    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN TRUE ELSE FALSE END FROM Student s WHERE s.classLevel.name = :classLevel_name " +
//            "AND s.classLevel.stream = :classLevel_stream AND s.user.gender = :user_gender AND s.user.name = :user_name")
//    boolean isAlreadyAvailableStudent(
//            String classLevel_name,
//            Stream classLevel_stream,
//            Gender user_gender,
//            String user_name);
}
