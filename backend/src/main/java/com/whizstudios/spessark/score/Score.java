package com.whizstudios.spessark.score;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.whizstudios.spessark.student.Student;
import com.whizstudios.spessark.subject.Subject;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Table(name = "score")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "student")
public class Score {

    @Id
    @SequenceGenerator(name = "score_id_generator", sequenceName = "score_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "score_id_generator")
    private Long id;

    @Column(name = "t1", columnDefinition = "NUMERIC", nullable = false)
    private double t1;

    @Column(name = "t2", columnDefinition = "NUMERIC", nullable = false)
    private double t2;

    @Column(name = "t3", columnDefinition = "NUMERIC", nullable = false)
    private double t3;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    @JsonBackReference
    private Student student;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;


    public Score(double t1,
                 double t2,
                 double t3,
                 Student student,
                 Subject subject) {
        this.t1 = t1;
        this.t2 = t2;
        this.t3 = t3;
        this.student = student;
        this.subject = subject;
    }

}
