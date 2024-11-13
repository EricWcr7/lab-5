package entity;

import java.util.HashMap;
import java.util.Map;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
/**
 * Factory for creating instances of CommonRecipe.
 */
public class CommonRecipeFactory implements RecipeFactory {

    @Override
    public Recipe createRecipe(final JsonObject recipeJson) {
        final String id = recipeJson.get("idMeal").getAsString();
        final String name = recipeJson.get("strMeal").getAsString();
        final String category = recipeJson.get("strCategory").getAsString();
        final String instructions = recipeJson.get("strInstructions").getAsString();

        // 创建 ingredientMeasureMap
        final Map<String, String> ingredientMeasureMap = new HashMap<>();

        // 提取成分和测量数据
        for (int i = 1; i <= recipeJson.size(); i++) {
            final String ingredientKey = "strIngredient" + i;
            final String measureKey = "strMeasure" + i;

            if (recipeJson.has(ingredientKey) && !recipeJson.get(ingredientKey).getAsString().isEmpty()) {
                final String ingredient = recipeJson.get(ingredientKey).getAsString();
                final String measure = recipeJson.has(measureKey) ? recipeJson.get(measureKey).getAsString() : "";
                ingredientMeasureMap.put(ingredient, measure);
            }
        }

        // 创建并返回 CommonRecipe 实例
        return new CommonRecipe(id, name, category, instructions, ingredientMeasureMap);
    }
}
