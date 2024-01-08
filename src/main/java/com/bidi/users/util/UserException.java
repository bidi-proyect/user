package com.bidi.users.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;


@Setter
@Getter
@RequiredArgsConstructor
public class UserException extends RuntimeException {
    private final HttpStatusCode statusCode;
    private final String code;
    private final String reason;

}
