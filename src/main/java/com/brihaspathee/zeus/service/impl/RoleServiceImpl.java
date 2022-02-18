package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.repository.RoleRepository;
import com.brihaspathee.zeus.domain.security.Role;
import com.brihaspathee.zeus.exception.RoleNotFoundException;
import com.brihaspathee.zeus.mapper.interfaces.RoleMapper;
import com.brihaspathee.zeus.service.interfaces.RoleService;
import com.brihaspathee.zeus.web.security.RoleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 17, February 2022
 * Time: 12:35 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    @Override
    public RoleDto getRoleById(UUID roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> {
            throw new RoleNotFoundException("Role with role id " + roleId + "not found");
        });
        return roleMapper.roleToRoleDto(role);
    }

    @Override
    public RoleDto getRoleByName(String roleName) {
        Role role = roleRepository.findRoleByRoleName(roleName).orElseThrow(() -> {
            throw new RoleNotFoundException("Role with role name " + roleName + " not found");
        });
        return roleMapper.roleToRoleDto(role);
    }

    @Override
    public Set<RoleDto> getAllRoles() {
        Set<Role> roles = roleRepository.findAll().stream().collect(Collectors.toSet());
        return roleMapper.rolesToRoleDtos(roles);
    }

    @Override
    public RoleDto createRole(RoleDto roleDto) {
        Role role = roleMapper.roleDtoToRole(roleDto);
        role = roleRepository.save(role);
        return roleMapper.roleToRoleDto(role);
    }

    @Override
    public void deleteRole(UUID roleId) {
        roleRepository.deleteById(roleId);
    }
}
