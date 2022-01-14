package com.zeus.web.resource.impl;

import com.zeus.web.resource.interfaces.TradingPartnerApi;
import com.zeus.web.response.TradingPartnerResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

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
    @Override
    public ResponseEntity<TradingPartnerResponse> getAllTradingPartners() {
        TradingPartnerResponse tradingPartnerResponse = TradingPartnerResponse.builder()
                .sampleResponse("Sample Trading Partner")
                .build();
        return ResponseEntity.ok(tradingPartnerResponse);
    }
}
