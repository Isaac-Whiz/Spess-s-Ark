package com.whizstudios.spessark.exception;

import java.time.LocalDateTime;

public record FormattedError (
        String path,
        String message,
        Integer statusCode,
        LocalDateTime dateTime
) {
}
