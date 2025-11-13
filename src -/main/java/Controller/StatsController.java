package Controller;

import Domain.Category;
import Service.StatsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    // 🔹 Nombre de livres par catégorie
    @GetMapping("/books-per-category")
    public Map<Category, Long> getBooksPerCategory() {
        return statsService.getBooksPerCategory();
    }

    // 🔹 Top auteurs (limit par défaut = 3)
    @GetMapping("/top-authors")
    public List<Map<String, Object>> getTopAuthors(@RequestParam(defaultValue = "3") int limit) {
        return statsService.getTopAuthors(limit);
    }
}
