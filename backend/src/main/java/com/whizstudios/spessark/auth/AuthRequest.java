package com.whizstudios.spessark.auth;

public record AuthRequest(
        String username,
        String password
) {
}
