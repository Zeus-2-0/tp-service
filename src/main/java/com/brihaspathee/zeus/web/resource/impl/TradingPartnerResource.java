package com.brihaspathee.zeus.web.resource.impl;

import com.brihaspathee.zeus.constants.ApiResponseConstants;
import com.brihaspathee.zeus.service.interfaces.TradingPartnerService;
import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
import com.brihaspathee.zeus.web.resource.interfaces.TradingPartnerApi;
import com.brihaspathee.zeus.web.response.InternalRefDataResponse;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<ZeusApiResponse<TradingPartnerList>> getAllTradingPartners() {
        TradingPartnerList tradingPartnerList = tradingPartnerService.getAllTradingPartners();
        ZeusApiResponse<TradingPartnerList> apiResponse = ZeusApiResponse.<TradingPartnerList>builder()
                .response(tradingPartnerList)
                .message(ApiResponseConstants.SUCCESS)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity<ZeusApiResponse<TradingPartnerList>> getAllSortedTradingPartners(Optional<Integer> page,
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
        ZeusApiResponse<TradingPartnerList> apiResponse = ZeusApiResponse.<TradingPartnerList>builder()
                .response(tradingPartnerList)
                .message(ApiResponseConstants.SUCCESS)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public ResponseEntity createTradingPartner(TradingPartnerDto tradingPartnerDto) {
        TradingPartnerDto savedTP = tradingPartnerService.savingTradingPartner(tradingPartnerDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location", "/api/v1/tp/"+savedTP.getTradingPartnerSK());
        return new ResponseEntity(httpHeaders, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updateTradingPartner(TradingPartnerDto tradingPartnerDto, UUID tradingPartnerSK) {
        return null;
    }

    @Override
    public ResponseEntity<ZeusApiResponse<TradingPartnerDto>> getTradingPartnerById(String tradingPartnerId) {
        return null;
    }


}
