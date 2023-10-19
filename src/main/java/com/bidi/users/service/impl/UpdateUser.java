package com.bidi.users.service.impl;

import com.bidi.users.dto.updateuser.UpdateUserRequest;
import com.bidi.users.service.UpdateUserService;
import com.bidi.users.util.MessageResponse;
import com.bidi.users.util.UserException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UpdateUser implements UpdateUserService {
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UpdateUser.class);
    @Value("${sso.config.url}")
    private String endpointBase;
    @Value("${sso.config.url.users}")
    private String updateUserPath;
    @Override
    public MessageResponse updateUser(UpdateUserRequest updateUserRequest, String idUser, String token) {
        logger.info("request: {}", updateUserRequest);
        logger.info("id: {}", idUser);
        try {
            restTemplate.exchange(setUrl(idUser),
                    HttpMethod.PUT,
                    setHttpEntity(updateUserRequest, token),
                    String.class);
            return new MessageResponse("00", "Successfully.");
        }catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.error("Error: {}", e.getMessage());
            throw new UserException(e.getStatusCode(), "01", e.getMessage());
        }
    }

    public String setUrl(String idUser) {
        return endpointBase + updateUserPath + idUser;
    }

    public HttpEntity<UpdateUserRequest> setHttpEntity(UpdateUserRequest updateUserRequest, String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);
        logger.info(token);
        return new HttpEntity<>(updateUserRequest, headers);
    }
}
