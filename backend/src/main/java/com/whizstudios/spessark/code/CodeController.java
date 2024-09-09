package com.whizstudios.spessark.code;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/code")
public class CodeController {

    private final CodeService codeService;

    public CodeController(CodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping(path = "generate/admin")
    public String generateAdminCode() {
        return codeService.generateAdminCode();
    }

    @GetMapping(path = "generate/user")
    public String generateUserCode() {
        return codeService.generateUserCode();
    }

    @PutMapping(path = "validate/{code}")
    public boolean validateCode(@PathVariable("code") String code) {
        var isUsed = codeService.validateCode(code);
        if (!isUsed) {
            codeService.updateCode(code);
            return true;
        } else {
            return false;
        }
    }
}
