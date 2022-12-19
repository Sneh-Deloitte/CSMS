package com.CSMS.CSMS.ConsumeAPI.dto;


import com.CSMS.CSMS.ConsumeAPI.validation.IdTag;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
public class ReserveNowParams extends SingleChargePointSelect {

    @NotNull(message = "Connector ID is required")
    @Min(value = 1, message = "Connector ID must be at least {value}")
    private Integer connectorId;

    @NotNull(message = "Expiry Date/Time is required")
    @Future(message = "Expiry Date/Time must be in future")
    private LocalDateTime expiry;

    @NotBlank(message = "User ID Tag is required.")
    @IdTag
    private String idTag;

}
