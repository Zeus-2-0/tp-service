package com.zeus.mapper.interfaces;

import com.zeus.domain.entity.TradingPartner;
import com.zeus.web.model.TradingPartnerDto;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 19, January 2022
 * Time: 4:23 PM
 * Project: Zeus
 * Package Name: com.zeus.mapper.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface TradingPartnerMapper {

    TradingPartnerDto tradingPartnerToTradingPartnerDto(TradingPartner tradingPartner);
    TradingPartner tradingPartnerDtoToTradingPartner(TradingPartnerDto tradingPartnerDto);

    List<TradingPartner> tradingPartnerDtoListToTradingPartnerList(List<TradingPartnerDto> tradingPartnerDtos);
    List<TradingPartnerDto> tradingPartnerListToTradingPartnerDtoList(List<TradingPartner> tradingPartners);
}
