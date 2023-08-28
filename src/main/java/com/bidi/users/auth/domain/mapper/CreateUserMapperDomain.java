package com.bidi.users.auth.domain.mapper;

import com.bidi.users.auth.domain.entity.CreateUserDomain;
import com.bidi.users.auth.domain.entity.dto.create.response.CreateUserResponseDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CreateUserMapperDomain {
    CreateUserResponseDomain createResponseToDomain(CreateUserDomain createUserDomain);

}
