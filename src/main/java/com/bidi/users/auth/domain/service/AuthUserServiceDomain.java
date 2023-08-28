package com.bidi.users.auth.domain.service;

import com.bidi.users.auth.domain.entity.dto.AuthUserDomain;
import com.bidi.users.auth.domain.entity.dto.auth.request.AuthUserRequestDomain;
import org.springframework.stereotype.Service;

@Service
public interface AuthUserServiceDomain {
    AuthUserDomain authUser(AuthUserRequestDomain authUserRequestDomain);
}
