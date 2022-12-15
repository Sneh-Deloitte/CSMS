package com.CSMS.CSMS.ConsumeAPI.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.joda.time.LocalDateTime;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * @author Sevket Goekay <sevketgokay@gmail.com>
 * @since 15.08.2014
 */
@Getter
@Setter
@ToString

public class OcppTagForm {

        // Internal database id
        private Integer ocppTagPk;

        @NotEmpty(message = "ID Tag is required")
        @IdTag
        private String idTag;
    
        // Is a FK in DB table. No validation needed. Operation will fail if DB constraint fails.
        private String parentIdTag;
    
        @Future(message = "Expiry Date/Time must be in future")
        private LocalDateTime expiryDate;
    
        private Integer maxActiveTransactionCount;
    
        private String note;
    
        /**
         * As specified in V0_9_9__update.sql default value is 1.
         */
        public Integer getMaxActiveTransactionCount() {
            return Objects.requireNonNullElse(maxActiveTransactionCount, 1);
        }
    
}
