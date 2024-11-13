package entity;

import com.google.gson.JsonObject;


/**
 * Factory interface for creating recipe instances.
 */
public interface RecipeFactory {

    /**
     * Creates a recipe with the specified details.
     *
     * @param recipeJson recipe Json
     * @return a Recipe instance with the specified details
     */
    Recipe createRecipe(JsonObject recipeJson);
}
