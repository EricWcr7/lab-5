package data_access;

import java.util.HashMap;
import java.util.Map;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entity.CommonRecipe;
import entity.Recipe;
import use_case.recipe_search.RecipeSearchDataAccessInterface;

/**
 * Implementation of RecipeSearchDataAccessInterface to access recipe data from the API.
 */
public class RecipeDataAccessObject implements RecipeSearchDataAccessInterface {
    private static final String API_URL = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
    private static final int STATUS_CODE_OK = 200;
    private final List<Recipe> recipes = new ArrayList<>();

    /**
     * Fetches recipes for all letters from 'a' to 'z' and stores them in the instance list.
     *
     * @return a list of Recipe objects for all letters from 'a' to 'z'
     */
    public List<Recipe> fetchRecipesForAllLetters() {
        recipes.clear();
        for (char letter = 'a'; letter <= 'z'; letter++) {
            fetchRecipesBySearchKeyword(String.valueOf(letter));
        }
        return recipes;
    }

    /**
     * Fetches recipes based on the provided search keyword and adds them to the instance list.
     *
     * @param searchKeyword the keyword to search recipes for
     */
    @Override
    public List<Recipe> fetchRecipesBySearchKeyword(String searchKeyword) {
        final String url = API_URL + searchKeyword;

        try {
            final HttpClient client = HttpClient.newHttpClient();
            final HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response status code is 200 (OK)
            if (response.statusCode() == STATUS_CODE_OK) {
                // Parse and store response
                parseAndStoreResponse(response.body());
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
        catch (com.google.gson.JsonSyntaxException jsonException) {
            System.err.println("Error parsing JSON response: " + jsonException.getMessage());
        }
        return recipes;
    }

    /**
     * Parses the JSON response and stores recipes in the instance variable.
     *
     * @param responseBody the JSON response body from the API
     */
    private void parseAndStoreResponse(String responseBody) {
        final Gson gson = new Gson();
        final JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
        final JsonArray mealsArray = jsonObject.getAsJsonArray("meals");

        if (mealsArray != null) {
            for (int i = 0; i < mealsArray.size(); i++) {
                final JsonObject mealObject = mealsArray.get(i).getAsJsonObject();

                final String id = mealObject.get("idMeal").getAsString();
                final String name = mealObject.get("strMeal").getAsString();
                final String category = mealObject.get("strCategory").getAsString();
                final String instructions = mealObject.get("strInstructions").getAsString();

                final Map<String, String> ingredientMeasureMap = new HashMap<>();
                final int size = 20;
                for (int j = 1; j <= size; j++) {
                    final String ingredientKey = "strIngredient" + j;
                    final String measureKey = "strMeasure" + j;

                    if (mealObject.has(ingredientKey) && !mealObject.get(ingredientKey).getAsString().isEmpty()) {
                        final String ingredient = mealObject.get(ingredientKey).getAsString();
                        final String measure = mealObject.get(measureKey).getAsString();
                        ingredientMeasureMap.put(ingredient, measure);
                    }
                }

                final Recipe recipe = new CommonRecipe(id, name, category, instructions, ingredientMeasureMap);
                recipes.add(recipe);
            }
        }
    }
}
