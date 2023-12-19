package com.bidi.users.dto.updateuser;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private String password;
    private List<String> roles;
}
