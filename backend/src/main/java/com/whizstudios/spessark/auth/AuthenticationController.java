package com.whizstudios.spessark.auth;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        AuthResponse response = service.login(request);

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, response.jwt())
                .body(response);
    }
}
