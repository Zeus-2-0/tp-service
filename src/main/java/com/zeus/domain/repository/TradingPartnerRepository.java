package com.zeus.domain.repository;

import com.zeus.domain.entity.TradingPartner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 19, January 2022
 * Time: 3:01 PM
 * Project: Zeus
 * Package Name: com.zeus.domain.repository
 * To change this template use File | Settings | File and Code Template
 */
@Repository
public interface TradingPartnerRepository extends JpaRepository<TradingPartner, UUID> {

    Optional<TradingPartner> findTradingPartnerByTradingPartnerId(String tradingPartnerId);

    Optional<TradingPartner> findTradingPartnerBySenderIdAndReceiverId(String senderId, String receiverId);
}
