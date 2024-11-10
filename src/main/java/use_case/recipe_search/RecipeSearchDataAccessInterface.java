package use_case.recipe_search;

import java.util.List;

import entity.Recipe;

/**
 * DAO for the RecipeSearch Use Case.
 */
public interface RecipeSearchDataAccessInterface {

    /**
     * Fetches recipes related to the given search keyword.
     *
     * @param searchKeyword the keyword to search for recipes
     * @return a list of Recipe objects related to this search keyword; if no recipes are found, returns an empty list
     */
    List<Recipe> fetchRecipesBySearchKeyword(String searchKeyword);
}
