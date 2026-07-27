package poc.repository;

import poc.domain.Author;

import java.util.List;
import java.util.Optional;

public class AuthorRepository {

    private final List<Author> authors = List.of(
            new Author("a1", "Ursula K. Le Guin"),
            new Author("a2", "Machado de Assis")
    );

    public Optional<Author> findById(String id) {
        return authors.stream()
                .filter(author -> author.id().equals(id))
                .findFirst();
    }
}