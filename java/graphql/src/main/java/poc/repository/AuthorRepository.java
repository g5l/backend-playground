package poc.repository;

import poc.domain.Book;

import java.util.List;
import java.util.Optional;

public class BookRepository {

    private final List<Book> books = List.of(
            new Book("b1", "A Wizard of Earthsea", "a1"),
            new Book("b2", "The Dispossessed", "a1"),
            new Book("b3", "Dom Casmurro", "a2")
    );
    
    public List<Book> findAll() {
        return books;
    }
    
    public Optional<Book> findById(String id) {
        return books.stream()
                .filter(book -> book.id().equals(id))
                .findFirst();
    }
    
    public List<Book> findByAuthorId(String authorId) {
        return books.stream()
                .filter(book -> book.authorId().equals(authorId))
                .toList();
    }
}