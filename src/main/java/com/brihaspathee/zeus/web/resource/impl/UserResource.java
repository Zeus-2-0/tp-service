package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.web.resource.interfaces.UserApi;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.brihaspathee.zeus.web.security.UserDto;
import com.brihaspathee.zeus.web.security.UserList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, February 2022
 * Time: 5:12 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class UserResource implements UserApi {


    @Override
    public ResponseEntity<ZeusApiResponse<UserList>> getUser(UUID userId, String username) {
        return null;
    }

    @Override
    public ResponseEntity<ZeusApiResponse<UserDto>> createUser(UserDto userDto) {
        return null;
    }

    @Override
    public ResponseEntity<ZeusApiResponse<UserDto>> updateUser(UserDto userDto, UUID userId) {
        return null;
    }


}
