package com.bidi.users.service.impl;

import com.bidi.users.dto.UserDto;
import com.bidi.users.service.GetUserByIdService;
import com.bidi.users.util.KeycloakProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class GetUserByIdImpl implements GetUserByIdService {

    @Override
    public UserDto getUserById(String idUser) {
        UserRepresentation userRepresentation = KeycloakProvider.getRealmResource()
                .users()
                .get(idUser).toRepresentation();
        return representationToDto1(userRepresentation);
    }

    public UserDto representationToDto1(UserRepresentation user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setCreatedTimestamp(String.valueOf(user.getCreatedTimestamp()));
        return userDto;
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
