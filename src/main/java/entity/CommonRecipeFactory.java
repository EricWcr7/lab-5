package entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommonRecipeFactory implements RecipeFactory {

    public Recipe createRecipe(String recipeName, List<String> ingredients, List<String> measures, String instructions) {
        final Map ingredientsWithAmount = new HashMap<String, String>();
        for (int i = 0; i < ingredients.size(); i++) {
            ingredientsWithAmount.put(ingredients.get(i), measures.get(i));
        }
        return new CommonRecipe(recipeName, ingredientsWithAmount, instructions);

    }


}
