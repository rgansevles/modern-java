package modernjava;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import com.sun.net.httpserver.HttpServer;

/**
 * Slide "HTTP Client API" (Java 11).
 * Starts a small local HTTP server first, so the demo works without internet.
 */
public class HttpClientDemo {

    private static final String JSON = "application/json";

    public static void main(String[] args) throws Exception {
        HttpServer server = startServer();
        URI uri = URI.create("http://localhost:" + server.getAddress().getPort() + "/employees/1");
        try {
            var client = HttpClient.newHttpClient();
            var request = HttpRequest.newBuilder(uri)
                .header("Accept", JSON)
                .timeout(Duration.ofSeconds(30))
                .build();
            var response = client.send(request,
                BodyHandlers.ofString());
            int status = response.statusCode();
            String body = response.body();
            System.out.println(status + " " + body);

            // async: returns a CompletableFuture
            client.sendAsync(request, BodyHandlers.ofString())
                .thenApply(r -> "async: " + r.statusCode() + " " + r.body())
                .thenAccept(System.out::println)
                .join();
        } finally {
            server.stop(0);
        }
    }

    private static HttpServer startServer() throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 0), 0);
        server.createContext("/employees", exchange -> {
            byte[] body = """
                {"id": 1, "name": "Ada"}""".getBytes(StandardCharsets.UTF_8);
            exchange.getResponseHeaders().add("Content-Type", JSON);
            exchange.sendResponseHeaders(200, body.length);
            try (var out = exchange.getResponseBody()) {
                out.write(body);
            }
        });
        server.start();
        return server;
    }
}
