package entity;

import java.util.Map;

/**
 * The representation of a recipe in our program.
 */
public interface Recipe {

    /**
     * Returns the recipe name of the user.
     * @return the recipe name of the user.
     */
    String getRecipeName();

    /**
     * Returns the recipe ingredients, (ex: mozzarella balls, 200g) of the user.
     * @return the recipe ingredients of the user.
     */
    Map<String, String> getRecipeIngredients();

    /**
     * Returns the recipe instructions of the user.
     * @return the recipe instructions of the user.
     */
    String getRecipeInstructions();

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getCreator();

}
