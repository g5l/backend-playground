package poc.graphql.fetcher;

import graphql.schema.DataFetcher;
import poc.domain.Author;
import poc.domain.Book;
import poc.repository.BookRepository;

import java.util.List;

public class AuthorFetchers {

    private final BookRepository bookRepository;

    public AuthorFetchers(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public DataFetcher<List<Book>> books() {
        return env -> {
            Author author = env.getSource();
            return bookRepository.findByAuthorId(author.id());
        };
    }
}