package com.whizstudios.spessark.score;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ScoreUpdateRequest {
    private ScoreTransient oldScore;
    private ScoreTransient update;
}
