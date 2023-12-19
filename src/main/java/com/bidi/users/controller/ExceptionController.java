package com.bidi.users.controller;

import com.bidi.users.util.MessageResponse;
import com.bidi.users.util.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ExceptionController {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<MessageResponse> handleUserException(UserException e){
        log.error("Error: {}", e.getReason());
        return new ResponseEntity<>(
                new MessageResponse(e.getCode(), e.getReason()),
                e.getStatusCode()
        );
    }

}
