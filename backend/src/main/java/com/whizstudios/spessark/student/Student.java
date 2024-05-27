package com.whizstudios.spessark.student;

import com.whizstudios.spessark.Utils.ClassLevel;
import com.whizstudios.spessark.Utils.Subject;
import com.whizstudios.spessark.Utils.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "student_no_unique", columnNames = "studentno")
},
indexes = {
        @Index(name = "student_name_index", columnList = "name", unique = false)
})
public class Student {
    @Id
    @SequenceGenerator(name = "student_no_generator", sequenceName = "student_no_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_no_generator")
    private long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name", nullable = false, columnDefinition = "TEXT")),
            @AttributeOverride(name = "gender", column = @Column(name = "gender", nullable = false, columnDefinition = "TEXT")),
            @AttributeOverride(name = "dateTime", column = @Column(name = "date_created", nullable = false, columnDefinition = "TEXT"))
    })
    private User user;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "class_name", nullable = false, columnDefinition = "TEXT")),
            @AttributeOverride(name = "stream", column = @Column(name = "stream", nullable = false, columnDefinition = "TEXT"))
    })
    private ClassLevel classLevel;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "subject_name", nullable = false, columnDefinition = "TEXT")),
            @AttributeOverride(name = "score", column = @Column(name = "score", nullable = false, columnDefinition = "NUMERIC"))
    })
    private Subject subject;

}
