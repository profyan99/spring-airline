package net.thumbtack.airline.dto.validator;

import net.thumbtack.airline.Utils;
import net.thumbtack.airline.dto.validator.annotation.LoginValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class LoginValidator implements ConstraintValidator<LoginValid, String> {

    private Utils utils;

    @Autowired
    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    @Override
    public void initialize(LoginValid loginValid) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !s.isEmpty() && s.matches("^[а-яА-ЯёЁa-zA-Z0-9]+$") && s.length() < utils.getMaxNameLength();
    }
}
