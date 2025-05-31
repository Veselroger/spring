package mvc.watchList;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import mvc.validation.GoodMovie;
import mvc.validation.Rating;

@GoodMovie
public record WatchlistItem(Integer id,
                            @NotBlank(message = "Title should not be empty")
                            String title,
                            @Rating
                            String rating,
                            @Size(max = 50, message = "Comment should be maximum 50 characters")
                            String comment) {

    public WatchlistItem() {
        this(0, null, null, null);
    }

    public WatchlistItem withId(Integer id) {
        return new WatchlistItem(id, this.title(), this.rating(), this.comment());
    }
}
