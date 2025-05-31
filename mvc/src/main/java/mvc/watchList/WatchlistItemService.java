package mvc.watchList;

import mvc.exception.DuplicateTitleException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service to work with {@link WatchlistItem}.
 */
@Service
public class WatchlistItemService {
    private final WatchlistItemRepository itemRepository;

    public WatchlistItemService(WatchlistItemRepository repository) {
        this.itemRepository = repository;
    }

    public int getWatchlistItemsSize() {
        return itemRepository.findAll().size();
    }

    public List<WatchlistItem> getAllItems() {
        return itemRepository.findAll();
    }

    public WatchlistItem findWatchlistItemById(Integer id) {
        return itemRepository.findById(id);
    }

    public void addOrUpdateWatchlistItem(WatchlistItem watchlistItem) throws DuplicateTitleException {
        WatchlistItem existingItem = findWatchlistItemById(watchlistItem.id());
        if (existingItem == null) {
            if (itemRepository.findByTitle(watchlistItem.title()) != null) {
                throw new DuplicateTitleException(watchlistItem.title());
            }
            itemRepository.save(watchlistItem);
        } else {
            itemRepository.save(watchlistItem);
        }
    }
}
