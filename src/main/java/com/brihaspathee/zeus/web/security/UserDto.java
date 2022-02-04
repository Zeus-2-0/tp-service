package com.brihaspathee.zeus.web.security;

import lombok.*;

import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, February 2022
 * Time: 3:24 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.security
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private UUID userId;

    private String username;

    private String password;

    private Set<RoleDto> roles;

    @Override
    public String toString() {
        return "UserDto{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
