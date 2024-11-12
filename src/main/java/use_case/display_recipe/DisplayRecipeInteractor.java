package use_case.display_recipe;

/**
 * The Recipe Search Interactor.
 */
public class DisplayRecipeInteractor implements DisplayRecipeInputBoundary {
//    private final RecipeSearchDataAccessInterface recipeSearchDataAccessInterface;
    private final DisplayRecipeOutputBoundary recipeSearchPresenter;


    public DisplayRecipeInteractor(DisplayRecipeOutputBoundary recipeSearchPresenter) {
        this.recipeSearchPresenter = recipeSearchPresenter;
    }

    @Override
    public void execute(DisplayRecipeInputData displayRecipeInputData) {

    }

}

