package com.bidi.users.service;

import com.bidi.users.dto.updateuser.UpdateUserRequest;
import com.bidi.users.util.MessageResponse;
import org.springframework.stereotype.Service;

@Service
public interface UpdateUserService {
    MessageResponse updateUser(String idUser, UpdateUserRequest updateUserRequest);
}
