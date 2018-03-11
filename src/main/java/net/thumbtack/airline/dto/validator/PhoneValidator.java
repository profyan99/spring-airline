package net.thumbtack.airline.dto.validator;

import net.thumbtack.airline.dto.validator.annotation.PhoneValid;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PhoneValidator implements ConstraintValidator<PhoneValid, String> {

    @Override
    public void initialize(PhoneValid phoneValid) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !s.isEmpty() && s.matches("^((8|\\+7)[\\- ]?)(\\(?\\d{3}\\)?[\\- ]?)[\\d\\- ]{10}$");
    }
}
