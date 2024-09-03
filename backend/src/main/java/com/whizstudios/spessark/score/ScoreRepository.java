package com.whizstudios.spessark.score;

import com.whizstudios.spessark.student.Student;
import com.whizstudios.spessark.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScoreRepository extends JpaRepository<Score, Long> {

    @Query("SELECT s FROM Score s WHERE s.subject.name = :subjectName")
    Optional<Score> findBySubjectName(@Param("subjectName") String subjectName);

    @Modifying
    @Query(value = "INSERT INTO Score (id, t1, t2, t3, student_id, subject_id) VALUES (nextval('score_id_sequence'), :t1, :t2, :t3, :student_id, :subject_id)", nativeQuery = true)
    void customSave( @Param("student_id") Long student_id,
                    @Param("subject_id") Long subject_id,
                    @Param("t1") Double t1,
                    @Param("t2") Double t2,
                    @Param("t3") Double t3);

    boolean existsByStudentIdAndSubjectId(Long studentId, Long subjectId);


    @Modifying
    @Query("UPDATE Score s SET s.student.id = :studentUpdateId, s.t1 = :updateT1, s.t2 = :updateT2, s.t3 = :updateT3 WHERE s.id = :scoreId")
    void updateScore(@Param("scoreId") Long scoreId,
                     @Param("studentUpdateId") Long studentUpdateId,
                     @Param("updateT1") Double updateT1,
                     @Param("updateT2") Double updateT2,
                     @Param("updateT3") Double updateT3
    );

    @Modifying
    @Query("UPDATE Score s SET s.t1 = :update_t1, s.t2 = :update_t2, s.t3 = :update_t3 WHERE s.student.id = :student_id AND s.subject.id = :subject_id")
    void updateScoreByStudent_IdAndSubject_Id(@Param("student_id") Long student_id,
                                              @Param("subject_id") Long subject_id,
                                              @Param("update_t1") Double update_t1,
                                              @Param("update_t2") Double update_t2,
                                              @Param("update_t3") Double update_t3
    );

    @Modifying
    @Query("DELETE FROM Score s WHERE s.id = :id")
    void deleteScoreById(@Param("id") Long id);

    @Modifying
    @Query("DELETE FROM Score s WHERE s.subject.id = :subjectId")
    void deleteScoreBySubjectId(@Param("subjectId") Long subjectId);

    @Query("SELECT s FROM Score s WHERE s.subject.name = :subjectName AND s.student.id = :studentId AND s.t1 = :t1 AND s.t2 = :t2 AND s.t3 = :t3")
    Optional<Score> findScore(@Param("subjectName") String subjectName,
                              @Param("studentId") Long studentId,
                              @Param("t1") Double t1,
                              @Param("t2") Double t2,
                              @Param("t3") Double t3);

    @Query("SELECT s FROM Score s WHERE s.subject.id = :subjectId AND s.student.id = :studentId AND s.t1 = :t1 AND s.t2 = :t2 AND s.t3 = :t3")
    Optional<Score> findScore(@Param("subjectId") Long subjectId,
                              @Param("studentId") Long studentId,
                              @Param("t1") Double t1,
                              @Param("t2") Double t2,
                              @Param("t3") Double t3);

    @Query("SELECT s FROM Score s WHERE s.subject.id = :subjectId AND s.student.id = :studentId")
    Optional<Score> findScore(@Param("subjectId") Long subjectId, @Param("studentId") Long studentId);

}