package com.bidi.users.service;

import com.bidi.users.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetAllUsersService {
    List<UserDto> getAllUsers();
}
