package com.whizstudios.spessark.code;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class CodeGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    public static Code generateAdminCode() {
        String adminPrefix = "ADMIN-";
        return new Code(adminPrefix + generateUniqueCode(), adminPrefix, false);
    }

    public static Code generateUserCode() {
        String userPrefix = "USER-";
        return new Code(userPrefix + generateUniqueCode(), userPrefix, false);
    }

    private static String generateUniqueCode() {
        var length = 6;
        StringBuilder uniqueCode = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(ALPHANUMERIC.length());
            uniqueCode.append(ALPHANUMERIC.charAt(randomIndex));
        }
        return uniqueCode.toString();
    }
}
