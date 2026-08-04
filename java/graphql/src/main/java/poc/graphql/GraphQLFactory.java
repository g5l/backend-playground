package poc.graphql;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import poc.graphql.fetcher.AuthorFetchers;
import poc.graphql.fetcher.BookFetchers;
import poc.graphql.fetcher.QueryFetchers;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

public class GraphQLFactory {

    private final QueryFetchers queryFetchers;
    private final BookFetchers bookFetchers;
    private final AuthorFetchers authorFetchers;

    public GraphQLFactory(QueryFetchers queryFetchers,
                          BookFetchers bookFetchers,
                          AuthorFetchers authorFetchers) {
        this.queryFetchers = queryFetchers;
        this.bookFetchers = bookFetchers;
        this.authorFetchers = authorFetchers;
    }

    public GraphQL create() {
        TypeDefinitionRegistry registry = new SchemaParser().parse(loadSchema());
        RuntimeWiring wiring = buildWiring();
        GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
        return GraphQL.newGraphQL(schema).build();
    }

    private RuntimeWiring buildWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type(newTypeWiring("Query")
                        .dataFetcher("books", queryFetchers.books())
                        .dataFetcher("book", queryFetchers.book()))
                .type(newTypeWiring("Book")
                        .dataFetcher("author", bookFetchers.author()))
                .type(newTypeWiring("Author")
                        .dataFetcher("books", authorFetchers.books()))
                .build();
    }

    private String loadSchema() {
        try (InputStream in = getClass().getResourceAsStream("/schema.graphqls")) {
            Objects.requireNonNull(in, "schema.graphqls not found on classpath");
            return new String(in.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to read schema.graphqls", e);
        }
    }
}