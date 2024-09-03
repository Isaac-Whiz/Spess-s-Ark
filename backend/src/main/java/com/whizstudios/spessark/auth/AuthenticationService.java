package com.whizstudios.spessark.auth;

import com.whizstudios.spessark.jwt.JwtUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager manager;
    private final JwtUtil util;

    public AuthenticationService(AuthenticationManager manager, JwtUtil util) {
        this.manager = manager;
        this.util = util;
    }

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.email(),
                        request.password()
                ));

        if (authentication.isAuthenticated()) {
            return new AuthResponse(util.generateToken(request.email()));
        }
        else {
            throw new RuntimeException();
        }
    }
}
