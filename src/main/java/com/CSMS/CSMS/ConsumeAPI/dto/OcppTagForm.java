package com.CSMS.CSMS.ConsumeAPI.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Getter
@Setter
@ToString

public class OcppTagForm {
        private Integer ocppTagPk;

        @NotEmpty(message = "ID Tag is required")
        @IdTag
        private String idTag;
        private String parentIdTag;
    
        @Future(message = "Expiry Date/Time must be in future")
        private LocalDateTime expiryDate;
    
        private Integer maxActiveTransactionCount;
    
        private String note;
        public Integer getMaxActiveTransactionCount() {
            return Objects.requireNonNullElse(maxActiveTransactionCount, 1);
        }
    
}
