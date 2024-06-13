package com.whizstudios.spessark.student;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.whizstudios.spessark.Utils.ClassLevel;
import com.whizstudios.spessark.Utils.User;
import com.whizstudios.spessark.admin.Admin;
import com.whizstudios.spessark.score.Score;
import com.whizstudios.spessark.teacher.Teacher;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "student_no_unique", columnNames = "studentno")
},
indexes = {
        @Index(name = "student_name_index", columnList = "name", unique = false)
})
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @SequenceGenerator(name = "student_no_generator", sequenceName = "student_no_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_no_generator")
    private Long id;

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

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Score> scores = new ArrayList<>();

    public Student(User user, ClassLevel classLevel) {
        this.user = user;
        this.classLevel = classLevel;
    }

    public Student(Long id, User user, ClassLevel classLevel) {
        this.id = id;
        this.user = user;
        this.classLevel = classLevel;
    }
}
