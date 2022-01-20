package com.zeus.domain.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

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
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trading_partner_detail")
public class TradingPartner {

    @Id
    @GeneratedValue(generator = "UUID")
    @Type(type = "uuid-char")
    @GenericGenerator(name="UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "trading_partner_sk", length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    private UUID tradingPartnerSK;

    @Column(name = "trading_partner_id", nullable = false, length = 100)
    private String tradingPartnerId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", nullable = true, length = 100)
    private String description;

    @Column(name = "sender_id", nullable = false, length = 100)
    private String senderId;

    @Column(name = "receiver_id", nullable = false, length = 100)
    private String receiverId;

    @Column(name = "line_of_business_type_code", nullable = false, length = 10)
    private String lineOfBusinessTypeCode;

    @Column(name = "state_type_code", nullable = false, length = 10)
    private String stateTypeCode;

    @Column(name = "marketplace_type_code", nullable = false, length = 10)
    private String marketplaceTypeCode;

    @CreationTimestamp
    @Column(name = "created_date", nullable = true)
    private LocalDateTime createdDate;

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
