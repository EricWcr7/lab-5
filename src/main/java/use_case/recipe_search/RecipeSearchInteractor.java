package use_case.recipe_search;

/**
 * The Recipe Search Interactor.
 */
public class RecipeSearchInteractor implements RecipeSearchInputBoundary {
    private final RecipeSearchDataAccessInterface recipeSearchDataAccessInterface;
    private final RecipeSearchOutputBoundary recipeSearchPresenter;

    public RecipeSearchInteractor(RecipeSearchDataAccessInterface recipeSearchDataAccessInterface,
                                  RecipeSearchOutputBoundary recipeSearchPresenter) {
        this.recipeSearchDataAccessInterface = recipeSearchDataAccessInterface;
        this.recipeSearchPresenter = recipeSearchPresenter;
    }

    @Override
    public void execute(RecipeSearchInputData recipeSearchInputData) {

    }

//    @Override
//    public void switchToSearchedView() {
//        recipeSearchPresenter.switchToSearchedView();
//    }
}

