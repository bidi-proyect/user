package com.bidi.users.service;

import com.bidi.users.dto.auth.request.AuthUserRequest;
import com.bidi.users.dto.auth.response.AuthUserResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthUserService {
    AuthUserResponse authUser(AuthUserRequest authUserRequest);
}
