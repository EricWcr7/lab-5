package entity;

import java.util.List;

/**
 * Factory for creating users.
 */
public interface RecipeFactory {
    /**
     * Creates a new Recipe.
     * @param instructions name of the new user
     * @param recipeName of the recipe
     * @return the new recipe
     */
    Recipe createRecipe(String recipeName, List<String> ingredients, List<String> measures, String instructions);

}
