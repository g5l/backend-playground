package poc.graphql.fetcher;

import graphql.schema.DataFetcher;
import poc.domain.Book;
import poc.repository.BookRepository;

import java.util.List;

public class QueryFetchers {

    private final BookRepository bookRepository;

    public QueryFetchers(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public DataFetcher<List<Book>> books() {
        return env -> bookRepository.findAll();
    }

    public DataFetcher<Book> book() {
        return env -> {
            String id = env.getArgument("id");
            return bookRepository.findById(id).orElse(null);
        };
    }
}