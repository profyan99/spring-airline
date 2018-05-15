package net.thumbtack.airline.dto.validator;

import net.thumbtack.airline.Utils;
import net.thumbtack.airline.dto.validator.annotation.PasswordValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PasswordValidator implements ConstraintValidator<PasswordValid, String> {
    private Utils utils;

    @Autowired
    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    @Override
    public void initialize(PasswordValid passwordValid) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !s.isEmpty() && s.length() >= utils.getMinPasswordLength();
    }
}

