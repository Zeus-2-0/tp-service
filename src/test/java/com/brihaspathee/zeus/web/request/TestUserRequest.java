package com.brihaspathee.zeus.web.request;

import com.brihaspathee.zeus.web.security.UserDto;
import com.brihaspathee.zeus.web.security.UserList;
import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 17, February 2022
 * Time: 3:34 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.request
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestUserRequest {

    private boolean exceptionExpected;

    private String exceptionCode;

    private String exceptionMessage;

    private String httpStatusCode;

    private boolean authException;

    private UserDto loggedInUser;

    private UserDto userDtoRequest;

    private UserList expectedUserList;

    private UserDto expectedUserDto;
}
