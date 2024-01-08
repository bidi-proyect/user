package com.bidi.users.controller;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.bidi.users.dto.request.AuthUserRequest;
import com.bidi.users.dto.request.UserRequest;
import com.bidi.users.dto.request.UpdatePasswordRequest;
import com.bidi.users.dto.response.UserResponse;
import com.bidi.users.dto.response.AuthUserResponse;
import com.bidi.users.service.AuthUserService;
import com.bidi.users.service.CreateUserService;
import com.bidi.users.service.GetAllUsersService;
import com.bidi.users.service.GetUserByIdService;
import com.bidi.users.service.LogOutService;
import com.bidi.users.service.UpdatePasswordService;
import com.bidi.users.service.UpdateUserService;
import com.bidi.users.dto.response.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final AuthUserService authUserService;
    private final CreateUserService createUserService;
    private final GetUserByIdService getUserByIdService;
    private final GetAllUsersService getAllUsersService;
    private final UpdateUserService updateUserService;
    private final UpdatePasswordService updatePasswordService;
    private final LogOutService logOutService;
    private final AmazonSNSClient amazonSNSClient;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllUsers(@RequestHeader("Authorization") String token) {

        return this.getAllUsersService.getAllUsers(token);
    }

    @GetMapping("/search/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(
            @RequestHeader("Authorization") String token,
            @PathVariable String userId) {

        return this.getUserByIdService.getUserById(token, userId);
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public AuthUserResponse auth(
            @RequestBody AuthUserRequest authUserRequest) {

        return this.authUserService.authUser(authUserRequest);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createUser(
            @RequestHeader("Authorization") String token,
            @RequestBody UserRequest userRequest) {

        return this.createUserService.createUser(token, userRequest);
    }

    @PostMapping("/logout/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void logout(
            @RequestHeader("Authorization") String token,
            @PathVariable String userId) {

        logOutService.logout(token, userId);
    }

    @PutMapping("/update/pw/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse updatePassword(
            @RequestHeader("Authorization") String token,
            @RequestBody UpdatePasswordRequest updatePasswordRequest,
            @PathVariable String userId) {

        return updatePasswordService.updatePassword(token, userId, updatePasswordRequest);
    }

    @PutMapping("/update/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse updateUser(
            @RequestHeader("Authorization") String token,
            @PathVariable String userId,
            @RequestBody UserRequest updateUserRequest) {

        return updateUserService.updateUser(token, userId, updateUserRequest);
    }
}
