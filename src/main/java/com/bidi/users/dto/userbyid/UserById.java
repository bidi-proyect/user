package com.bidi.users.dto.userbyid;

import lombok.Data;

@Data
public class UserById {
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String createdTimestamp;
}
