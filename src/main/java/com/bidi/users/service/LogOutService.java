package com.bidi.users.service;

import org.springframework.stereotype.Service;

@Service
public interface LogOutService {
    void logout(String token, String userId);
}
