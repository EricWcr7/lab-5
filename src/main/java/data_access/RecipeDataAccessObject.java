package data_access;
import entity.CommonRecipe;
import entity.Recipe;
import entity.RecipeFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

// 目前是读json文件
public class RecipeDataAccessObject {
    private List<Recipe> recipeList;

    public RecipeDataAccessObject(RecipeFactory recipeFactory) {
        String filePath = "salad.json"; // Path to your JSON file
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject json = new JSONObject(content);
            JSONArray meals = json.getJSONArray("meals");

            for (int i = 0; i < meals.length(); i++) {
                JSONObject meal = meals.getJSONObject(i);
                String mealName = meal.getString("strMeal");
                String instructions = meal.getString("strInstructions");

                List<String> ingredients = new ArrayList<>();
                List<String> measures = new ArrayList<>();

                for (int j = 1; j <= 20; j++) {
                    String ingredientKey = "strIngredient" + j;
                    String measureKey = "strMeasure" + j;

                    String ingredient = meal.optString(ingredientKey).trim();
                    String measure = meal.optString(measureKey).trim();

                    if (!ingredient.isEmpty() && !ingredient.equals("null")) {
                        ingredients.add(ingredient);
                    }
                    if (!measure.isEmpty() && !measure.equals("null")) {
                        measures.add(measure);
                    }
                }

                System.out.println("Meal: " + mealName);
                System.out.println("Instructions: " + instructions);
                System.out.println("Ingredients: " + ingredients);
                System.out.println("Measures: " + measures);
                System.out.println("----------");

                final Recipe recipe = recipeFactory.createRecipe(mealName, ingredients, measures, instructions);
                recipeList.add(recipe);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

