<dependencies>
    <!-- Jackson for JSON parsing -->
    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.15.0</version>
    </dependency>
    
    <!-- SLF4J for logging (needed by Jackson) -->
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-api</artifactId>
        <version>2.0.0-alpha1</version>
    </dependency>
</dependencies>




public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // toString() method for better printing
    @Override
    public String toString() {
        return "Post{" +
               "userId=" + userId +
               ", id=" + id +
               ", title='" + title + '\'' +
               ", body='" + body + '\'' +
               '}';
    }
}





import com.fasterxml.jackson.databind.ObjectMapper;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class RestApiClient {
    public static void main(String[] args) {
        // API endpoint for fetching posts
        String apiUrl = "https://jsonplaceholder.typicode.com/posts";

        // Create HttpClient instance
        HttpClient client = HttpClient.newHttpClient();

        // Build the GET request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            // Send GET request and get the response asynchronously
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response is successful (status code 200)
            if (response.statusCode() == 200) {
                // Parse the JSON response into a list of Post objects
                ObjectMapper objectMapper = new ObjectMapper();
                List<Post> posts = objectMapper.readValue(response.body(), objectMapper.getTypeFactory().constructCollectionType(List.class, Post.class));

                // Display the posts in a structured format
                for (Post post : posts) {
                    System.out.println(post);
                }
            } else {
                System.out.println("Failed to fetch data. HTTP Status: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
