package com.bidi.users.service.impl;

import com.bidi.users.dto.request.UserRequest;
import com.bidi.users.service.UpdateUserService;
import com.bidi.users.dto.response.MessageResponse;
import com.bidi.users.util.StringConstants;
import com.bidi.users.util.UserException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Log4j2
public class UpdateUserImpl implements UpdateUserService {

    private final RestTemplate restTemplate;
    @Value("${sso.config.url}")
    private String urlBase;
    @Value("${sso.config.url.path.users}")
    private String updatePath;
    @Override
    public MessageResponse updateUser(String token, String userId, UserRequest userRequest) {
        log.info("Request is made...");
        String url = urlBase + updatePath + userId;
        try {
            ResponseEntity<MessageResponse> response = this.restTemplate
                    .exchange(
                            url, HttpMethod.PUT,
                            setHttpEntity(token, userRequest),
                            MessageResponse.class
                    );
            if (!response.getStatusCode().is2xxSuccessful()) {
                return new MessageResponse(StringConstants.CODE_01, StringConstants.SOMETHING_WRONG);
            }
            return new MessageResponse(StringConstants.CODE_00, StringConstants.SUCCESSFULLY);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("Error {}", e.getMessage());
            throw new UserException(e.getStatusCode(), StringConstants.CODE_1, e.getMessage());
        }
    }

    public HttpEntity<UserRequest> setHttpEntity(String token, UserRequest userRequest) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);
        return new HttpEntity<>(userRequest, headers);
    }
}
