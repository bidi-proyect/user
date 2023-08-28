package com.bidi.users.auth.domain.entity.dto.create.request.helper;

import lombok.Data;

@Data
public class CredentialsRequestDomain {
    private String type;
    private String password;
}
