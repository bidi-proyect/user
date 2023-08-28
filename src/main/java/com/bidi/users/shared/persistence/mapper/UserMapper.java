package com.bidi.users.shared.persistence.mapper;

import com.bidi.users.auth.domain.entity.CreateUserDomain;
import com.bidi.users.shared.persistence.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User userDomainToEntity(CreateUserDomain createUserDomain);
}
