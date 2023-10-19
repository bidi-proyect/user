package com.bidi.users.dto.updatepassword;

import lombok.Data;

@Data
public class UpdatePasswordRequest {
    private String type;
    private String temporary;
    private String value;
}
