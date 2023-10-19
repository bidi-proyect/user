package com.bidi.users.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
@Setter
public class UserException extends RuntimeException {
    private final HttpStatus statusCode;
    private final String code;
    private final String reason;
}
