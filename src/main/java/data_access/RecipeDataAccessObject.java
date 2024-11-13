package data_access;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import use_case.recipe_search.RecipeSearchDataAccessInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for the RecipeSearch Use Case.
 */
public class RecipeDataAccessObject implements RecipeSearchDataAccessInterface {

    private static final String API_URL = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
    private static final String FILE_IO_API_URL = "https://file.io";
    private static final int STATUS_CODE_OK = 200;
    private static final String FILE_PATH = "recipes.txt";

    private final List<JsonObject> recipesJson = new ArrayList<>();
    private final List<JsonObject> searchedRecipesJson = new ArrayList<>();

    /**
     * Fetches recipes for all alphabetical keywords (a-z) and stores them in a list.
     */
    public void fetchRecipesForAllKeywords() {
        for (char keyword = 'a'; keyword <= 'z'; keyword++) {
            fetchRecipesBySearchKeyword(String.valueOf(keyword));
        }
        // write file
        writeRecipesToFile();
    }

    /**
     * Fetches recipes based on the provided search keyword and adds each recipe's JsonObject to the list.
     *
     * @param searchKeyword the keyword to search recipes for
     */
    public List<JsonObject> fetchRecipesBySearchKeyword(String searchKeyword) {
        try {
            // Construct the URL with the search keyword as a query parameter
            final String apiUrl = API_URL + searchKeyword;
            final URL url = new URL(apiUrl);

            // Open the connection
            final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Check the response code
            final int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                final BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                final StringBuilder content = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }

                // Close connections
                in.close();
                conn.disconnect();

                // Parse JSON response using Gson
                final JsonObject jsonResponse = JsonParser.parseString(content.toString()).getAsJsonObject();
                final int count = jsonResponse.has("count") ? jsonResponse.get("count").getAsInt() : 0;
                System.out.println("Count: " + count);

                // Get "files" array from the response and check if it's not empty
                if (jsonResponse.has("files")) {
                    final JsonArray files = jsonResponse.getAsJsonArray("files");
                    if (files.size() > 0) { // Check if the files array is not empty
                        for (int i = 0; i < files.size(); i++) {
                            final JsonObject file = files.get(i).getAsJsonObject();
                            searchedRecipesJson.add(file);
                        }
                    }
                    else {
                        System.out.println("No files found in the response.");
                    }
                }
            }
            else {
                System.out.println("GET request failed. Response code: " + responseCode);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        // Return the list of searched recipes, even if it's empty
        return searchedRecipesJson;
    }


    /**
     * Parses the JSON response and adds each recipe JsonObject to the recipesJson list.
     *
     * @param responseBody the JSON response body from the API
     */
    public void parseAndStoreRawJson(String responseBody) {
        final JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
        final JsonArray mealsArray = jsonObject.getAsJsonArray("meals");
        for (int i = 0; i < mealsArray.size(); i++) {
            final JsonObject mealObject = mealsArray.get(i).getAsJsonObject();
            recipesJson.add(mealObject);
        }
    }

    /**
     * Writes the list of recipes in JSON format to a file and uploads it to File.io.
     */
    public void writeRecipesToFile() {
        final File file = new File(FILE_PATH);

        // if exists, delete file
        if (file.exists() && !file.delete()) {
            System.err.println("Error: Unable to delete existing file.");
        }

        // create and write in the file
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(recipesJson.toString());
            System.out.println("Recipes data written to file successfully.");
            // upload file
            uploadFileToFileIo();
        }
        catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getMessage());
        }
    }

    /**
     * Uploads the generated file to File.io API.
     */
    public void uploadFileToFileIo() {
        try {
            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest.BodyPublisher fileBody = HttpRequest.BodyPublishers.ofFile(Path.of(FILE_PATH));

            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(FILE_IO_API_URL))
                    .header("Content-Type", "multipart/form-data")
                    .POST(fileBody)
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // check status code
            if (response.statusCode() == STATUS_CODE_OK) {
                System.out.println("File uploaded successfully: " + response.body());
            }
            else {
                System.err.println("Failed to upload file. Status code: " + response.statusCode());
            }
        }
        catch (IOException | InterruptedException e) {
            System.err.println("Error during file upload: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public List<JsonObject> getRecipesJson() {
        return recipesJson;
    }
}
