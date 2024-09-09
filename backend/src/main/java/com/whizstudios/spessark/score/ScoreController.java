package com.whizstudios.spessark.score;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/score")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping(path = "{t1}/{t2}/{t3}/{name}/{subject}/{dateTime}")
    public boolean addScore(@PathVariable("t1") Double t1,
                            @PathVariable("t2") Double t2,
                            @PathVariable("t3") Double t3,
                            @PathVariable("name") String name,
                            @PathVariable("dateTime") LocalDateTime dateTime,
                            @PathVariable("subject") String subject) {
        return scoreService.addScore(name, subject, dateTime, t1, t2, t3);
    }

    @PutMapping(path = "scores/update")
    public void updateScore(@RequestBody ScoreUpdateRequest request) {
        scoreService.updateScore(request.getOldScore(), request.getUpdate());
    }

    @DeleteMapping(path ="delete/{subject}")
    public void deleteScore(@PathVariable("subject") String subject) {
        scoreService.deleteScore(subject);
    }
}
