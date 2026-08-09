package poc;

import com.sun.net.httpserver.HttpServer;
import graphql.GraphQL;
import poc.graphql.GraphQLFactory;
import poc.graphql.fetcher.AuthorFetchers;
import poc.graphql.fetcher.BookFetchers;
import poc.graphql.fetcher.QueryFetchers;
import poc.http.GraphQLHttpHandler;
import poc.repository.AuthorRepository;
import poc.repository.BookRepository;

import java.net.InetSocketAddress;

public class Main {

    public static void main(String[] args) throws Exception {
        BookRepository bookRepository = new BookRepository();
        AuthorRepository authorRepository = new AuthorRepository();

        QueryFetchers queryFetchers = new QueryFetchers(bookRepository);
        BookFetchers bookFetchers = new BookFetchers(authorRepository);
        AuthorFetchers authorFetchers = new AuthorFetchers(bookRepository);

        GraphQL graphql = new GraphQLFactory(queryFetchers, bookFetchers, authorFetchers).create();

        HttpServer server = HttpServer.create(new InetSocketAddress(4000), 0);
        server.createContext("/graphql", new GraphQLHttpHandler(graphql));
        server.start();

        System.out.println("GraphQL server running at http://localhost:4000/graphql");
    }
}