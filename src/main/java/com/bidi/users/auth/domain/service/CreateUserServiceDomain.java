package com.bidi.users.auth.domain.service;

import com.bidi.users.auth.domain.entity.CreateUserDomain;
import com.bidi.users.auth.domain.entity.dto.create.request.CreateUserRequestDomain;
import org.springframework.stereotype.Service;

@Service
public interface CreateUserServiceDomain {
    CreateUserDomain createUser(CreateUserRequestDomain createUserRequestDomain);
}
