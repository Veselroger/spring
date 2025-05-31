package mvc.watchList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WatchlistServiceTest {

    @InjectMocks
    private WatchlistItemService watchlistItemService;

    @Mock
    private WatchlistItemRepository repository;

    @Test
    public void testGetWatchlistItemsReturnsAllItemsFromRepository() {
        //Arrange
        WatchlistItem item1 = new WatchlistItem(1, "Star Wars", "7.7", "");
        WatchlistItem item2 = new WatchlistItem(2, "Star Treck", "8.8", "");

        List<WatchlistItem> mockItems = Arrays.asList(item1, item2);
        when(repository.findAll()).thenReturn(mockItems);

        //Act
        List<WatchlistItem> result = watchlistItemService.getAllItems();

        //Assert
        assertTrue(result.size() == 2);
        assertTrue(result.get(0).title().equals("Star Wars"));
        assertTrue(result.get(1).title().equals("Star Treck"));
    }
}
