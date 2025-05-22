import java.io.IOException;
import java.net.URI;
import java.net.http.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class AzureVisionApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        String subscriptionKey = "fake-key-1234abcd5678efgh";
        String endpoint = "https://pedro.cognitiveservices.azure.com/";
        String imagePath = "azure.jpg";

        HttpClient client = HttpClient.newHttpClient();
        byte[] imageBytes = Files.readAllBytes(Path.of(imagePath));

        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(endpoint + "vision/v3.2/analyze?visualFeatures=Description,Tags,Faces,Objects"))
            .header("Content-Type", "application/octet-stream")
            .header("Ocp-Apim-Subscription-Key", subscriptionKey)
            .POST(HttpRequest.BodyPublishers.ofByteArray(imageBytes))
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Resposta da API:");
        System.out.println(response.body());
    }
}
