package com.bidi.users.auth.domain.entity.dto.auth.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class AuthUserResponseDomain {
    @JsonProperty(value = "access_token")
    private String accesToken;
    @JsonProperty(value = "expires_in")
    private int expiresIn;
    @JsonProperty(value = "refresh_expires_in")
    private int refreshExpiresIn;
    @JsonProperty(value = "refresh_token")
    private String refreshToken;
    @JsonProperty(value = "token_type")
    private String tokenType;
    @JsonProperty(value = "not-before-policy")
    private int notBeforePolicy;
    @JsonProperty(value = "session_state")
    private String sessionState;
    @JsonProperty(value = "scope")
    private String scope;
}
