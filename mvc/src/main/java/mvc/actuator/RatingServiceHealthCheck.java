package mvc.actuator;

import mvc.rating.MovieRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * A health check to check {@link MovieRatingService} availability.
 * Extends <a href=https://docs.spring.io/spring-boot/api/rest/actuator/health.html>Health Actuator endpoint</a>.
 *
 * @see <a href=https://docs.spring.io/spring-boot/reference/actuator/endpoints.html#actuator.endpoints.health.writing-custom-health-indicators>Custom health indicators</a>
 */
@Component
public class RatingServiceHealthCheck implements HealthIndicator {

    @Autowired
    private MovieRatingService ratingService;

    @Override
    public Health health() {
        if (ratingService.getMovieRating("Up") == null) {
            return Health.down().withDetail("Cause", "OMDb API is not available").build();
        }
        return Health.up().build();
    }
}
