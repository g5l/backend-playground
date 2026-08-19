package poc.graphql.fetcher;

import graphql.schema.DataFetcher;
import poc.domain.Book;
import poc.repository.BookRepository;

import java.util.Map;

public class MutationFetchers {

    private final BookRepository bookRepository;

    public MutationFetchers(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public DataFetcher<Book> addBook() {
        return env -> {
            Map<String, Object> input = env.getArgument("input");
            String title = (String) input.get("title");
            String authorId = (String) input.get("authorId");
            return bookRepository.add(title, authorId);
        };
    }
}