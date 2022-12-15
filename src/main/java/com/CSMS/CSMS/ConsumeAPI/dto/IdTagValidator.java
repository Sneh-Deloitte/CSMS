package com.CSMS.CSMS.ConsumeAPI.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Allowed characters are:
 * Upper or lower case letters, numbers and dot, colon, dash, underscore symbols.
 *
 * @author Sevket Goekay <sevketgokay@gmail.com>
 * @since 15.08.2014
 */
public class IdTagValidator implements ConstraintValidator<IdTag, String> {

    private static final String IDTAG_PATTERN = "^[a-zA-Z0-9.:_-]{1,20}$";
    private static final Pattern PATTERN = Pattern.compile(IDTAG_PATTERN);

    @Override
    public void initialize(IdTag idTag) {
        // No-op
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        return string == null || PATTERN.matcher(string).matches();
    }
}