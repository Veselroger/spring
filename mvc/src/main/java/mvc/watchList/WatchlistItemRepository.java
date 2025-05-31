package mvc.watchList;

import java.util.List;

public interface WatchlistItemRepository {
    WatchlistItem findById(Integer id);

    WatchlistItem findByTitle(String title);

    List<WatchlistItem> findAll();

    void save(WatchlistItem item);
}
