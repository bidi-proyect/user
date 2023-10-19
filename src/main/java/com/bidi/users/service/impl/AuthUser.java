package com.bidi.users.service.impl;

import com.bidi.users.dto.auth.request.AuthUserRequest;
import com.bidi.users.dto.auth.response.AuthUserResponse;
import com.bidi.users.service.AuthUserService;
import com.bidi.users.util.StringConstants;
import com.bidi.users.util.UserException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class AuthUser implements AuthUserService {

    private final RestTemplate restTemplate;
    private final Environment environment;
    private final Logger logger = LoggerFactory.getLogger(CreateUser.class);
    @Value("${sso.config.url}")
    private String url;
    @Value("${sso.config.url.auth}")
    private String authPath;
    @Value("${sso.config.anonymouse.username}")
    private String username;
    @Value("${sso.config.anonymouse.password}")
    private String password;

    @Override
    public AuthUserResponse authUser(AuthUserRequest authUserRequest) {
        logger.info("Request: {}", authUserRequest.toString());

        try {
            ResponseEntity<AuthUserResponse> response = this.restTemplate.exchange(
                    url + authPath,
                    HttpMethod.POST,
                    setHttpEntity(authUserRequest),
                    AuthUserResponse.class);
            logger.debug("Token crm {}", response.getBody());
            return setResponse(response.getBody());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.error("Error {}", e.getMessage());
            throw new UserException(e.getStatusCode(), "01", e.getMessage());
        }
    }

    private HttpEntity<MultiValueMap<String, String>> setHttpEntity(AuthUserRequest authUserRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add(StringConstants.USERNAME, authUserRequest.getUsername());
        params.add(StringConstants.PASSWORD, authUserRequest.getPassword());
        params.add(StringConstants.GRANT_TYPE, environment.getProperty("sso.config.crm.grant-type"));
        params.add(StringConstants.CLIENT_ID, environment.getProperty("sso.config.crm.client-id"));
        params.add(StringConstants.CLIENT_SECRET, environment.getProperty("sso.config.crm.client-secret"));

        return new HttpEntity<MultiValueMap<String, String>>(params, headers);
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
            logger.error("Token is null");
            throw new UserException(HttpStatus.INTERNAL_SERVER_ERROR, "01", "Something was wrong.");
        }
        return false;
    }

    public String getAnonymouseToken() {
        AuthUserResponse response = authUser(new AuthUserRequest(username, password));
        return "Bearer " + response.getAccesToken();
    }
}
