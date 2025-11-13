package Service;

import Domain.Author;
import Domain.Book;
import Domain.Category;
import Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatsService {

    private final BookRepository bookRepository;

    public StatsService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 🔹 1️⃣ Nombre de livres par catégorie
    public Map<Category, Long> getBooksPerCategory() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .collect(Collectors.groupingBy(Book::getCategory, Collectors.counting()));
    }

    // 🔹 2️⃣ Top auteurs (ayant le plus de livres)
    public List<Map<String, Object>> getTopAuthors(int limit) {
        List<Book> books = bookRepository.findAll();

        Map<Author, Long> countByAuthor = books.stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.counting()));

        return countByAuthor.entrySet().stream()
                .sorted(Map.Entry.<Author, Long>comparingByValue().reversed())
                .limit(limit)
                .map(entry -> {
                    Map<String, Object> data = new LinkedHashMap<>();
                    Author author = entry.getKey();
                    data.put("authorId", author.getId());
                    data.put("name", author.getFirstName() + " " + author.getLastName());
                    data.put("bookCount", entry.getValue());
                    return data;
                })
                .collect(Collectors.toList());
    }
}
