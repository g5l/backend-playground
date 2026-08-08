package poc;

import graphql.GraphQL;
import poc.graphql.GraphQLFactory;
import poc.graphql.fetcher.AuthorFetchers;
import poc.graphql.fetcher.BookFetchers;
import poc.graphql.fetcher.QueryFetchers;
import poc.repository.AuthorRepository;
import poc.repository.BookRepository;

public class Main {

    public static void main(String[] args) throws Exception {
        BookRepository bookRepository = new BookRepository();
        AuthorRepository authorRepository = new AuthorRepository();

        QueryFetchers queryFetchers = new QueryFetchers(bookRepository);
        BookFetchers bookFetchers = new BookFetchers(authorRepository);
        AuthorFetchers authorFetchers = new AuthorFetchers(bookRepository);

        GraphQL graphql = new GraphQLFactory(queryFetchers, bookFetchers, authorFetchers).create();
    }
}