# TASK-2-REST-API-CLIENT
**COMPANY**: CODTECH IT SOLUTIONS

**NAME** :ROHIT KISHOR PAWAR

**INTERN ID**: CT08HJD

**DOMAIN** : JAVA PROGRAMMING

**BATCH DURATION** : December 30th,2024 to January 30th,2025

**MENTOR NAME** : NEELA SANTHOSH

**DESCRIPTION** :
Creating a Java application that consumes a public REST API to fetch and display data is a valuable skill for modern software development. This task involves handling HTTP requests, parsing JSON responses, and presenting the data in a structured format. In this example, we will use the OpenWeatherMap API to fetch weather data and display it in a user-friendly manner.

Overview
The application will perform the following steps:

Send an HTTP GET Request: Connect to the OpenWeatherMap API to fetch weather data for a specified city.

Parse the JSON Response: Extract relevant information from the JSON response.

Display the Data: Present the weather data in a structured format.

Dependencies
To handle HTTP requests and parse JSON responses, we will use the HttpURLConnection class and the org.json library. If you are using Maven, add the following dependency to your pom.xml file:

xml
<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20210307</version>
</dependency>
If you are not using Maven, download the json-20210307.jar file from the Maven Repository and add it to your project.

Implementation
Here is the complete Java code for the application:

java
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApp {

    private static final String API_KEY = "YOUR_API_KEY";
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/weather?q=";

    public static void main(String[] args) {
        String city = "Delhi";
        String urlString = BASE_URL + city + "&appid=" + API_KEY + "&units=metric";

        try {
            // Create URL object
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set request method
            connection.setRequestMethod("GET");

            // Get response code
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Read response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                displayWeatherData(jsonResponse);
            } else {
                System.out.println("GET request failed. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void displayWeatherData(JSONObject jsonResponse) {
        String cityName = jsonResponse.getString("name");
        JSONObject main = jsonResponse.getJSONObject("main");
        double temperature = main.getDouble("temp");
        int humidity = main.getInt("humidity");
        JSONObject weather = jsonResponse.getJSONArray("weather").getJSONObject(0);
        String description = weather.getString("description");

        System.out.println("Weather Data for " + cityName + ":");
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Description: " + description);
    }
}
Explanation
Import Statements: Import the necessary classes for handling HTTP requests and parsing JSON responses.

API Key: Replace "YOUR_API_KEY" with your actual OpenWeatherMap API key.

URL Construction: Construct the URL with the city name, API key, and units (metric for Celsius).

HTTP Request: Use HttpURLConnection to send a GET request to the API.

Response Handling: Read the response and convert it to a StringBuilder.

JSON Parsing: Parse the JSON response using the org.json library.

Display Data: Extract relevant data (city name, temperature, humidity, description) and display it in a structured format.

Conclusion
This Java application demonstrates how to consume a public REST API, handle HTTP requests, parse JSON responses, and display the data in a structured format. By using the HttpURLConnection class and the org.json library, developers can efficiently interact with REST APIs and integrate external data into their applications. This example can be extended to include more features, such as user input for different cities, error handling, and more detailed weather information
