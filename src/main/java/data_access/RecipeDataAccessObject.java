package data_access;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import use_case.recipe_search.RecipeSearchDataAccessInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
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
    public void fetchRecipesBySearchKeyword(String searchKeyword) {
        final String url = API_URL + searchKeyword;

        try {
            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response status code is 200 (OK)
            if (response.statusCode() == STATUS_CODE_OK) {
                // Parse and store JSON response
                parseAndStoreRawJson(response.body());
            }
            else {
                System.err.println("Error: Received HTTP " + response.statusCode() + " from API.");
            }
        }
        catch (IOException ioException) {
            System.err.println("Network error while fetching recipes: " + ioException.getMessage());
        }
        catch (InterruptedException interruptedException) {
            System.err.println("Request was interrupted: " + interruptedException.getMessage());
            Thread.currentThread().interrupt();
        }
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

        // if exsists, delete file
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
    private void uploadFileToFileIo() {
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
