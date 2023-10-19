package com.bidi.users.controller;

import com.bidi.users.util.MessageResponse;
import com.bidi.users.util.UserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {
    private static final Logger logger = LoggerFactory.getLogger(ExceptionController.class);

    @ExceptionHandler(UserException.class)
    public ResponseEntity<MessageResponse> handleUserException(UserException e){
        logger.error("Error: {}", e.getReason());
        return new ResponseEntity<>(
                new MessageResponse(e.getCode(), e.getReason()),
                e.getStatusCode()
        );
    }

}
