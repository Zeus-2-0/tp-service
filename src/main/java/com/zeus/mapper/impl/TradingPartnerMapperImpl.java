package com.zeus.mapper.impl;

import com.zeus.domain.entity.TradingPartner;
import com.zeus.mapper.interfaces.TradingPartnerMapper;
import com.zeus.web.model.TradingPartnerDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 19, January 2022
 * Time: 4:30 PM
 * Project: Zeus
 * Package Name: com.zeus.mapper.impl
 * To change this template use File | Settings | File and Code Template
 */
@Component
public class TradingPartnerMapperImpl implements TradingPartnerMapper {
    @Override
    public TradingPartnerDto tradingPartnerToTradingPartnerDto(TradingPartner tradingPartner) {
        if(tradingPartner == null){
            return null;
        }
        TradingPartnerDto tradingPartnerDto = TradingPartnerDto.builder()
                .tradingPartnerSK(tradingPartner.getTradingPartnerSK())
                .tradingPartnerId(tradingPartner.getTradingPartnerId())
                .description(tradingPartner.getDescription())
                .name(tradingPartner.getName())
                .senderId(tradingPartner.getSenderId())
                .receiverId(tradingPartner.getReceiverId())
                .lineOfBusinessTypeCode(tradingPartner.getLineOfBusinessTypeCode())
                .marketplaceTypeCode(tradingPartner.getMarketplaceTypeCode())
                .stateTypeCode(tradingPartner.getStateTypeCode())
                .build();
        return tradingPartnerDto;
    }

    @Override
    public TradingPartner tradingPartnerDtoToTradingPartner(TradingPartnerDto tradingPartnerDto) {

        if(tradingPartnerDto == null){
            return null;
        }
        TradingPartner tradingPartner = TradingPartner.builder()
                .tradingPartnerSK(tradingPartnerDto.getTradingPartnerSK())
                .tradingPartnerId(tradingPartnerDto.getTradingPartnerId())
                .description(tradingPartnerDto.getDescription())
                .name(tradingPartnerDto.getName())
                .senderId(tradingPartnerDto.getSenderId())
                .receiverId(tradingPartnerDto.getReceiverId())
                .lineOfBusinessTypeCode(tradingPartnerDto.getLineOfBusinessTypeCode())
                .marketplaceTypeCode(tradingPartnerDto.getMarketplaceTypeCode())
                .stateTypeCode(tradingPartnerDto.getStateTypeCode())
                .build();
        return tradingPartner;
    }

    @Override
    public List<TradingPartner> tradingPartnerDtoListToTradingPartnerList(List<TradingPartnerDto> tradingPartnerDtos) {
        return tradingPartnerDtos.stream().map(this::tradingPartnerDtoToTradingPartner).collect(Collectors.toList());
    }

    @Override
    public List<TradingPartnerDto> tradingPartnerListToTradingPartnerDtoList(List<TradingPartner> tradingPartners) {
        return tradingPartners.stream().map(this::tradingPartnerToTradingPartnerDto).collect(Collectors.toList());
    }
}
