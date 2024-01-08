package com.bidi.users.service;

import com.bidi.users.dto.request.UserRequest;
import com.bidi.users.dto.response.MessageResponse;
import org.springframework.stereotype.Service;

@Service
public interface CreateUserService {
    MessageResponse createUser(String token, UserRequest userRequest);
}
