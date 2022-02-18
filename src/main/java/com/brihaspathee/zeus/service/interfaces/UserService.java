package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.web.security.UserDto;

import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 12, February 2022
 * Time: 9:54 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface UserService {
    UserDto saveUser(UserDto userDto);
    UserDto getUserById(UUID userId);
    UserDto getUserByUserName(String username);
    Set<UserDto> getAllUsers();
    void deleteUser(UUID userId);
}
