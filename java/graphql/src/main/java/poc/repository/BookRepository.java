package poc.repository;

import poc.domain.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class BookRepository {

    private final List<Book> books = new ArrayList<>(List.of(
            new Book("b1", "A Wizard of Earthsea", "a1"),
            new Book("b2", "The Dispossessed", "a1"),
            new Book("b3", "Dom Casmurro", "a2")
    ));

    private final AtomicInteger nextId = new AtomicInteger(books.size() + 1);

    public List<Book> findAll() {
        return books;
    }

    public Book add(String title, String authorId) {
        Book book = new Book("b" + nextId.getAndIncrement(), title, authorId);
        books.add(book);
        return book;
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