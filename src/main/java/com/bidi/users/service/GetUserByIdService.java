package com.bidi.users.service;

import com.bidi.users.dto.userbyid.UserById;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetUserByIdService {
    UserById getUserInfo(String idUser, String token);
}
