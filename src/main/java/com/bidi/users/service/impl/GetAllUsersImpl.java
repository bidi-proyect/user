package com.bidi.users.service.impl;

import com.bidi.users.dto.UserDto;
import com.bidi.users.service.GetAllUsersService;
import com.bidi.users.util.KeycloakProvider;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetAllUsersImpl implements GetAllUsersService {
    @Override
    public List<UserDto> getAllUsers() {
        List<UserRepresentation> representations = KeycloakProvider.getRealmResource()
                .users()
                .list();

        return representationToDto(representations);
    }

    public List<UserDto> representationToDto(List<UserRepresentation> userRepresentations) {
        return userRepresentations.stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(user.getId());
                    userDto.setUsername(user.getUsername());
                    userDto.setEmail(user.getEmail());
                    userDto.setFirstName(user.getFirstName());
                    userDto.setLastName(user.getLastName());
                    userDto.setCreatedTimestamp(String.valueOf(user.getCreatedTimestamp()));
                    return userDto;
                }).collect(Collectors.toList());
    }

}
