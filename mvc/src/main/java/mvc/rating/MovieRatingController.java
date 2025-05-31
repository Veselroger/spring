package mvc.rating;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MovieRatingController {
    private final MovieRatingService service;

    public MovieRatingController(MovieRatingService service) {
        this.service = service;
    }

    /**
     * Get Rating information by movie title.
     *
     * @param title Movie title
     * @return Movie rating information
     */
    @GetMapping("/fetch-rating")
    @ResponseBody
    public String fetchRatingForTitle(@RequestParam("title") String title) {
        return service.getMovieRating(title);
    }

}