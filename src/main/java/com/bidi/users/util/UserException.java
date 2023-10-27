package com.bidi.users.util;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class UserException extends RuntimeException {
    private final HttpStatus statusCode;
    private final String code;
    private final String reason;

}
