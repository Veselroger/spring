package mvc.rating;

import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Service to get rating information.
 */
@Service
public class MovieRatingService {
    private static final String API_URL = "http://www.omdbapi.com/?apikey=";

    @Autowired
    private RestTemplate restTemplate;

    @Value("${omdbapi.token:#{null}}")
    private String token;

    /**
     * Get movie rating by its title
     * @param title Movie title
     * @return Movie rating
     */
    public String getMovieRating(String title) {
        try {
            RestTemplate template = new RestTemplate();
            String url = API_URL + token + "&t=" + title;
            ResponseEntity<ObjectNode> response = template.getForEntity(url, ObjectNode.class);

            ObjectNode jsonObject = response.getBody();
            return jsonObject.path("imdbRating").asText();
        } catch (Exception e) {
            System.out.println("Something went wrong while calling OMDb API" + e.getMessage());
            return null;
        }
    }

}
