package interface_adapter.recipe_search;

import use_case.recipe_search.RecipeSearchInputBoundary;
import use_case.recipe_search.RecipeSearchInputData;

/**
 * Controller for the RecipeSearch Use Case.
 */
public class RecipeSearchController {
    private final RecipeSearchInputBoundary recipeSearchInteractor;

    public RecipeSearchController(RecipeSearchInputBoundary recipeSearchInteractor) {
        this.recipeSearchInteractor = recipeSearchInteractor;
    }

    /**
     * Executes the Signup Use Case.
     * @param searchKeyword the keyword user type to search for
     */
    public void execute(String searchKeyword) {
        final RecipeSearchInputData recipeSearchInputData = new RecipeSearchInputData(searchKeyword);

        recipeSearchInteractor.execute(recipeSearchInputData);
    }
}
