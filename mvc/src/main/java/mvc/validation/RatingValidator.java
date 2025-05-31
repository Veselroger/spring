package mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * A validator to validate movies rating information.
 */
public class RatingValidator implements ConstraintValidator<Rating, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            double number = Double.parseDouble(value);
            return number >= 1 && number <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
