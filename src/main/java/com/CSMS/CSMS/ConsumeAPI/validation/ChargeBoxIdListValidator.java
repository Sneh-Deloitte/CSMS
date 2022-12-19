package com.CSMS.CSMS.ConsumeAPI.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ChargeBoxIdListValidator implements ConstraintValidator<ChargeBoxId, List<String>> {

    private static final ChargeBoxIdValidator VALIDATOR = new ChargeBoxIdValidator();

    @Override
    public void initialize(ChargeBoxId constraintAnnotation) {
    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        for (String s : value) {
            if (!VALIDATOR.isValid(s, context)) {
                return false;
            }
        }
        return true;
    }
}
