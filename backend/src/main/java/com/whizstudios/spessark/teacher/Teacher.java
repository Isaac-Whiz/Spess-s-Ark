package com.whizstudios.spessark.teacher;

import com.whizstudios.spessark.utils.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "teacher_password_unique", columnNames = "password")
},
        indexes = {
                @Index(name = "teacher_name_index", columnList = "name")
        })
public class Teacher {

    @Id
    @SequenceGenerator(name = "teacher_id_generator", sequenceName = "teacher_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_id_generator")
    private long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name", nullable = false, columnDefinition = "TEXT")),
            @AttributeOverride(name = "gender", column = @Column(name = "gender", nullable = false, columnDefinition = "TEXT")),
            @AttributeOverride(name = "dateTime", column = @Column(name = "date_created", nullable = false, columnDefinition = "TEXT"))
    })
    private User user;

    @Email
    @Column(name = "email", nullable = false, columnDefinition = "TEXT")
    private String email;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(name = "subject_taught", nullable = false, columnDefinition = "TEXT")
    private String subjectTaught;

    public Teacher(User user, String email, String password, String subjectTaught) {
        this.user = user;
        this.email = email;
        this.password = password;
        this.subjectTaught = subjectTaught;
    }

    public Teacher(User user, String password) {
        this.user = user;
        this.password = password;
    }
}
