package poc.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQL;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

public class GraphQLHttpHandler implements HttpHandler {

    private final GraphQL graphql;
    private final ObjectMapper mapper = new ObjectMapper();

    public GraphQLHttpHandler(GraphQL graphql) {
        this.graphql = graphql;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            if (!exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                sendJson(exchange, 405, Map.of("errors", "Only POST is supported"));
                return;
            }

            Map<String, Object> body = mapper.readValue(
                    exchange.getRequestBody(), Map.class);

            String query = (String) body.get("query");
            if (query == null) {
                sendJson(exchange, 400, Map.of("errors", "Missing 'query' field"));
                return;
            }

            @SuppressWarnings("unchecked")
            Map<String, Object> variables =
                    (Map<String, Object>) body.getOrDefault("variables", Map.of());

            ExecutionInput input = ExecutionInput.newExecutionInput()
                    .query(query)
                    .variables(variables)
                    .build();

            ExecutionResult result = graphql.execute(input);
            sendJson(exchange, 200, result.toSpecification());

        } catch (Exception e) {
            sendJson(exchange, 500, Map.of("errors", "Internal error: " + e.getMessage()));
        }
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