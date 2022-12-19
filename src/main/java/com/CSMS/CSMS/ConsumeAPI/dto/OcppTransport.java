package com.CSMS.CSMS.ConsumeAPI.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum OcppTransport {
    SOAP("S"),
    JSON("J");
    
    private final String value;

    public static OcppTransport fromValue(String v) {
        for (OcppTransport c: OcppTransport.values()) {
            if (c.getValue().equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public static OcppTransport fromName(String v) {
        for (OcppTransport c: OcppTransport.values()) {
            if (c.name().equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
