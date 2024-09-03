package com.whizstudios.spessark.code;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Code {

    @Id
    @SequenceGenerator(name = "code_id_generator", sequenceName = "code_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "code_id_generator")
    private Long id;

    @Column(name = "code", columnDefinition = "TEXT", unique = true, nullable = false)
    private String code;

    @Column(name = "user_type", columnDefinition = "TEXT", nullable = false)
    private String userType;

    @Column(name = "used", nullable = false)
    private boolean used;

    public Code(String code, String userType, boolean used) {
        this.code = code;
        this.userType = userType;
        this.used = used;
    }
}
