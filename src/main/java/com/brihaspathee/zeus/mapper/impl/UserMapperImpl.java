package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.security.User;
import com.brihaspathee.zeus.mapper.interfaces.RoleMapper;
import com.brihaspathee.zeus.mapper.interfaces.UserMapper;
import com.brihaspathee.zeus.web.security.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 12, February 2022
 * Time: 8:19 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final RoleMapper roleMapper;

    @Override
    public User userDtoToUser(UserDto userDto) {
        if(userDto == null){
            return null;
        }
        User user = User.builder()
                .userId(userDto.getUserId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
        if(userDto.getRoles()!=null &&
            !userDto.getRoles().isEmpty()){
            user.setRoles(userDto.getRoles().stream().map(roleMapper :: roleDtoToRole).collect(Collectors.toSet()));
        }
        return user;
    }

    @Override
    public UserDto userToUserDto(User user) {
        if(user == null){
            return null;
        }
        UserDto userDto = UserDto.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        if(user.getRoles()!=null &&
                !user.getRoles().isEmpty()){
            userDto.setRoles(user.getRoles().stream().map(roleMapper :: roleToRoleDto).collect(Collectors.toSet()));
        }
        return userDto;
    }

    @Override
    public Set<User> userDtosToUser(Set<UserDto> userDtos) {

        return userDtos.stream().map(this::userDtoToUser).collect(Collectors.toSet());
    }

    @Override
    public Set<UserDto> usersToUserDtos(Set<User> users) {

        return users.stream().map(this::userToUserDto).collect(Collectors.toSet());
    }
}
