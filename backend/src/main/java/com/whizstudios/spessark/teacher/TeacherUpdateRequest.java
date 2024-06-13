package com.whizstudios.spessark.teacher;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class TeacherUpdateRequest {
    private Teacher oldTeacher;
    private Teacher update;
}
