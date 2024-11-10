package entity;

import java.util.Map;
/**
 * Factory for creating instances of CommonRecipe.
 */
public class CommonRecipeFactory implements RecipeFactory {

    /**
     * Creates a CommonRecipe with the specified details.
     *
     * @param id the ID of the recipe
     * @param name the name of the recipe
     * @param category the category of the recipe
     * @param instructions the cooking instructions
     * @param ingredientMeasureMap the ingredient and measurement of the recipe
     * @return a new CommonRecipe instance with the specified details
     */
    @Override
    public Recipe createRecipe(String id, String name, String category,
                               String instructions, Map<String, String> ingredientMeasureMap) {
        return new CommonRecipe(id, name, category, instructions, ingredientMeasureMap);
    }
}
