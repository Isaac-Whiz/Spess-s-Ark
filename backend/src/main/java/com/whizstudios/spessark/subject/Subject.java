package com.whizstudios.spessark.subject;

import com.whizstudios.spessark.score.Score;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "subject_name_unique", columnNames = "name")
})
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @SequenceGenerator(name = "subject_id_generator", sequenceName = "subject_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_id_sequence")
    private Long id;

    @Column(name = "name", columnDefinition = "TEXT")
    private String name;

    public Subject(String name) {
        this.name = name;
    }
}
