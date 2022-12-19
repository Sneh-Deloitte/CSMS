package com.CSMS.CSMS.ConsumeAPI.dto;

import com.neovisionaries.i18n.CountryCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class Address {

    // Internal database id
    private Integer addressPk;

    private String street;
    private String houseNumber;
    private String zipCode;
    private String city;
    private CountryCode country;

    public boolean isEmpty() {
        return addressPk == null
                && street == null
                && houseNumber == null
                && zipCode == null
                && city == null
                && country == null;
    }

    public String getCountryAlpha2OrNull() {
        if (country == null) {
            return null;
        } else {
            return country.getAlpha2();
        }
    }
}
