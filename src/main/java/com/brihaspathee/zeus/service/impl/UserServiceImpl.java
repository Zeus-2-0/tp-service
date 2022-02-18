package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.repository.RoleRepository;
import com.brihaspathee.zeus.domain.repository.UserRepository;
import com.brihaspathee.zeus.domain.security.User;
import com.brihaspathee.zeus.exception.RoleNotFoundException;
import com.brihaspathee.zeus.exception.UserNotFoundException;
import com.brihaspathee.zeus.mapper.interfaces.UserMapper;
import com.brihaspathee.zeus.service.interfaces.UserService;
import com.brihaspathee.zeus.web.security.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 12, February 2022
 * Time: 9:55 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final RoleRepository roleRepository;

    @Override
    public UserDto saveUser(UserDto userDto) {
        if(userDto.getUserId() == null){
            boolean rolesPresent = userDto.getRoles().stream().allMatch(roleDto -> roleRepository.existsById(roleDto.getRoleId()));
            if(!rolesPresent){
                throw new RoleNotFoundException("One or more roles assigned to the user not found");
            }
        }
        User user = userMapper.userDtoToUser(userDto);
        user = userRepository.save(user);
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto getUserById(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new UserNotFoundException("User with user id " + userId + " not found");
        });
        return userMapper.userToUserDto(user);
    }

    @Override
    public UserDto getUserByUserName(String username) {
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> {
            throw new UserNotFoundException("User with user name " + username + " not found");
        });
        return userMapper.userToUserDto(user);
    }

    @Override
    public Set<UserDto> getAllUsers() {
        Set<User> users = userRepository.findAll().stream().collect(Collectors.toSet());
        return userMapper.usersToUserDtos(users);
    }

    @Override
    public void deleteUser(UUID userId) {
        userRepository.deleteById(userId);
    }
}
