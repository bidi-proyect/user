package com.bidi.users.service;

import com.bidi.users.dto.response.UserResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetAllUsersService {
    List<UserResponse> getAllUsers(String token);
}
