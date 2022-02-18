package com.brihaspathee.zeus.mapper.interfaces;

import com.brihaspathee.zeus.domain.security.Authority;
import com.brihaspathee.zeus.web.security.AuthorityDto;

import java.util.Set;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 12, February 2022
 * Time: 10:04 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface AuthorityMapper {

    Authority authorityDtoToAuthority(AuthorityDto authorityDto);
    AuthorityDto authorityToAuthorityDto(Authority authority);

    Set<Authority> authorityDtosToAuthorities(Set<AuthorityDto> authorityDtos);
    Set<AuthorityDto> authoritiesToAuthorityDtos(Set<Authority> authorities);
}
