package mvc.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import mvc.watchList.WatchlistItem;

/**
 * A validator to validate good movies for sufficiency of information.
 */
public class GoodMovieValidator implements ConstraintValidator<GoodMovie, WatchlistItem> {
    @Override
    public boolean isValid(WatchlistItem value, ConstraintValidatorContext context) {
        try {
            double rating = Double.parseDouble(value.rating());
            if (rating >= 8) return !value.comment().isEmpty();
        } catch (Exception e) {
            return true;
        }
        return true;
    }
}
