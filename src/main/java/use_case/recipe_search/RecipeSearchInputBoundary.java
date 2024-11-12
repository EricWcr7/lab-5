package use_case.recipe_search;

/**
 * Input Boundary for actions which are related to recipe search.
 */
public interface RecipeSearchInputBoundary {

    /**
     * Executes the signup use case.
     * @param recipeSearchInputData the input data
     */
    void execute(RecipeSearchInputData recipeSearchInputData);

    void switchToSearchResultView();

//    /**
//     * Executes the switch to login view use case.
//     */
//    void switchToSearchedView();
}

