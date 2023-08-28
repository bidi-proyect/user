package com.bidi.users.auth.domain.entity.dto.auth.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthUserRequestDomain {

    @JsonProperty(value = "username")
    private String username;
    @JsonProperty(value = "client_id")
    private String clientId;
    @JsonProperty(value = "grant_type")
    private String grantType;
    @JsonProperty(value = "client_secret")
    private String clientSecret;
    @JsonProperty(value = "password")
    private String password;
}
