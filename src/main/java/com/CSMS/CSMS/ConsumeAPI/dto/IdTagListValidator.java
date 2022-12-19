package com.CSMS.CSMS.ConsumeAPI.dto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class IdTagListValidator implements ConstraintValidator<IdTag, List<String>> {

    private static final IdTagValidator VALIDATOR = new IdTagValidator();

    @Override
    public void initialize(IdTag constraintAnnotation) {
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
