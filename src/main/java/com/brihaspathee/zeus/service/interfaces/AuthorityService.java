package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.web.security.AuthorityDto;

import java.util.Set;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 17, February 2022
 * Time: 11:13 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface AuthorityService {

    AuthorityDto getAuthorityById(UUID authorityId);
    AuthorityDto getAuthorityByPermission(String permission);
    Set<AuthorityDto> getAllAuthorities();
    AuthorityDto createAuthority(AuthorityDto authorityDto);
    void deleteAuthority(UUID authorityId);
}
