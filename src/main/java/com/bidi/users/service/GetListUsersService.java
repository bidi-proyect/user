package com.bidi.users.service;

import com.bidi.users.dto.userlist.ListUsers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface GetListUsersService {
    List<ListUsers> getListUsers(String token);
}
