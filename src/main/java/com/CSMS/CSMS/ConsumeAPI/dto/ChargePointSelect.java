package com.CSMS.CSMS.ConsumeAPI.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public final class ChargePointSelect {
    private final OcppTransport ocppTransport;
    private final String chargeBoxId;
    private final String endpointAddress;

    public ChargePointSelect(OcppTransport ocppTransport, String chargeBoxId) {
        this(ocppTransport, chargeBoxId, "-");
    }

    public boolean isEndpointAddressSet() {
        return !("-".equals(endpointAddress));
    }

    public boolean isSoap() {
        return OcppTransport.SOAP == ocppTransport;
    }
}
