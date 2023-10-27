package com.bidi.users.controller;

import com.bidi.users.dto.auth.request.AuthUserRequest;
import com.bidi.users.dto.auth.response.AuthUserResponse;
import com.bidi.users.dto.newuser.CreateUserRequest;
import com.bidi.users.dto.updatepassword.UpdatePasswordRequest;
import com.bidi.users.dto.updateuser.UpdateUserRequest;
import com.bidi.users.dto.userbyid.UserById;
import com.bidi.users.dto.userlist.ListUsers;
import com.bidi.users.service.AuthUserService;
import com.bidi.users.service.CreateUserService;
import com.bidi.users.service.GetListUsersService;
import com.bidi.users.service.GetUserByIdService;
import com.bidi.users.service.UpdatePasswordService;
import com.bidi.users.service.UpdateUserService;
import com.bidi.users.util.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
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
public class UserController {

    private final AuthUserService authUserService;
    private final CreateUserService createUserService;
    private final GetUserByIdService getUserByIdService;
    private final GetListUsersService getListUsersService;
    private final UpdateUserService updateUserService;
    private final UpdatePasswordService updatePasswordService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/{username}")
    @ResponseStatus(HttpStatus.OK)
    public UserById getUserInfo(
            @RequestHeader("Authorization") String token,
            @PathVariable String username) {

        return this.getUserByIdService.getUserInfo(username, token);
    }

    @GetMapping("list")
    @ResponseStatus(HttpStatus.OK)
    private List<ListUsers> getListUser(@RequestHeader("Authorization") String token) {

        return this.getListUsersService.getListUsers(token);
    }

    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public AuthUserResponse authUser(
            @RequestBody AuthUserRequest authUserRequest) {

        return authUserService.authUser(authUserRequest);
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createUser(
            @RequestBody CreateUserRequest createUserRequest,
            @RequestHeader("Authorization") String token){

        return this.createUserService.createUser(createUserRequest);
    }

    @PutMapping("/update/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(
            @RequestBody UpdateUserRequest updateUserRequest,
            @PathVariable String idUser,
            @RequestHeader("Authorization") String token){

        updateUserService.updateUser(updateUserRequest, idUser, token);
    }

    @PutMapping("/update/pw/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(
            @RequestBody UpdatePasswordRequest updatePasswordRequest,
            @PathVariable String idUser,
            @RequestHeader("Authorization") String token) {

        updatePasswordService.updatePassword(updatePasswordRequest, idUser, token);
    }

}
