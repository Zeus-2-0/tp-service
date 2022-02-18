package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.web.security.RoleDto;

import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 17, February 2022
 * Time: 11:12 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface RoleService {

    RoleDto getRoleById(UUID roleId);
    RoleDto getRoleByName(String roleName);
    Set<RoleDto> getAllRoles();
    RoleDto createRole(RoleDto roleDto);
    void deleteRole(UUID roleId);
}
