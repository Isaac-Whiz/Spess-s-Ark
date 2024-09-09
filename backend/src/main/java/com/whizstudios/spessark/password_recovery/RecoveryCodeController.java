package com.whizstudios.spessark.password_recovery;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/password")
public class RecoveryCodeController {

    private final PasswordRecoveryCodeGenerator generator;

    public RecoveryCodeController(PasswordRecoveryCodeGenerator generator) {
        this.generator = generator;
    }

    @PostMapping(path = "generateCode/{emailAddress}")
    public boolean getCode(@PathVariable("emailAddress")  String emailAddress) {
        return generator.validateEmailAndSendCode(emailAddress);
    }

    @PostMapping(path = "validateCode/{code}")
    public boolean validCode(@PathVariable("code") String code) {
        return generator.isCodeValid(code);
    }

    @PutMapping(path = "resetPassword/{email}/{password}")
    public void resetPassword(@PathVariable("email") String email,
                                 @PathVariable("password") String password) {
        generator.resetPassword(email, password);
    }

}
