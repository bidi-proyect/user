package com.bidi.users.service.impl;

import com.bidi.users.dto.newuser.CreateUserRequest;
import com.bidi.users.dto.userlist.ListUsers;
import com.bidi.users.service.GetListUsersService;
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
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GetListUsers implements GetListUsersService {
    @Value("${sso.config.url}")
    private String urlBase;

    @Value("${sso.config.url.users}")
    private String usersPath;

    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(GetListUsers.class);
    @Override
    public List<ListUsers> getListUsers(String token) {
        logger.info(token);

        try {
            ResponseEntity<List<ListUsers>> response = restTemplate.exchange(
                    urlBase + usersPath,
                    HttpMethod.GET, setHttpEntity(token),
                    new ParameterizedTypeReference<List<ListUsers>>() {
                    }
            );
            isResponse200(response);

            return response.getBody();
        }catch (Exception e) {
            logger.error("error: {}", e.getMessage());
            throw new UserException(HttpStatus.CONFLICT, "01", "Error.");
        }
    }

    public HttpEntity<CreateUserRequest> setHttpEntity(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", token);
        return new HttpEntity<>(headers);
    }
    public void isResponse200(ResponseEntity<List<ListUsers>> response) {
        if (!response.getStatusCode().is2xxSuccessful()) {
            logger.error("error");
            throw new UserException(HttpStatus.CONFLICT, "01", "Error.");
        }
    }
}
