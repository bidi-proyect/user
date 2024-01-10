package com.bidi.users.controller;

import com.bidi.users.dto.response.MessageResponse;
import com.bidi.users.util.UserException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    @ExceptionHandler(UserException.class)
    public ResponseEntity<MessageResponse> handleUserException(UserException e){
        return new ResponseEntity<>(
                new MessageResponse(e.getCode(), e.getReason()),
                e.getStatusCode()
        );
    }
}
