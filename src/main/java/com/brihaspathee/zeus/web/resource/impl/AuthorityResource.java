package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.web.resource.interfaces.AuthorityApi;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.brihaspathee.zeus.web.security.AuthorityDto;
import com.brihaspathee.zeus.web.security.AuthorityList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 11, February 2022
 * Time: 6:44 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthorityResource implements AuthorityApi {
    @Override
    public ResponseEntity<ZeusApiResponse<AuthorityList>> getAuthorities(UUID authorityId, String authorityName) {
        return null;
    }

    @Override
    public ResponseEntity<ZeusApiResponse<AuthorityDto>> createAuthority(AuthorityDto authorityDto) {
        return null;
    }

    @Override
    public ResponseEntity<ZeusApiResponse<AuthorityDto>> updateAuthority(AuthorityDto authorityDto, UUID authorityId) {
        return null;
    }
}
