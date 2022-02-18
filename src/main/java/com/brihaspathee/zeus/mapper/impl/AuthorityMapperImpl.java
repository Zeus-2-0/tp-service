package com.brihaspathee.zeus.mapper.impl;

import com.brihaspathee.zeus.domain.security.Authority;
import com.brihaspathee.zeus.domain.security.Role;
import com.brihaspathee.zeus.mapper.interfaces.AuthorityMapper;
import com.brihaspathee.zeus.web.security.AuthorityDto;
import com.brihaspathee.zeus.web.security.RoleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 12, February 2022
 * Time: 10:07 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Component
public class AuthorityMapperImpl implements AuthorityMapper {

    @Override
    public Authority authorityDtoToAuthority(AuthorityDto authorityDto) {
        if (authorityDto == null){
            return null;
        }
        Authority authority = Authority.builder()
                .authorityId(authorityDto.getAuthorityId())
                .permission(authorityDto.getPermission())
                .build();
        if (authority.getRoles() != null &&
                !authority.getRoles().isEmpty()){
            authority.setRoles(authorityDto.getRoles()
                    .stream()
                    .map(roleDto -> Role.builder()
                            .roleId(roleDto.getRoleId())
                            .roleName(roleDto.getRoleName())
                            .build())
                    .collect(Collectors.toSet()));
        }
        return authority;

    }

    @Override
    public AuthorityDto authorityToAuthorityDto(Authority authority) {
        if(authority == null){
            return null;
        }
        AuthorityDto authorityDto = AuthorityDto.builder()
                .authorityId(authority.getAuthorityId())
                .permission(authority.getPermission())
                .build();
        if(authority.getRoles() != null &&
            !authority.getRoles().isEmpty()){
            authorityDto.setRoles(authority.getRoles().stream()
                    .map(role -> RoleDto.builder()
                                .roleId(role.getRoleId())
                                .roleName(role.getRoleName())
                                .build())
                    .collect(Collectors.toSet()));
        }
        return authorityDto;

    }

    @Override
    public Set<Authority> authorityDtosToAuthorities(Set<AuthorityDto> authorityDtos) {
        return authorityDtos
                .stream()
                .map(authorityDto -> authorityDtoToAuthority(authorityDto)).collect(Collectors.toSet());
    }

    @Override
    public Set<AuthorityDto> authoritiesToAuthorityDtos(Set<Authority> authorities) {
        return authorities
                .stream()
                .map(authority -> authorityToAuthorityDto(authority)).collect(Collectors.toSet());
    }
}
