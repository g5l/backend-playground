package poc.graphql.fetcher;

import graphql.schema.DataFetcher;
import poc.domain.Author;
import poc.domain.Book;
import poc.repository.AuthorRepository;

public class BookFetchers {

    private final AuthorRepository authorRepository;

    public BookFetchers(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public DataFetcher<Author> author() {
        return env -> {
            Book book = env.getSource();
            return authorRepository.findById(book.authorId())
                    .orElseThrow(() -> new IllegalStateException(
                            "Book " + book.id() + " references missing author " + book.authorId()));
        };
    }
}