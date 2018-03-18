package net.thumbtack.airline.dto.validator;

import net.thumbtack.airline.ConstantsSetting;
import net.thumbtack.airline.dto.validator.annotation.PatronymicValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class PatronymicValidator implements ConstraintValidator<PatronymicValid, String> {
    private ConstantsSetting constantsSetting;

    @Autowired
    public void setConstantsSetting(ConstantsSetting constantsSetting) {
        this.constantsSetting = constantsSetting;
    }

    @Override
    public void initialize(PatronymicValid patronymicValid) {

    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s == null || s.isEmpty() || s.matches(ConstantsSetting.NAME_REGEX) && s.length() < constantsSetting.getMaxNameLength();
    }
}
