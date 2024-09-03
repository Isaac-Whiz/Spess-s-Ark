package com.whizstudios.spessark.password_recovery;

import org.springframework.web.bind.annotation.*;

@RestController
public class RecoveryCodeController {

    private final PasswordRecoveryCodeGenerator generator;

    public RecoveryCodeController(PasswordRecoveryCodeGenerator generator) {
        this.generator = generator;
    }

    @PostMapping(path = "api/v1/generateCode/{emailAddress}")
    public boolean getCode(@PathVariable("emailAddress")  String emailAddress) {
        return generator.validateEmailAndSendCode(emailAddress);
    }

    @PostMapping(path = "api/v1/validateCode/{code}")
    public boolean validCode(@PathVariable("code") String code) {
        return generator.isCodeValid(code);
    }

    @PutMapping(path = "api/v1/resetPassword/{email}/{password}")
    public void resetPassword(@PathVariable("email") String email,
                                 @PathVariable("password") String password) {
        generator.resetPassword(email, password);
    }

}
