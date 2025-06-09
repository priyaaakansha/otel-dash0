import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class JavaClient {
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

            // HTTP POST request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            System.out.println("Status Code: " + response.statusCode());
            System.out.println("Response Body: " + response.body());

            Thread.sleep(10000); // wait for 10 secs before sending data S

            if (response.statusCode() == 200) {
                System.out.println("Success! Server responded with 200 OK.");
            } else {
                System.out.println("Server returned error code.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
