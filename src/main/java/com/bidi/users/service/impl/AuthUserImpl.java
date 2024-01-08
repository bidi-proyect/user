package com.bidi.users.service.impl;

import com.bidi.users.dto.request.AuthUserRequest;
import com.bidi.users.dto.response.AuthUserResponse;
import com.bidi.users.service.AuthUserService;
import com.bidi.users.util.StringConstants;
import com.bidi.users.util.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthUserImpl implements AuthUserService {

    private final RestTemplate restTemplate;
    private final Environment environment;
    @Value("${sso.config.url}")
    private String url;
    @Value("${sso.config.url.path.auth}")
    private String authPath;
    @Value("${sso.config.anonymouse.username}")
    private String username;
    @Value("${sso.config.anonymouse.password}")
    private String password;

    @Override
    public AuthUserResponse authUser(AuthUserRequest authUserRequest) {
        log.info("Request is made...");

        try {
            ResponseEntity<AuthUserResponse> response = this.restTemplate.exchange(
                    url + authPath,
                    HttpMethod.POST,
                    httpEntityForAuth(authUserRequest),
                    AuthUserResponse.class);

            log.info("Token generated successfully.");

            log.debug("Token crm {}", response.getBody());
            return setResponse(response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("Error {}", e.getMessage());
            throw new UserException(e.getStatusCode(), StringConstants.CODE_1, e.getMessage());
        }
    }

    private HttpEntity<MultiValueMap<String, String>> httpEntityForAuth(AuthUserRequest authUserRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add(StringConstants.USERNAME, authUserRequest.getUsername());
        params.add(StringConstants.PASSWORD, authUserRequest.getPassword());
        params.add(StringConstants.GRANT_TYPE, environment.getProperty("sso.config.crm.grant-type"));
        params.add(StringConstants.CLIENT_ID, environment.getProperty("sso.config.crm.client-id"));
        params.add(StringConstants.CLIENT_SECRET, environment.getProperty("sso.config.crm.client-secret"));

        return new HttpEntity<>(params, headers);
    }

    public AuthUserResponse setResponse(AuthUserResponse authUserResponse) {
        if (!isResponseNull(authUserResponse)) {
            authUserResponse.setCode("00");
            authUserResponse.setMessage("Successfully.");
        }
        return authUserResponse;
    }

    public boolean isResponseNull(AuthUserResponse authUserResponse) {
        if (authUserResponse == null) {
            log.error("Token is null");
            throw new UserException(HttpStatus.INTERNAL_SERVER_ERROR, "01", "Something was wrong.");
        }
        return false;
    }

    public String getAnonymouseToken() {
        AuthUserResponse response = authUser(new AuthUserRequest(username, password));
        return "Bearer " + response.getAccesToken();
    }
}
