package com.bidi.users.auth.application.usecases.authuser;

import com.bidi.users.auth.domain.entity.dto.AuthUserDomain;
import com.bidi.users.auth.domain.entity.dto.auth.request.AuthUserRequestDomain;
import com.bidi.users.auth.domain.service.AuthUserServiceDomain;
import org.springframework.stereotype.Service;

@Service
public class AuthUserServiceImpl implements AuthUserServiceDomain {

    @Override
    public AuthUserDomain authUser(AuthUserRequestDomain authUserRequestDomain) {
        return null;
    }
}
