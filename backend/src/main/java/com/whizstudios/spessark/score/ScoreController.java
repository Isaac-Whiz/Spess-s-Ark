package com.whizstudios.spessark.score;

import org.springframework.web.bind.annotation.*;

@RestController
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping(path = "api/v1/score/{studentName}/{subjectName}/{scores}")
    public boolean addScore(@PathVariable("studentName") String studentName, @PathVariable("subjectName") String subjectName, @PathVariable("scores") Integer scores) {
        return scoreService.addScore(studentName, subjectName, scores);
    }

    @PutMapping(path = "api/v1/scores/update")
    public void updateScore(@RequestBody ScoreUpdateRequest request) {
        scoreService.updateScore(request.getOldScore(), request.getUpdate());
    }
}
