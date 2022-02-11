package com.brihaspathee.zeus.service.impl;

import com.brihaspathee.zeus.domain.entity.TradingPartner;
import com.brihaspathee.zeus.exception.TradingPartnerNotFoundException;
import com.brihaspathee.zeus.mapper.interfaces.TradingPartnerMapper;
import com.brihaspathee.zeus.service.interfaces.ReferenceDataService;
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


    private final TradingPartnerRepository tradingPartnerRepository;

    private final TradingPartnerMapper tradingPartnerMapper;

    private final ReferenceDataService referenceDataService;


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
        validateReferenceData(tradingPartnerDto);
        TradingPartner tradingPartner = tradingPartnerMapper.tradingPartnerDtoToTradingPartner(tradingPartnerDto);
        if(tradingPartnerDto.getTradingPartnerSK()!=null){
            TradingPartner currentTP = tradingPartnerRepository.getById(tradingPartnerDto.getTradingPartnerSK());
            tradingPartner.setCreatedDate(currentTP.getCreatedDate());
        }

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
        referenceDataService.lookupReferenceData(internalRefDataRequestList);

    }
}
