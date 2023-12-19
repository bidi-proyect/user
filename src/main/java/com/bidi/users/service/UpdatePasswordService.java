package com.bidi.users.service;

import com.bidi.users.dto.updatepassword.UpdatePasswordRequest;
import com.bidi.users.util.MessageResponse;
import org.springframework.stereotype.Service;

@Service
public interface UpdatePasswordService {
    MessageResponse updatePassword(UpdatePasswordRequest updatePasswordRequest,
                                   String idUser);
}
