package com.bidi.users.service.impl;

import com.bidi.users.dto.newuser.CreateUserRequest;
import com.bidi.users.service.CreateUserService;
import com.bidi.users.util.KeycloakProvider;
import com.bidi.users.util.MessageResponse;
import jakarta.ws.rs.core.Response;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
public class CreateUserImpl implements CreateUserService {

    @Override
    public MessageResponse createUser(@NonNull CreateUserRequest createUserRequest) {
        int status;
        UsersResource usersResource = KeycloakProvider.getUserResource();

        UserRepresentation userRepresentation = dtoToRepresentation(createUserRequest);

        Response response = usersResource.create(userRepresentation);

        status = response.getStatus();

        if (status == 201) {
            String path = response.getLocation().getPath();
            String userId = path.substring(path.lastIndexOf("/") + 1);

            CredentialRepresentation credentialRepresentation = new CredentialRepresentation();

            credentialRepresentation.setTemporary(false);
            credentialRepresentation.setType(OAuth2Constants.PASSWORD);
            credentialRepresentation.setValue(createUserRequest.getPassword());

            usersResource.get(userId).resetPassword(credentialRepresentation);

            RealmResource realmResource = KeycloakProvider.getRealmResource();

            List<RoleRepresentation> roleRepresentations;

            if (createUserRequest.getRoles() == null || createUserRequest.getRoles().isEmpty()) {
                roleRepresentations = List.of(realmResource.roles().get("userBidi").toRepresentation());
            } else {
                roleRepresentations = realmResource.roles()
                        .list()
                        .stream()
                        .filter(role -> createUserRequest.getRoles()
                                .stream()
                                .anyMatch(roleName -> roleName.equalsIgnoreCase(role.getName())))
                        .toList();
            }

            realmResource.users().get(userId).roles().realmLevel().add(roleRepresentations);
            return new MessageResponse("00", "User created successfully");
        } else if (status == 409) {
            log.error("User exist already.");
            return new MessageResponse("01", "User exist already.");
        } else {
            log.error("Error creating user.");
            return new MessageResponse("00", "Error creating user.");
        }
    }

    public UserRepresentation dtoToRepresentation(CreateUserRequest createUserRequest) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(createUserRequest.getFirstName());
        userRepresentation.setLastName(createUserRequest.getLastName());
        userRepresentation.setUsername(createUserRequest.getUsername());
        userRepresentation.setEmail(createUserRequest.getEmail());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);

        return userRepresentation;
    }

}
