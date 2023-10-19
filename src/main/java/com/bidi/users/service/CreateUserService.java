package com.bidi.users.service;

import com.bidi.users.dto.newuser.CreateUserRequest;
import com.bidi.users.util.MessageResponse;
import org.springframework.stereotype.Service;

@Service
public interface CreateUserService {
    MessageResponse createUser(CreateUserRequest createUserRequest);
}
