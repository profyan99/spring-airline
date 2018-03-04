package net.thumbtack.airline.dto.validator;

import net.thumbtack.airline.dto.validator.annotation.NameValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NameValidator implements ConstraintValidator<NameValid, String> {

    //TODO change max and min to values from properties

    @Override
    public void initialize(NameValid nameValid) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !s.isEmpty() && s.matches("^[а-яА-ЯёЁ\\s\\-]+$") && s.length() < 20 && s.length() > 6;
    }
}
