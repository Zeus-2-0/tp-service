package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.web.resource.interfaces.RoleApi;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.brihaspathee.zeus.web.security.RoleDto;
import com.brihaspathee.zeus.web.security.RoleList;
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
public class RoleResource implements RoleApi {
    @Override
    public ResponseEntity<ZeusApiResponse<RoleList>> getRoles(UUID roleId, String roleName) {
        return null;
    }

    @Override
    public ResponseEntity<ZeusApiResponse<RoleDto>> createRole(RoleDto roleDto) {
        return null;
    }

    @Override
    public ResponseEntity<ZeusApiResponse<RoleDto>> updateRole(RoleDto roleDto, UUID roleId) {
        return null;
    }
}
