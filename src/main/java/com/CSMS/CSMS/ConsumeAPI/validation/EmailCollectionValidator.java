package com.CSMS.CSMS.ConsumeAPI.validation;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Collection;

public class EmailCollectionValidator implements ConstraintValidator<EmailCollection, Collection<String>> {

    private static final EmailValidator VALIDATOR = new EmailValidator();

    @Override
    public void initialize(EmailCollection constraintAnnotation) {

    }

    @Override
    public boolean isValid(Collection<String> value, ConstraintValidatorContext context) {
        for (String s : value) {
            if (!VALIDATOR.isValid(s, context)) {
                return false;
            }
        }
        return true;
    }
}
