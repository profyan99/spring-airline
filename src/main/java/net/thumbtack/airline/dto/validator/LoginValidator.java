package net.thumbtack.airline.dto.validator;

import net.thumbtack.airline.dto.validator.annotation.LoginValid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LoginValidator implements ConstraintValidator<LoginValid, String> {
    @Override
    public void initialize(LoginValid loginValid) {

    }

    //TODO change max and min to values from properties
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !s.isEmpty() && s.matches("^[а-яА-ЯёЁa-zA-Z0-9]+$") && s.length() > 6 && s.length() < 20;
    }
}
