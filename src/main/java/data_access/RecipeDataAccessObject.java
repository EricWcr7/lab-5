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
import java.util.ArrayList;
import java.util.List;

/**
 * DAO for the RecipeSearch Use Case.
 */
public class RecipeDataAccessObject implements RecipeSearchDataAccessInterface {

    private static final String API_URL = "https://www.themealdb.com/api/json/v1/1/search.php?s=";
    private static final int STATUS_CODE_OK = 200;
    private static final String FILE_PATH = "recipes.json"; // 文件路径

    // 存储JsonObject类型的列表
    private final List<JsonObject> recipesJson = new ArrayList<>();

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
                writeRecipesToFile(); // 将数据写入文件
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

        if (mealsArray != null) {
            recipesJson.clear(); // 清空之前的数据
            for (int i = 0; i < mealsArray.size(); i++) {
                final JsonObject mealObject = mealsArray.get(i).getAsJsonObject();
                // 将 JsonObject 直接添加到列表中
                recipesJson.add(mealObject);
            }
        }
    }

    /**
     * Writes the list of recipes in JSON format to a file.
     */
    public void writeRecipesToFile() {
        final File file = new File(FILE_PATH);

        // 如果文件存在，则删除
        if (file.exists() && !file.delete()) {
            System.err.println("Error: Unable to delete existing file.");
            // 删除失败后继续写入
        }

        // 创建并写入新的文件
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            writer.write(recipesJson.toString());
            System.out.println("Recipes data written to file successfully.");
        }
        catch (IOException e) {
            System.err.println("Error while writing to file: " + e.getMessage());
        }
    }

    public List<JsonObject> getRecipesJson() {
        return recipesJson;
    }
}
