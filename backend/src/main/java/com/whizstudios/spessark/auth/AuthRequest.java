package com.whizstudios.spessark.auth;

public record AuthRequest(
        String email,
        String password
) {
}
