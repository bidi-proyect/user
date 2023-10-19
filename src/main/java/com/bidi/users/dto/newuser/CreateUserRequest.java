package com.bidi.users.dto.newuser;

import com.bidi.users.dto.newuser.helper.Attributes;
import com.bidi.users.dto.newuser.helper.Credentials;
import lombok.Data;

import java.util.List;

@Data
public class CreateUserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private Attributes attributes;
    private List<Credentials> credentials;
}
