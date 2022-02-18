package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.constants.ApiResponseConstants;
import com.brihaspathee.zeus.service.interfaces.AuthorityService;
import com.brihaspathee.zeus.web.resource.interfaces.AuthorityApi;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.brihaspathee.zeus.web.security.AuthorityDto;
import com.brihaspathee.zeus.web.security.AuthorityList;
import com.brihaspathee.zeus.web.security.RoleDto;
import com.brihaspathee.zeus.web.security.RoleList;
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
public class AuthorityResource implements AuthorityApi {

    private final AuthorityService authorityService;

    @Override
    public ResponseEntity<ZeusApiResponse<AuthorityList>> getAuthorities(UUID authorityId, String permission) {
        AuthorityList authorityList = new AuthorityList();
        if(authorityId!=null){
            AuthorityDto authorityDto = authorityService.getAuthorityById(authorityId);
            authorityList.setAuthorityList(List.of(authorityDto));
        }else if(permission!=null){
            AuthorityDto authorityDto = authorityService.getAuthorityByPermission(permission);
            authorityList.setAuthorityList(List.of(authorityDto));
        }else{
            List<AuthorityDto> authorityDtos = authorityService.getAllAuthorities().stream().toList();
            authorityList.setAuthorityList(authorityDtos);
        }
        ZeusApiResponse<AuthorityList> apiResponse = ZeusApiResponse.<AuthorityList>builder()
                .reason(ApiResponseConstants.SUCCESS_REASON)
                .message(ApiResponseConstants.SUCCESS)
                .response(authorityList)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ZeusApiResponse<AuthorityDto>> createAuthority(AuthorityDto authorityDto) {
        AuthorityDto savedAuthority = authorityService.createAuthority(authorityDto);
        ZeusApiResponse<AuthorityDto> apiResponse = ZeusApiResponse.<AuthorityDto>builder()
                .reason(ApiResponseConstants.SUCCESS_REASON)
                .message(ApiResponseConstants.SUCCESS)
                .response(savedAuthority)
                .build();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/tp/authority/"+savedAuthority.getAuthorityId());
        return new ResponseEntity<ZeusApiResponse<AuthorityDto>>(apiResponse, httpHeaders, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ZeusApiResponse<AuthorityDto>> updateAuthority(AuthorityDto authorityDto, UUID authorityId) {
        return null;
    }
}
