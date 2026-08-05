package poc.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import graphql.GraphQL;

import java.io.IOException;
import java.io.OutputStream;

public class GraphQLHttpHandler implements HttpHandler {

    private final GraphQL graphql;
    private final ObjectMapper mapper = new ObjectMapper();

    public GraphQLHttpHandler(GraphQL graphql) {
        this.graphql = graphql;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
    }

    private void sendJson(HttpExchange exchange, int status, Object payload) throws IOException {
        byte[] bytes = mapper.writeValueAsBytes(payload);
        exchange.getResponseHeaders().add("Content-Type", "application/json");
        exchange.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}