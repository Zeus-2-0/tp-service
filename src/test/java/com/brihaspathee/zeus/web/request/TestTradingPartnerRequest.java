package com.brihaspathee.zeus.web.request;

import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
import com.brihaspathee.zeus.web.response.ZeusApiResponse;
import com.brihaspathee.zeus.web.security.UserDto;
import lombok.*;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 03, February 2022
 * Time: 3:18 PM
 * Project: Zeus
 * Package Name: com.brihaspathee.zeus.web.request
 * To change this template use File | Settings | File and Code Template
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TestTradingPartnerRequest {

    private boolean exceptionExpected;

    private String exceptionCode;

    private String exceptionMessage;

    private String httpStatusCode;

    private UserDto userDto;

    private TradingPartnerDto tradingPartnerDto;

    private TradingPartnerList tradingPartnerListResponse;

    private TradingPartnerDto tradingPartnerDtoResponse;


    @Override
    public String toString() {
        return "TestTradingPartnerRequest{" +
                "exceptionExpected=" + exceptionExpected +
                ", exceptionCode='" + exceptionCode + '\'' +
                ", exceptionMessage='" + exceptionMessage + '\'' +
                ", httpStatusCode='" + httpStatusCode + '\'' +
                ", userDto=" + userDto +
                ", tradingPartnerDto=" + tradingPartnerDto +
                ", tradingPartnerListResponse=" + tradingPartnerListResponse +
                ", tradingPartnerDtoResponse=" + tradingPartnerDtoResponse +
                '}';
    }
}
