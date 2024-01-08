package com.bidi.users.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private List<String> attributes;
    private Map<String, String> credentials;
    @JsonProperty(value = "realmRoles")
    private List<String> roles;
}
