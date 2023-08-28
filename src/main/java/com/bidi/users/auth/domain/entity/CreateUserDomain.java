package com.bidi.users.auth.domain.entity;

import com.bidi.users.auth.domain.entity.dto.create.request.helper.AttributesRequestDomain;
import com.bidi.users.auth.domain.entity.dto.create.request.helper.CredentialsRequestDomain;
import lombok.Data;

import java.util.List;

@Data
public class CreateUserDomain {

    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private AttributesRequestDomain attributes;
    private List<CredentialsRequestDomain> credentials;
}
