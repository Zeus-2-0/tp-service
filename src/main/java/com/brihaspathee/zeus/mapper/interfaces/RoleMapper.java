package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.security.Role;
import com.brihaspathee.zeus.web.security.RoleDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 12, February 2022
 * Time: 10:01 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface RoleMapper {

    Role roleDtoToRole(RoleDto roleDto);
    RoleDto roleToRoleDto(Role role);

    Set<Role> roleDtosToRole(Set<RoleDto> roleDtos);
    Set<RoleDto> rolesToRoleDtos(Set<Role> roles);
}
