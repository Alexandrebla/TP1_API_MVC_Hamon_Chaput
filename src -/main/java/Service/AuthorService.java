package Service;

import Domain.Author;
import Repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // 🔹 Récupérer tous les auteurs
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // 🔹 Récupérer un auteur par son ID
    public Optional<Author> getAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    // 🔹 Ajouter un auteur
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    // 🔹 Modifier un auteur
    public Author updateAuthor(Long id, Author updatedAuthor) {
        return authorRepository.findById(id)
                .map(author -> {
                    author.setFirstName(updatedAuthor.getFirstName());
                    author.setLastName(updatedAuthor.getLastName());
                    author.setBirthYear(updatedAuthor.getBirthYear());
                    return authorRepository.save(author);
                })
                .orElseThrow(() -> new RuntimeException("Auteur non trouvé avec l'id : " + id));
    }

    // 🔹 Supprimer un auteur
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
