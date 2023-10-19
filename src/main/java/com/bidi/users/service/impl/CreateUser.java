package com.bidi.users.service.impl;

import com.bidi.users.dto.newuser.CreateUserRequest;
import com.bidi.users.service.CreateUserService;
import com.bidi.users.util.MessageResponse;
import com.bidi.users.util.UserException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class CreateUser implements CreateUserService {

    private final AuthUser authUser;
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(CreateUser.class);
    @Value("${sso.config.url}")
    private String endpointBase;
    @Value("${sso.config.url.users}")
    private String createPath;


    @Override
    public MessageResponse createUser(CreateUserRequest createUserRequest) {

        logger.info("request: {}", createUserRequest);

        try {
            ResponseEntity<String> response = this.restTemplate
                    .postForEntity(
                            endpointBase + createPath,
                            setHttpEntity(createUserRequest),
                            String.class
                    );
            return new MessageResponse("00", "User created successfully.");
        }catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.error("Error: {}", e.getMessage());
            throw new UserException(e.getStatusCode(), "01", e.getMessage());
        }
    }

    public HttpEntity<CreateUserRequest> setHttpEntity(CreateUserRequest createUserRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authUser.getAnonymouseToken());
        return new HttpEntity<>(createUserRequest, headers);
    }

}
