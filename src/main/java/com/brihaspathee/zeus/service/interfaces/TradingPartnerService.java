package com.brihaspathee.zeus.service.interfaces;

import com.brihaspathee.zeus.web.model.TradingPartnerDto;
import com.brihaspathee.zeus.web.model.TradingPartnerList;
import org.springframework.data.domain.PageRequest;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 19, January 2022
 * Time: 4:16 PM
 * Project: Zeus
 * Package Name: com.zeus.service.interfaces
 * To change this template use File | Settings | File and Code Template
 */
public interface TradingPartnerService {

    TradingPartnerList getAllTradingPartners();
    TradingPartnerList getTradingPartnersSorted(PageRequest pageRequest);
    TradingPartnerDto savingTradingPartner(TradingPartnerDto tradingPartnerDto);
    TradingPartnerDto getTradingPartnerById(String tradingPartnerId);
    TradingPartnerDto getTradingPartnerBySenderAndReceiverId(String senderId, String receiverId);
}
