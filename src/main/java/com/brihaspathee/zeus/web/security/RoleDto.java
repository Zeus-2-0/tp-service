package com.brihaspathee.zeus.web.security;

import lombok.*;

import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, February 2022
 * Time: 3:25 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.security
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    private UUID roleId;

    private String roleName;

    private Set<AuthorityDto> authorities;

    private Set<UserDto> users;

    @Override
    public String toString() {
        return "RoleDto{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                ", authorities=" + authorities +
                ", users=" + users +
                '}';
    }
}
