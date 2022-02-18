package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.repository.AuthorityRepository;
import com.brihaspathee.zeus.domain.security.Authority;
import com.brihaspathee.zeus.exception.AuthorityNotFoundException;
import com.brihaspathee.zeus.mapper.interfaces.AuthorityMapper;
import com.brihaspathee.zeus.service.interfaces.AuthorityService;
import com.brihaspathee.zeus.web.security.AuthorityDto;
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
 * Time: 11:14 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {

    private final AuthorityRepository authorityRepository;

    private final AuthorityMapper authorityMapper;

    @Override
    public AuthorityDto getAuthorityById(UUID authorityId) {
        Authority authority = authorityRepository.findById(authorityId).orElseThrow(() -> {
            throw new AuthorityNotFoundException("Authority with authority id " + authorityId + " not found.");
        });
        return authorityMapper.authorityToAuthorityDto(authority);
    }

    @Override
    public AuthorityDto getAuthorityByPermission(String permission) {
        Authority authority = authorityRepository.findAuthorityByPermission(permission).orElseThrow(() -> {
            throw new AuthorityNotFoundException("Authority with permission " + permission + " not found.");
        });
        return authorityMapper.authorityToAuthorityDto(authority);
    }

    @Override
    public Set<AuthorityDto> getAllAuthorities() {
        Set<Authority> authorities = authorityRepository.findAll().stream().collect(Collectors.toSet());
        return authorityMapper.authoritiesToAuthorityDtos(authorities);
    }

    @Override
    public AuthorityDto createAuthority(AuthorityDto authorityDto) {
        Authority authority = authorityMapper.authorityDtoToAuthority(authorityDto);
        authority = authorityRepository.save(authority);
        return authorityMapper.authorityToAuthorityDto(authority);
    }

    @Override
    public void deleteAuthority(UUID authorityId) {
        authorityRepository.deleteById(authorityId);
    }
}
