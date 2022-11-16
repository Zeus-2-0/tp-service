package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.service.interfaces.ReferenceDataService;
import com.brihaspathee.zeus.web.request.InternalRefDataRequestList;
import com.brihaspathee.zeus.web.response.InternalRefDataResponseList;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 05, February 2022
 * Time: 10:06 AM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ReferenceDataServiceImpl implements ReferenceDataService {

    @Value("${url.host.ref-data}")
    private String refDataHost;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    @Override
    public InternalRefDataResponseList lookupReferenceData(InternalRefDataRequestList internalRefDataRequestList) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity httpEntity = new HttpEntity(internalRefDataRequestList, headers);
        ZeusApiResponse apiResponse = restTemplate.postForObject(refDataHost+"ref-data/internal/list",httpEntity, ZeusApiResponse.class);
        log.info("API Response:{}", apiResponse);
        if (apiResponse.getMessage().equals("Success")){
            InternalRefDataResponseList responseList = objectMapper.convertValue(apiResponse.getResponse(), InternalRefDataResponseList.class);
            log.info("Internal Ref Data Response:{}", responseList);
            return responseList;
        }else{
            return null;
        }
    }
}
