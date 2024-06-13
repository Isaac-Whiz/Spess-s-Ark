package com.whizstudios.spessark.student;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class StudentUpdateRequest {
    private Student oldStudent;
    private Student update;
}
