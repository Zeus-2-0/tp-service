package com.zeus.web.resource.impl;

import com.zeus.service.interfaces.TradingPartnerService;
import com.zeus.web.model.TradingPartnerDto;
import com.zeus.web.model.TradingPartnerList;
import com.zeus.web.resource.interfaces.TradingPartnerApi;
import com.zeus.web.response.TradingPartnerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 14, January 2022
 * Time: 9:52 AM
 * Project: Zeus
 * Package Name: com.zeus.web.resource.impl
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class TradingPartnerResource implements TradingPartnerApi {

    private final TradingPartnerService tradingPartnerService;

    @Override
    public ResponseEntity<TradingPartnerList> getAllTradingPartners() {
        TradingPartnerList tradingPartnerList = tradingPartnerService.getAllTradingPartners();
        return ResponseEntity.ok(tradingPartnerList);
    }

    @Override
    public ResponseEntity<TradingPartnerList> getAllSortedTradingPartners(Optional<Integer> page,
                                                                          Optional<Integer> pageSize,
                                                                          Optional<String> sortBy) {
        log.info("page:{}, pageSize:{}, sortBy:{}", page, pageSize, sortBy);
        TradingPartnerList tradingPartnerList = tradingPartnerService
                .getTradingPartnersSorted(
                        PageRequest.of(
                                page.orElse(0),
                                pageSize.orElse(5),
                                Sort.Direction.ASC,
                                sortBy.orElse("tradingPartnerId")));
        return ResponseEntity.ok(tradingPartnerList);
    }

    @Override
    public ResponseEntity createTradingPartner(TradingPartnerDto tradingPartnerDto) {
        return null;
    }

    @Override
    public ResponseEntity updateTradingPartner(TradingPartnerDto tradingPartnerDto, UUID tradingPartnerSK) {
        return null;
    }

    @Override
    public ResponseEntity<TradingPartnerList> getTradingPartnerById(String tradingPartnerId) {
        return null;
    }
}
