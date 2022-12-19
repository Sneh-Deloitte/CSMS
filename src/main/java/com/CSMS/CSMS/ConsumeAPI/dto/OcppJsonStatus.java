package com.CSMS.CSMS.ConsumeAPI.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public final class OcppJsonStatus {
    private  int chargeBoxPk;
    private  String chargeBoxId, connectedSince;
    private  String connectionDuration;
    private  OcppVersion version;
    private  String connectedSinceDT;
}
