package com.bidi.users.service.impl;

import com.bidi.users.dto.request.UpdatePasswordRequest;
import com.bidi.users.dto.response.MessageResponse;
import com.bidi.users.service.LogOutService;
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
public class LogoutImpl implements LogOutService {

    @Value("${sso.config.url}")
    private String urlBase;
    @Value("${sso.config.url.path.users}")
    private String logoutPath;
    @Value("${sso.config.url.path.logout}")
    private String logoutPathExtend;

    private final RestTemplate restTemplate;

    @Override
    public void logout(String token, String userId) {
        log.info("Request is made...");
        String url = urlBase + logoutPath + userId + logoutPathExtend;
        log.info(url);
        try {
            ResponseEntity<MessageResponse> response = this.restTemplate
                    .exchange(
                            url, HttpMethod.PUT,
                            setHttpEntity(token),
                            MessageResponse.class
                    );
            if (!response.getStatusCode().is2xxSuccessful()) {
                log.info("Error.");
            }
            log.info("Ok.");
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("Error {}", e.getMessage());
            throw new UserException(e.getStatusCode(), StringConstants.CODE_1, e.getMessage());
        }
    }

    public HttpEntity<UpdatePasswordRequest> setHttpEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);

        return new HttpEntity<>(headers);
    }
}
