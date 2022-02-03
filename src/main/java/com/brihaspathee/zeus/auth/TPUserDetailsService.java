package com.brihaspathee.zeus.auth;

import com.brihaspathee.zeus.domain.repository.UserRepository;
import com.brihaspathee.zeus.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 21, January 2022
 * Time: 11:06 AM
 * Project: Zeus
 * Package Name: com.zeus.auth
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TPUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Getting Details from DB using JPA");
        return userRepository.findUserByUsername(username).orElseThrow( () -> {
            return new UserNotFoundException("User with username " + username + " not found");
        }) ;
    }
}
