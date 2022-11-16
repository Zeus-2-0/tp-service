package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.security.Role;
import com.brihaspathee.zeus.mapper.interfaces.AuthorityMapper;
import com.brihaspathee.zeus.mapper.interfaces.RoleMapper;
import com.brihaspathee.zeus.web.security.RoleDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
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
public class RoleMapperImpl implements RoleMapper {

    private final AuthorityMapper authorityMapper;

    @Override
    public Role roleDtoToRole(RoleDto roleDto) {
        if(roleDto == null){
            return null;
        }
        Role role = Role.builder()
                .roleId(roleDto.getRoleId())
                .roleName(roleDto.getRoleName())
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();
        if(roleDto.getAuthorities() != null &&
            !roleDto.getAuthorities().isEmpty()){
            role.setAuthorities(roleDto.getAuthorities()
                    .stream()
                    .map(authorityMapper :: authorityDtoToAuthority).collect(Collectors.toSet()));
        }
        return role;
    }

    @Override
    public RoleDto roleToRoleDto(Role role) {
        if(role == null){
            return null;
        }
        RoleDto roleDto = RoleDto.builder()
                .roleId(role.getRoleId())
                .roleName(role.getRoleName())
                .build();
        if(role.getAuthorities() != null &&
            !role.getAuthorities().isEmpty()){
            roleDto.setAuthorities(role.getAuthorities()
                    .stream()
                    .map(authorityMapper :: authorityToAuthorityDto).collect(Collectors.toSet()));
        }
        return roleDto;
    }

    @Override
    public Set<Role> roleDtosToRole(Set<RoleDto> roleDtos) {
        return roleDtos.stream().map(this::roleDtoToRole).collect(Collectors.toSet());
    }

    @Override
    public Set<RoleDto> rolesToRoleDtos(Set<Role> roles) {
        return roles.stream().map(this::roleToRoleDto).collect(Collectors.toSet());
    }
}
