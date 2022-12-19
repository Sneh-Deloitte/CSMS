package com.CSMS.CSMS.ConsumeAPI.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD, METHOD})
@Retention(RUNTIME)
@Constraint(validatedBy = {IdTagValidator.class, IdTagListValidator.class})
public @interface IdTag {

    String message() default "ID Tag can only contain upper or lower case letters, numbers and dot, colon, dash, underscore symbols";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
