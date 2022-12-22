package com.CSMS.CSMS.ConsumeAPI.dto;

import com.CSMS.CSMS.ConsumeAPI.validation.ChargeBoxId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
@Getter
@Setter
@ToString
public class ChargePointForm {

    private Integer chargeBoxPk;

    @NotBlank(message = "ChargeBox ID is required")
    @ChargeBoxId
    private String chargeBoxId;

    @NotBlank(message = "Registration status is required")
    private String registrationStatus;

    @NotNull
    private Boolean insertConnectorStatusAfterTransactionMsg;

    private Address address;

    @Range(min = -90, max = 90, message = "Latitude must be between {min} and {max}")
    private BigDecimal locationLatitude;

    @Range(min = -180, max = 180, message = "Longitude must be between {min} and {max}")
    private BigDecimal locationLongitude;

    private String description;
    private String note;

    @URL(message = "Admin address must be a valid URL")
    private String adminAddress;

}
