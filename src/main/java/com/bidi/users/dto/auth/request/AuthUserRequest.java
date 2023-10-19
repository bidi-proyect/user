package com.bidi.users.dto.auth.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthUserRequest {
    private String username;
    private String password;
}
