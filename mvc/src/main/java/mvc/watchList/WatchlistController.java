package mvc.watchList;

import jakarta.validation.Valid;
import mvc.exception.DuplicateTitleException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WatchlistController {

    private final WatchlistItemService itemService;

    public WatchlistController(WatchlistItemService itemRepository) {
        this.itemService = itemRepository;
    }

    @GetMapping("/watchlist")
    public ModelAndView getWatchlist() {
        List<WatchlistItem> items = itemService.getAllItems();
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("watchlistItems", items);
        model.put("numberOfMovies", items.size());

        String viewName = "watchlist";
        return new ModelAndView(viewName, model);
    }

    @GetMapping("/watchlistItemForm")
    public ModelAndView showWatchlistItemForm(@RequestParam(required = false) Integer id) {
        Map<String, Object> model = new HashMap<String, Object>();
        WatchlistItem item = itemService.findWatchlistItemById(id);
        if (item == null) item = new WatchlistItem();
        model.put("watchlistItem", item);

        String viewName = "watchlistItemForm";
        return new ModelAndView(viewName, model);
    }

    @PostMapping("/watchlistItemForm")
    public ModelAndView submitWatchlistItemForm(@Valid @ModelAttribute WatchlistItem watchlistItem, BindingResult check) {
        if (check.hasErrors()) {
            return new ModelAndView("watchlistItemForm");
        }
        try {
            itemService.addOrUpdateWatchlistItem(watchlistItem);
        } catch (DuplicateTitleException e) {
            check.rejectValue("title", "", "This movie is already on your watchlist");
            return new ModelAndView("watchlistItemForm");
        }

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/watchlist");
        return new ModelAndView(redirectView);
    }

}
