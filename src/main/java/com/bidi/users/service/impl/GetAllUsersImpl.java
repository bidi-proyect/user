package com.bidi.users.service.impl;

import com.bidi.users.dto.response.UserResponse;
import com.bidi.users.service.GetAllUsersService;
import com.bidi.users.util.StringConstants;
import com.bidi.users.util.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class GetAllUsersImpl implements GetAllUsersService {

    private final RestTemplate restTemplate;
    @Value("${sso.config.url}")
    private String urlBase;
    @Value("${sso.config.url.path.users}")
    private String getPath;
    @Override
    public List<UserResponse> getAllUsers(String token) {
        String url = urlBase + getPath;
        try {
            ResponseEntity<List<UserResponse>> response = this.restTemplate
                    .exchange(
                            url, HttpMethod.GET,
                            setHttpEntity(token),
                            new ParameterizedTypeReference<>() {}
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

    public List<UserResponse> isResponse200(ResponseEntity<List<UserResponse>> response) {
        if (!response.getStatusCode().is2xxSuccessful()) {
            log.error("error");
            throw new UserException(HttpStatus.CONFLICT, "01", "Error.");
        }
        return response.getBody();
    }
}
