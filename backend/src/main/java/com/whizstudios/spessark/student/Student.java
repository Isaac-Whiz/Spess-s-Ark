package com.whizstudios.spessark.student;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.whizstudios.spessark.utils.ClassLevel;
import com.whizstudios.spessark.utils.User;
import com.whizstudios.spessark.score.Score;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(
indexes = {
        @Index(name = "student_name_index", columnList = "name")
})
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "scores")
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

    @OneToMany(mappedBy = "student", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.EAGER)
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
