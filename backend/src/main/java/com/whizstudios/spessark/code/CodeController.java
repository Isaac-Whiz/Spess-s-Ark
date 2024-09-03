package com.whizstudios.spessark.code;

import org.springframework.web.bind.annotation.*;

@RestController
public class CodeController {

    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(path = "api/v1/generate/admin")
    public String generateAdminCode() {
        return codeService.generateAdminCode();
    }

    @GetMapping(path = "api/v1/generate/user")
    public String generateUserCode() {
        return codeService.generateUserCode();
    }

    @PutMapping(path = "api/v1/validate/{code}")
    public boolean validateCode(@PathVariable("code") String code) {
        System.out.println("Executed");
        System.out.println("Code " + code);
        var isUsed = codeService.validateCode(code);
        System.out.println("Is used " + isUsed);
        if (!isUsed) {
            codeService.updateCode(code);
            return true;
        } else {
            return false;
        }
    }
}
