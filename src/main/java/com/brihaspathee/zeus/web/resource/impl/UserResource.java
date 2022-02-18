package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.constants.ApiResponseConstants;
import com.brihaspathee.zeus.service.interfaces.UserService;
import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.resource.interfaces.UserApi;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.brihaspathee.zeus.web.security.UserDto;
import com.brihaspathee.zeus.web.security.UserList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    private final UserService userService;

    @Override
    public ResponseEntity<ZeusApiResponse<UserList>> getUser(UUID userId, String username) {
        log.info("User Id:{}", userId);
        log.info("Username:{}", username);
        UserList userList = new UserList();
        if(userId != null){
            UserDto userDto = userService.getUserById(userId);
            userList.setUsers(List.of(userDto));
        }else if (username != null){
            UserDto userDto = userService.getUserByUserName(username);
            userList.setUsers(List.of(userDto));
        }else{
            List<UserDto> userDtos = userService.getAllUsers().stream().toList();
            userList.setUsers(userDtos);
        }
        ZeusApiResponse<UserList> apiResponse = ZeusApiResponse.<UserList>builder()
                .reason(ApiResponseConstants.SUCCESS_REASON)
                .message(ApiResponseConstants.SUCCESS)
                .response(userList)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ZeusApiResponse<UserDto>> createUser(UserDto userDto) {
        UserDto savedUser = userService.saveUser(userDto);
        ZeusApiResponse<UserDto> apiResponse = ZeusApiResponse.<UserDto>builder()
                .reason(ApiResponseConstants.SUCCESS_REASON)
                .message(ApiResponseConstants.SUCCESS)
                .response(savedUser)
                .build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/tp/user/"+savedUser.getUserId());
        return new ResponseEntity<ZeusApiResponse<UserDto>>(apiResponse, httpHeaders, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ZeusApiResponse<UserDto>> updateUser(UserDto userDto, UUID userId) {
        return null;
    }


}
