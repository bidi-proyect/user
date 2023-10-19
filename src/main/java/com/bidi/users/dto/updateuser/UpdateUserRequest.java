package com.bidi.users.dto.updateuser;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
}
