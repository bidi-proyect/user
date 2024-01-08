package com.bidi.users.service;

import com.bidi.users.dto.request.UpdatePasswordRequest;
import com.bidi.users.dto.response.MessageResponse;
import org.springframework.stereotype.Service;

@Service
public interface UpdatePasswordService {
    MessageResponse updatePassword(String token, String idUser, UpdatePasswordRequest updatePasswordRequest);
}
