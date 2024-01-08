package com.bidi.users.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthUserResponse {
    private String code;
    private String message;
    @JsonProperty(value = "access_token")
    private String accesToken;
}
