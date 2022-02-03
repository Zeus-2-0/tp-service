package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.entity.TradingPartner;
import com.brihaspathee.zeus.exception.TradingPartnerNotFoundException;
import com.brihaspathee.zeus.mapper.interfaces.TradingPartnerMapper;
import com.brihaspathee.zeus.web.response.InternalRefDataResponseList;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.brihaspathee.zeus.domain.repository.TradingPartnerRepository;
import com.brihaspathee.zeus.service.interfaces.TradingPartnerService;
import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
import com.brihaspathee.zeus.web.request.InternalRefDataRequest;
import com.brihaspathee.zeus.web.request.InternalRefDataRequestList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 19, January 2022
 * Time: 4:16 PM
 * Project: Zeus
 * Package Name: com.zeus.service.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TradingPartnerServiceImpl implements TradingPartnerService {

    @Value("${url.host.ref-data}")
    private String refDataHost;

    private final TradingPartnerRepository tradingPartnerRepository;

    private final TradingPartnerMapper tradingPartnerMapper;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    @Override
    public TradingPartnerList getAllTradingPartners() {
        List<TradingPartner> tradingPartners = tradingPartnerRepository.findAll();
        return getTradingPartnerList(tradingPartners);
    }



    @Override
    public TradingPartnerList getTradingPartnersSorted(PageRequest pageRequest) {
        List<TradingPartner> tradingPartners = tradingPartnerRepository.findAll(pageRequest).stream().toList();
        return getTradingPartnerList(tradingPartners);
    }

    @Override
    public TradingPartnerDto savingTradingPartner(TradingPartnerDto tradingPartnerDto) {
        log.info("Ref-data host: {}", refDataHost );
        validateReferenceData(tradingPartnerDto);
        TradingPartner tradingPartner = tradingPartnerMapper.tradingPartnerDtoToTradingPartner(tradingPartnerDto);
        tradingPartner = tradingPartnerRepository.save(tradingPartner);
        return tradingPartnerMapper.tradingPartnerToTradingPartnerDto(tradingPartner);
    }



    @Override
    public TradingPartnerDto getTradingPartnerById(String tradingPartnerId) {
        Optional<TradingPartner> optionalTradingPartner = tradingPartnerRepository.findTradingPartnerByTradingPartnerId(tradingPartnerId);
        return getTradingPartnerDto(optionalTradingPartner);
    }

    @Override
    public TradingPartnerDto getTradingPartnerBySenderAndReceiverId(String senderId, String receiverId) {
        Optional<TradingPartner> optionalTradingPartner = tradingPartnerRepository.findTradingPartnerBySenderIdAndReceiverId(senderId,receiverId);
        return getTradingPartnerDto(optionalTradingPartner);
    }

    private TradingPartnerDto getTradingPartnerDto(Optional<TradingPartner> optionalTradingPartner) {
        if(optionalTradingPartner.isEmpty()){
            throw new TradingPartnerNotFoundException("Trading partner not found");
        }
        TradingPartner tradingPartner = optionalTradingPartner.get();
        return tradingPartnerMapper.tradingPartnerToTradingPartnerDto(tradingPartner);
    }

    private TradingPartnerList getTradingPartnerList(List<TradingPartner> tradingPartners) {
        List<TradingPartnerDto> tradingPartnerDtos = tradingPartnerMapper.tradingPartnerListToTradingPartnerDtoList(tradingPartners);
        return TradingPartnerList.builder().tradingPartnerDtos(tradingPartnerDtos).build();
    }

    private void validateReferenceData(TradingPartnerDto tradingPartnerDto) {
        log.info("Inside Validate Reference data:{}", tradingPartnerDto);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        InternalRefDataRequestList internalRefDataRequestList = InternalRefDataRequestList.builder()
                .internalRefDataRequestList(List.of(InternalRefDataRequest.builder()
                                .internalListTypeName("LineOfBusiness")
                                .internalListCode(tradingPartnerDto.getLineOfBusinessTypeCode())
                                .build(),
                        InternalRefDataRequest.builder()
                                .internalListTypeName("Marketplace")
                                .internalListCode(tradingPartnerDto.getMarketplaceTypeCode())
                                .build(),
                        InternalRefDataRequest.builder()
                                .internalListTypeName("State")
                                .internalListCode(tradingPartnerDto.getStateTypeCode())
                                .build()))
                .build();

        HttpEntity httpEntity = new HttpEntity(internalRefDataRequestList, headers);
        ZeusApiResponse apiResponse = restTemplate.postForObject(refDataHost+"internal/refdata/list",httpEntity, ZeusApiResponse.class);
        log.info("API Response:{}", apiResponse);
        if (apiResponse.getMessage().equals("Success")){
            InternalRefDataResponseList responseList = objectMapper.convertValue(apiResponse.getResponse(), InternalRefDataResponseList.class);
            log.info("Internal Ref Data Response:{}", responseList);
        }

    }
}
