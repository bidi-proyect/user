package com.bidi.users.controller;


import com.bidi.users.dto.updatepassword.UpdatePasswordRequest;
import com.bidi.users.dto.updateuser.UpdateUserRequest;
import com.bidi.users.dto.UserDto;
import com.bidi.users.service.GetAllUsersService;
import com.bidi.users.service.GetUserByIdService;
import com.bidi.users.service.UpdatePasswordService;
import com.bidi.users.service.UpdateUserService;
import com.bidi.users.util.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@CrossOrigin("*")
@PreAuthorize("hasRole('userBidi') or hasRole('adminBidi')")
public class UserController {

    private final GetUserByIdService getUserByIdService;
    private final GetAllUsersService getAllUsersService;
    private final UpdateUserService updateUserService;
    private final UpdatePasswordService updatePasswordService;

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getAllUsers() {
        return this.getAllUsersService.getAllUsers();
    }

    @GetMapping("/search/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUserInfo(@PathVariable String idUser) {
        return this.getUserByIdService.getUserById(idUser);
    }

    @PutMapping("/update/pw/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public void updatePassword(
            @RequestBody UpdatePasswordRequest updatePasswordRequest,
            @PathVariable String idUser) {

        updatePasswordService.updatePassword(updatePasswordRequest, idUser);
    }

    @PutMapping("/update/{idUser}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponse updateUser(
            @PathVariable String idUser,
            @RequestBody UpdateUserRequest updateUserRequest){

        return updateUserService.updateUser(idUser, updateUserRequest);
    }

}
