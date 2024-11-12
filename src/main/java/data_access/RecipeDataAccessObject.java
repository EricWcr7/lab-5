package data_access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.CommonRecipe;
import use_case.recipe_search.RecipeSearchDataAccessInterface;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

/**
 * DAO for the RecipeSearch Use Case.
 */
public class RecipeDataAccessObject implements RecipeSearchDataAccessInterface {

    private static final String API_URL = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
    private static final String FILE_IO_API_URL = "https://file.io";
    private static final int STATUS_CODE_OK = 200;
    private static final String FILE_PATH = "recipes.json";

    /**
     * Fetches all recipes from the API by iterating over keywords (a-z).
     *
     * @return a list of all CommonRecipe objects
     */
    @Override
    public List<CommonRecipe> fetchAllRecipes() {
        System.out.println("Starting to fetch all recipes from API...");
        List<CommonRecipe> allRecipes = new ArrayList<>();

        for (char keyword = 'a'; keyword <= 'z'; keyword++) {
            System.out.println("Fetching recipes for keyword: " + keyword);
            List<CommonRecipe> recipes = fetchRecipesByKeyword(String.valueOf(keyword));
            if (!recipes.isEmpty()) {
                allRecipes.addAll(recipes);
                System.out.println("Added " + recipes.size() + " recipes for keyword: " + keyword);
            } else {
                System.out.println("No recipes found for keyword: " + keyword);
            }
        }

        System.out.println("Finished fetching all recipes. Total recipes found: " + allRecipes.size());
        return allRecipes;
    }

    @Override
    /**
     * Helper method to fetch recipes based on a single character keyword.
     *
     * @param keyword the keyword to search for recipes
     * @return a list of CommonRecipe objects matching the keyword
     */
    public List<CommonRecipe> fetchRecipesByKeyword(String keyword) {
        List<CommonRecipe> recipes = new ArrayList<>();

        try {
            final String url = API_URL + URLEncoder.encode(keyword, StandardCharsets.UTF_8);
            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                System.out.println("Successful response for keyword: " + keyword);
                recipes = parseRecipes(response.body());
            } else {
                System.err.println("Error: Received HTTP " + response.statusCode() + " for keyword: " + keyword);
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error fetching recipes for keyword " + keyword + ": " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        return recipes;
    }

    /**
     * Parses the JSON response and converts it to a list of CommonRecipe objects.
     *
     * @param responseBody the JSON response body from the API
     * @return a list of CommonRecipe objects parsed from the response
     */
    private List<CommonRecipe> parseRecipes(String responseBody) {
        System.out.println("Parsing recipes.");
        List<CommonRecipe> recipes = new ArrayList<>();
        final JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();

        if (jsonObject.has("meals") && !jsonObject.get("meals").isJsonNull()) {
            final JsonArray mealsArray = jsonObject.getAsJsonArray("meals");
            System.out.println("Number of recipes found in response: " + mealsArray.size());

            for (int i = 0; i < mealsArray.size(); i++) {
                final JsonObject mealObject = mealsArray.get(i).getAsJsonObject();

                String id = mealObject.has("idMeal") && !mealObject.get("idMeal").isJsonNull()
                        ? mealObject.get("idMeal").getAsString() : "";
                String name = mealObject.has("strMeal") && !mealObject.get("strMeal").isJsonNull()
                        ? mealObject.get("strMeal").getAsString() : "";
                String category = mealObject.has("strCategory") && !mealObject.get("strCategory").isJsonNull()
                        ? mealObject.get("strCategory").getAsString() : "";
                String instructions = mealObject.has("strInstructions") && !mealObject.get("strInstructions").isJsonNull()
                        ? mealObject.get("strInstructions").getAsString() : "";

                Map<String, String> ingredientMeasureMap = new HashMap<>();
                for (int j = 1; j <= 20; j++) {
                    String ingredientKey = "strIngredient" + j;
                    String measureKey = "strMeasure" + j;

                    String ingredient = mealObject.has(ingredientKey) && !mealObject.get(ingredientKey).isJsonNull()
                            ? mealObject.get(ingredientKey).getAsString() : "";
                    String measure = mealObject.has(measureKey) && !mealObject.get(measureKey).isJsonNull()
                            ? mealObject.get(measureKey).getAsString() : "";

                    if (!ingredient.isEmpty()) {
                        ingredientMeasureMap.put(ingredient, measure);
                    }
                }

                CommonRecipe recipe = new CommonRecipe(id, name, category, instructions, ingredientMeasureMap);
                recipes.add(recipe);
            }
        } else {
            System.out.println("No recipes found in the API response.");
        }

        return recipes;
    }

    @Override
    /**
     * Writes the list of recipes in JSON format to a file and uploads it to File.io.
     *
     * @param recipes the list of recipes to write to the file
     */
    public void writeRecipesToFile(List<CommonRecipe> recipes) {
        System.out.println("Writing recipes to JSON file.");
        final File file = new File(FILE_PATH);

        if (file.exists() && !file.delete()) {
            System.err.println("Error: Unable to delete existing file.");
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonContent = gson.toJson(recipes);

        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(jsonContent);
            System.out.println("Recipes data written to file successfully.");
            uploadFileToFileIo();
        } catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getMessage());
        }
    }

    /**
     * Uploads the generated file to File.io API.
     */
    private void uploadFileToFileIo() {
        System.out.println("Uploading file to File.io with Bearer Auth.");
        try {
            final HttpClient client = HttpClient.newHttpClient();
            String bearerToken = "Meal Master"; // Replace with your actual Bearer token

            // Build the multipart form data request
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(FILE_IO_API_URL))
                    .header("Authorization", "Bearer " + bearerToken)
                    .header("Content-Type", "multipart/form-data")
                    .POST(ofFileUpload(Path.of(FILE_PATH)))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == STATUS_CODE_OK) {
                System.out.println("File uploaded successfully: " + response.body());
            } else {
                System.err.println("Failed to upload file. Status code: " + response.statusCode());
                System.err.println("Response body: " + response.body()); // Print the error response for debugging
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Error during file upload: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    // Helper method to handle file upload as multipart/form-data
    public static HttpRequest.BodyPublisher ofFileUpload(Path path) throws IOException {
        var boundary = "----WebKitFormBoundary";
        var fileBytes = Files.readAllBytes(path);
        var byteArrays = new ArrayList<byte[]>();

        // Add the file as 'file' field in multipart form-data
        byteArrays.add(("--" + boundary + "\r\nContent-Disposition: form-data; name=\"file\"; filename=\""
                + path.getFileName() + "\"\r\nContent-Type: application/json\r\n\r\n").getBytes(StandardCharsets.UTF_8));
        byteArrays.add(fileBytes);
        byteArrays.add(("\r\n--" + boundary + "--\r\n").getBytes(StandardCharsets.UTF_8));

        return HttpRequest.BodyPublishers.ofByteArrays(byteArrays);
    }

}
