package com.bidi.users.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;


@Setter
@Getter
@RequiredArgsConstructor
public class UserException extends RuntimeException {
    private final HttpStatus statusCode;
    private final String code;
    private final String reason;

}
