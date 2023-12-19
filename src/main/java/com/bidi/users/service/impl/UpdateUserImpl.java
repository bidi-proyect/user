package com.bidi.users.service.impl;

import com.bidi.users.dto.updateuser.UpdateUserRequest;
import com.bidi.users.service.UpdateUserService;
import com.bidi.users.util.KeycloakProvider;
import com.bidi.users.util.MessageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
@Slf4j
public class UpdateUserImpl implements UpdateUserService {
    @Override
    public MessageResponse updateUser(String idUser, UpdateUserRequest updateUserRequest) {

        UserRepresentation userRepresentation = dtoToRepresentation(updateUserRequest);
        UserResource userResource = KeycloakProvider.getUserResource().get(idUser);
        userResource.update(userRepresentation);
        log.info("User updated successfully.");

        return new MessageResponse("00", "User updated successfully.");

    }

    public UserRepresentation dtoToRepresentation(UpdateUserRequest updateUserRequest) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setFirstName(updateUserRequest.getFirstName());
        userRepresentation.setLastName(updateUserRequest.getLastName());
        userRepresentation.setUsername(updateUserRequest.getUsername());
        userRepresentation.setEmail(updateUserRequest.getEmail());
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        userRepresentation.setRealmRoles(updateUserRequest.getRoles());

        if (updateUserRequest.getPassword() != null) {
            userRepresentation.setCredentials(Collections.singletonList(dtoToCredential(updateUserRequest)));
        }
        return userRepresentation;
    }

    public CredentialRepresentation dtoToCredential(UpdateUserRequest updateUserRequest) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();

        credentialRepresentation.setTemporary(false);
        credentialRepresentation.setType(OAuth2Constants.PASSWORD);
        credentialRepresentation.setValue(updateUserRequest.getPassword());
        return credentialRepresentation;
    }

}
