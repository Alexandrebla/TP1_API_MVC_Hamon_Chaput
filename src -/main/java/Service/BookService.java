package Service;

import Domain.Book;
import Repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 🔹 Récupérer tous les livres
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // 🔹 Récupérer un livre par son ID
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    // 🔹 Ajouter un livre
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    // 🔹 Modifier un livre
    public Book updateBook(Long id, Book updatedBook) {
        return bookRepository.findById(id)
                .map(book -> {
                    book.setTitle(updatedBook.getTitle());
                    book.setIsbn(updatedBook.getIsbn());
                    book.setYear(updatedBook.getYear());
                    book.setCategory(updatedBook.getCategory());
                    book.setAuthor(updatedBook.getAuthor());
                    return bookRepository.save(book);
                })
                .orElseThrow(() -> new RuntimeException("Livre non trouvé avec l'id : " + id));
    }

    // 🔹 Supprimer un livre
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
