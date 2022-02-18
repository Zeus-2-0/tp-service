package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.security.User;
import com.brihaspathee.zeus.web.security.UserDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 12, February 2022
 * Time: 9:59 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface UserMapper {

    User userDtoToUser(UserDto userDto);
    UserDto userToUserDto(User user);

    Set<User> userDtosToUser(Set<UserDto> userDtos);
    Set<UserDto> usersToUserDtos(Set<User> users);
}
