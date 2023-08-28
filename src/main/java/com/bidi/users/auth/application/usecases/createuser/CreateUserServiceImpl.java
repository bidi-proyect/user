package com.bidi.users.auth.application.usecases.createuser;

import com.bidi.users.auth.domain.entity.CreateUserDomain;
import com.bidi.users.auth.domain.entity.dto.create.request.CreateUserRequestDomain;
import com.bidi.users.auth.domain.service.CreateUserServiceDomain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreateUserServiceImpl implements CreateUserServiceDomain {
    @Value("${sso.config.url}")
    private String urlBase;
    @Value("${sso.config.url.create}")
    private String createPath;
    @Autowired
    RestTemplate restTemplate;
    private static  final Logger logger  = LoggerFactory.getLogger(CreateUserServiceImpl.class);

    @Override
    public CreateUserDomain createUser(CreateUserRequestDomain createUserRequestDomain) {
        return null;
    }
}
