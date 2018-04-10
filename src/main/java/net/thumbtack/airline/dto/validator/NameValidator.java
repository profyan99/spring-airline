package net.thumbtack.airline.dto.validator;

import net.thumbtack.airline.Utils;
import net.thumbtack.airline.dto.validator.annotation.NameValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class NameValidator implements ConstraintValidator<NameValid, String> {

    private Utils utils;

    @Autowired
    public void setUtils(Utils utils) {
        this.utils = utils;
    }

    @Override
    public void initialize(NameValid nameValid) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !s.isEmpty() && s.matches(Utils.NAME_REGEX) && s.length() < utils.getMaxNameLength();
    }
}
