package net.thumbtack.airline.dto.validator;

import net.thumbtack.airline.dto.validator.annotation.TimeValid;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class TimeValidator implements ConstraintValidator<TimeValid, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s != null && !s.isEmpty() && s.matches("^(2[0-3]|[0-1]\\d):[0-5]\\d$");
    }

    @Override
    public void initialize(TimeValid constraintAnnotation) {

    }
}
