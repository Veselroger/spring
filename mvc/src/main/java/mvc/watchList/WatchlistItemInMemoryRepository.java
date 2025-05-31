package mvc.watchList;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of a repository to store {@link WatchlistItem}.
 */
@Repository
public class WatchlistItemInMemoryRepository implements WatchlistItemRepository {

    private final Map<Integer, WatchlistItem> items = new HashMap<>();

    public WatchlistItemInMemoryRepository() {
        items.put(1, new WatchlistItem(1, "Lion King", "8.5", "Hakuna matata!"));
        items.put(2, new WatchlistItem(2, "Frozen", "7.5", "Let it go!"));
    }

    @Override
    public WatchlistItem findById(Integer id) {
        return items.get(id);
    }

    @Override
    public WatchlistItem findByTitle(String title) {
        return items.values().stream().findAny()
                .filter((str) -> str.title().equals(title))
                .orElse(null);
    }

    @Override
    public List<WatchlistItem> findAll() {
        return new ArrayList<>(items.values());
    }

    @Override
    public void save(WatchlistItem item) {
        this.items.put(item.id(), item);
    }

}
