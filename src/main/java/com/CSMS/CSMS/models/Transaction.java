package com.CSMS.CSMS.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import org.joda.time.DateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    String chargeBoxIdentity;
    Integer connectorId;
    String idTag;
    DateTime startTimestamp;
    Integer meterStart;
    Integer reservationId;
    String statusOfTransaction;
    DateTime stopTimestamp;
    String stopReason;
    Integer meterStop;


    public Transaction(String chargeBoxIdentity, Integer connectorId, String idTag, DateTime startTimestamp,Integer MeterStart,Integer reservationId, String statusOfTransaction, DateTime stopTimestamp, String stopReason, Integer meterStop) {
        this.chargeBoxIdentity = chargeBoxIdentity;
        this.connectorId = connectorId;
        this.idTag = idTag;
        this.startTimestamp = startTimestamp;
        this.meterStart = MeterStart;
        this.reservationId = reservationId;
        this.statusOfTransaction=statusOfTransaction;
        this.stopTimestamp=stopTimestamp;
        this.stopReason=stopReason;
        this.meterStop=meterStop;
    }

}
