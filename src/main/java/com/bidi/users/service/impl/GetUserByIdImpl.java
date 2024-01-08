package com.bidi.users.service.impl;

import com.bidi.users.dto.response.UserResponse;
import com.bidi.users.service.GetUserByIdService;
import com.bidi.users.util.StringConstants;
import com.bidi.users.util.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;


@Service
@RequiredArgsConstructor
@Slf4j
public class GetUserByIdImpl implements GetUserByIdService {

    private final RestTemplate restTemplate;
    @Value("${sso.config.url}")
    private String urlBase;
    @Value("${sso.config.url.path.users}")
    private String getPath;

    @Override
    public UserResponse getUserById(String token, String userId) {
        String url = urlBase + getPath + userId;
        try {
            ResponseEntity<UserResponse> response = this.restTemplate
                    .exchange(
                            url, HttpMethod.GET,
                            setHttpEntity(token),
                            UserResponse.class
                    );
            return isResponse200(response);
        }catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("Error {}", e.getMessage());
            throw new UserException(e.getStatusCode(), StringConstants.CODE_1, e.getMessage());
        }
    }


    public HttpEntity<UserResponse> setHttpEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);
        return new HttpEntity<>(headers);
    }

    public UserResponse isResponse200(ResponseEntity<UserResponse> response) {
        if (!response.getStatusCode().is2xxSuccessful()) {
            log.error("error");
            throw new UserException(HttpStatus.CONFLICT, "01", "Error.");
        }
        return response.getBody();
    }
}
