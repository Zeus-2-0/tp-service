package com.brihaspathee.zeus.domain.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Created in Intellij IDEA
 * User: Balaji Varadharajan
 * Date: 19, January 2022
 * Time: 2:54 PM
 * Project: Zeus
 * Package Name: com.zeus.domain.entity
 * To change this template use File | Settings | File and Code Template
 */
@Slf4j
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trading_partner_detail")
public class TradingPartner {

    /**
     * Primary key of the table
     */
    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "trading_partner_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID tradingPartnerSK;

    /**
     * The unique id of the trading partner
     */
    @Column(name = "trading_partner_id", nullable = false, length = 100)
    private String tradingPartnerId;

    /**
     * Name of the trading partner
     */
    @Column(name = "name", nullable = false, length = 100)
    private String name;

    /**
     * Description of the trading partner
     */
    @Column(name = "description", nullable = true, length = 100)
    private String description;

    /**
     * Sender id associated with the trading partner
     */
    @Column(name = "sender_id", nullable = false, length = 100)
    private String senderId;

    /**
     * Receiver id associated with the trading partner
     */
    @Column(name = "receiver_id", nullable = false, length = 100)
    private String receiverId;

    /**
     * The line of business associated with the trading partner
     */
    @Column(name = "line_of_business_type_code", nullable = false, length = 10)
    private String lineOfBusinessTypeCode;

    /**
     * The business unit associated with the trading partner
     */
    @Column(name = "business_unit_type_code", nullable = false, length = 10)
    private String businessUnitTypeCode;

    /**
     * The state type code associated with the trading partner
     */
    @Column(name = "state_type_code", nullable = false, length = 10)
    private String stateTypeCode;

    /**
     * Marketplace type code associated with the trading partner
     */
    @Column(name = "marketplace_type_code", nullable = false, length = 10)
    private String marketplaceTypeCode;

    /**
     * Date when the record was created
     */
    @CreationTimestamp
    @Column(name = "created_date", nullable = true)
    private LocalDateTime createdDate;

    /**
     * Date when the record was updated
     */
    @UpdateTimestamp
    @Column(name = "updated_date", nullable = true)
    private LocalDateTime updatedDate;


    @Override
    public String toString() {
        return "TradingPartner{" +
                "tradingPartnerSK=" + tradingPartnerSK +
                ", tradingPartnerId='" + tradingPartnerId + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", senderId='" + senderId + '\'' +
                ", receiverId='" + receiverId + '\'' +
                ", lineOfBusinessTypeCode='" + lineOfBusinessTypeCode + '\'' +
                ", stateTypeCode='" + stateTypeCode + '\'' +
                ", marketplaceTypeCode='" + marketplaceTypeCode + '\'' +
                ", createdDate=" + createdDate +
                ", updatedDate=" + updatedDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TradingPartner that = (TradingPartner) o;
        return tradingPartnerSK.equals(that.tradingPartnerSK);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tradingPartnerSK);
    }

}
