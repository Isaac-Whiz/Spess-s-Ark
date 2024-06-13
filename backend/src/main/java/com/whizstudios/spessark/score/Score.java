package com.whizstudios.spessark.score;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.whizstudios.spessark.student.Student;
import com.whizstudios.spessark.subject.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "score")
@AllArgsConstructor
@NoArgsConstructor
public class Score {

    @Id
    @SequenceGenerator(name = "score_id_generator", sequenceName = "score_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "score_id_generator")
    private Long id;

    @Column(name = "scores", columnDefinition = "NUMERIC", nullable = false)
    private Integer scores;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonBackReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    public Score(Integer scores, Student student, Subject subject) {
        this.scores = scores;
        this.student = student;
        this.subject = subject;
    }
}
