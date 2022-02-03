package com.brihaspathee.zeus.web.model;

import lombok.*;

import java.util.List;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 19, January 2022
 * Time: 3:10 PM
 * Project: Zeus
 * Package Name: com.zeus.web.model
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TradingPartnerList {

    private List<TradingPartnerDto> tradingPartnerDtos;

    @Override
    public String toString() {
        return "TradingPartnerList{" +
                "tradingPartnerDtos=" + tradingPartnerDtos +
                '}';
    }
}
