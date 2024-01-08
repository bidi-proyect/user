package com.bidi.users.service;

import com.bidi.users.dto.request.AuthUserRequest;
import com.bidi.users.dto.response.AuthUserResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthUserService {
    AuthUserResponse authUser(AuthUserRequest authUserRequest);
}
