package com.bidi.users.service.impl;

import com.bidi.users.dto.updatepassword.UpdatePasswordRequest;
import com.bidi.users.service.UpdatePasswordService;
import com.bidi.users.util.MessageResponse;
import com.bidi.users.util.UserException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class UpdatePasswordImpl implements UpdatePasswordService {

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(UpdatePasswordImpl.class);

    @Value("${sso.config.url}")
    private String endpointBase;
    @Value("${sso.config.url.users}")
    private String passwordPath;

    @Override
    public MessageResponse updatePassword(
            UpdatePasswordRequest updatePasswordRequest,
            String idUser) {
        logger.error("request: {}", updatePasswordRequest);
        try {
            restTemplate.exchange(setUrl(idUser), HttpMethod.PUT,
                    setRequest(updatePasswordRequest),
                    String.class);
            return new MessageResponse("00", "Successfully.");
        }catch (HttpClientErrorException | HttpServerErrorException e) {
            logger.error("Error: {}", e.getMessage());
            throw new UserException((HttpStatus) e.getStatusCode(), "01", e.getMessage());
        }
    }

    public String setUrl(String idUser) {
        return endpointBase + passwordPath + idUser + "/reset-password";
    }

    public HttpEntity<UpdatePasswordRequest> setRequest(UpdatePasswordRequest updatePasswordRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(updatePasswordRequest, headers);
    }
}
