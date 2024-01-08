package com.bidi.users.service;

import com.bidi.users.dto.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public interface GetUserByIdService {
    UserResponse getUserById(String token, String idUser);
}
