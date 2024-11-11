package use_case.recipe_search;


/**
 * DAO for the RecipeSearch Use Case.
 */
public interface RecipeSearchDataAccessInterface {

    /**
     * Fetches recipes related to the given search keyword.
     *
     * @param searchKeyword the keyword to search for recipes
     */
    void fetchRecipesBySearchKeyword(String searchKeyword);

    /**
     * Parses the JSON response and adds each recipe JsonObject to the recipesJson list.
     *
     * @param responseBody the JSON response body from the API
     */
    void parseAndStoreRawJson(String responseBody);
}
