package com.CSMS.CSMS.ConsumeAPI.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class ChargeBoxIdValidator implements ConstraintValidator<ChargeBoxId, String> {

    private static final String REGEX = "\\S+";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    @Override
    public void initialize(ChargeBoxId idTag) {
    }

    @Override
    public boolean isValid(String string, ConstraintValidatorContext constraintValidatorContext) {
        return string == null || PATTERN.matcher(string).matches();
    }
}
