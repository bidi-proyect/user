package com.bidi.users.service.impl;

import com.bidi.users.dto.userlist.ListUsers;
import com.bidi.users.service.GetListUsersService;
import com.bidi.users.util.UserException;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@Service
@RequiredArgsConstructor
public class GetListUsers implements GetListUsersService {
    private static final String REALM = "credibanco";
    private static final String ATTRIBUTE = "cel";
    private static final String VALUE = "1212121212";
    private final RestTemplate restTemplate;
    private static final Logger logger = LoggerFactory.getLogger(GetListUsers.class);
    @Override
    public List<ListUsers> getListUsers(String token) {
        // Crear un objeto RestTemplate
        RestTemplate restTemplate = new RestTemplate();

        // Crear un encabezado de autorización
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", token);
        logger.info(token);

        // Crear un objeto HttpEntity
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Crear la URL de la solicitud
        String url = "https://sso-sso-pruebas.apps-pruebas.credibanco.com/auth/admin/realms/credibanco/users";

        // Enviar la solicitud
        ResponseEntity<List<ListUsers>> response = restTemplate.exchange(url, HttpMethod.GET, entity,
                new ParameterizedTypeReference<List<ListUsers>>() {}
        );

        // Procesar la respuesta
        if (!response.getStatusCode().is2xxSuccessful()) {
            throw new UserException(HttpStatus.CONFLICT, "01", "Error.");

        }
        return response.getBody();
    }
}
