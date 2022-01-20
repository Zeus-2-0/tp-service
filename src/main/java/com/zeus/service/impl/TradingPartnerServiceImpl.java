package com.zeus.service.impl;

import com.zeus.domain.entity.TradingPartner;
import com.zeus.domain.repository.TradingPartnerRepository;
import com.zeus.exception.TradingPartnerNotFoundException;
import com.zeus.mapper.interfaces.TradingPartnerMapper;
import com.zeus.service.interfaces.TradingPartnerService;
import com.zeus.web.model.TradingPartnerDto;
import com.zeus.web.model.TradingPartnerList;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

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
@Service
@RequiredArgsConstructor
public class TradingPartnerServiceImpl implements TradingPartnerService {

    private final TradingPartnerRepository tradingPartnerRepository;

    private final TradingPartnerMapper tradingPartnerMapper;

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


}
