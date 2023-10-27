package com.bidi.users.service.impl;

import com.bidi.users.dto.userbyid.UserById;
import com.bidi.users.service.GetUserByIdService;
import com.bidi.users.util.UserException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetUserById implements GetUserByIdService {
    @Value("${sso.config.url}")
    private String basePath;
    @Value("${sso.config.url.get}")
    private String getPath;
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(GetUserById.class);
    @Override
    public UserById getUserInfo(String idUser, String token) {
        logger.info("IdUser: {}", idUser);
        try {
            ResponseEntity<UserById> response = this.restTemplate.exchange(
                    basePath + getPath + idUser,
                    HttpMethod.GET,
                    setHttpEntity(token),
                    UserById.class
            );
            return response.getBody();
        }catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.error("Error: {}", e.getMessage());
            throw new UserException((HttpStatus) e.getStatusCode(), "01", e.getMessage());
        }
    }

    public HttpEntity<String> setHttpEntity(String token) {
        logger.info("----- {}", token);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        return new HttpEntity<>(headers);
    }
}
