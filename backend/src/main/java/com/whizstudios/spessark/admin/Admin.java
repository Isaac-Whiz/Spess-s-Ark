package com.whizstudios.spessark.admin;

import com.whizstudios.spessark.Utils.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "admin_password_unique", columnNames = "password")
},
indexes = {
        @Index(name = "admin_name_index", columnList = "name")
})
public class Admin {

    @Id
    @SequenceGenerator(name = "admin_id_generator", sequenceName = "admin_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admin_id_generator")
    private long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "name", nullable = false, columnDefinition = "TEXT")),
            @AttributeOverride(name = "gender", column = @Column(name = "gender", nullable = false, columnDefinition = "TEXT")),
            @AttributeOverride(name = "dateTime", column = @Column(name = "date_created", nullable = false, columnDefinition = "TEXT"))
    })
    private User user;

    @Column(name = "password", nullable = false, columnDefinition = "TEXT")
    private String password;



}
