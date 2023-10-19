package com.bidi.users.config;

import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakBean {
    @Bean
    Keycloak keycloak() {
        return KeycloakBuilder.builder()
                .serverUrl("https://sso-sso-pruebas.apps-pruebas.credibanco.com/auth")
                .realm("credibanco")
                .clientId("client-bidi-cbco")
                .clientSecret("78c9ed7e-538e-44a8-9eda-2895c8296d10")
                .grantType(OAuth2Constants.PASSWORD)
                .username("adminbidi@yopmail.com")
                .password("Colombia.4")
                .build();
    }
}
