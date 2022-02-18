package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.constants.ApiResponseConstants;
import com.brihaspathee.zeus.service.interfaces.RoleService;
import com.brihaspathee.zeus.web.resource.interfaces.RoleApi;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.brihaspathee.zeus.web.security.RoleDto;
import com.brihaspathee.zeus.web.security.RoleList;
import com.brihaspathee.zeus.web.security.UserDto;
import com.brihaspathee.zeus.web.security.UserList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    private RoleService roleService;

    @Override
    public ResponseEntity<ZeusApiResponse<RoleList>> getRoles(UUID roleId, String roleName) {
        RoleList roleList = new RoleList();
        if(roleId != null){
            RoleDto roleDto = roleService.getRoleById(roleId);
            roleList.setRoleDtoList(List.of(roleDto));
        }else if (roleName != null){
            RoleDto roleDto = roleService.getRoleByName(roleName);
            roleList.setRoleDtoList(List.of(roleDto));
        }else{
            List<RoleDto> roleDtos = roleService.getAllRoles().stream().toList();
            roleList.setRoleDtoList(roleDtos);
        }
        ZeusApiResponse<RoleList> apiResponse = ZeusApiResponse.<RoleList>builder()
                .reason(ApiResponseConstants.SUCCESS_REASON)
                .message(ApiResponseConstants.SUCCESS)
                .response(roleList)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ZeusApiResponse<RoleDto>> createRole(RoleDto roleDto) {
        RoleDto savedRole = roleService.createRole(roleDto);
        ZeusApiResponse<RoleDto> apiResponse = ZeusApiResponse.<RoleDto>builder()
                .reason(ApiResponseConstants.SUCCESS_REASON)
                .message(ApiResponseConstants.SUCCESS)
                .response(savedRole)
                .build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/tp/role/"+savedRole.getRoleId());
        return new ResponseEntity<ZeusApiResponse<RoleDto>>(apiResponse, httpHeaders, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ZeusApiResponse<RoleDto>> updateRole(RoleDto roleDto, UUID roleId) {
        return null;
    }
}
