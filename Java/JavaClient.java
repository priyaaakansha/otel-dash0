import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JavaClient {

    private static final Logger logger = Logger.getLogger(JavaClient.class.getName());

    public static void main(String[] args) {
        try {
            String json = """
                    {
                        "name": "Aakansha",
                        "message": "Hello from Java client!"
                    }
                    """;

            String apiUrl = System.getenv("API_URL");

            if (apiUrl == null || apiUrl.isEmpty()) {
                throw new IllegalArgumentException("Environment variable 'API_URL' is not set.");
            }

            HttpClient client = HttpClient.newHttpClient();

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            logger.info("Sending POST request to " + apiUrl);
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            logger.info("Status Code: " + response.statusCode());
            logger.info("Response Body: " + response.body());

            Thread.sleep(10000); // wait for 10 secs before sending data

            if (response.statusCode() == 200) {
                logger.info("Success! Server responded with 200 OK.");
            } else {
                logger.warning("Server returned error code: " + response.statusCode());
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, "Exception occurred: ", e);
        }
    }
}
