package com.bidi.users.controller;

import com.bidi.users.dto.auth.request.AuthUserRequest;
import com.bidi.users.dto.auth.response.AuthUserResponse;
import com.bidi.users.dto.newuser.CreateUserRequest;
import com.bidi.users.service.AuthUserService;
import com.bidi.users.service.CreateUserService;
import com.bidi.users.util.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
@CrossOrigin("*")
public class PublicController {

    private final AuthUserService authUserService;
    private final CreateUserService createUserService;

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public AuthUserResponse authUser(@RequestBody AuthUserRequest authUserRequest) {
        return authUserService.authUser(authUserRequest);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createUser(@RequestBody CreateUserRequest createUserRequest) {
        return createUserService.createUser(createUserRequest);
    }
}
