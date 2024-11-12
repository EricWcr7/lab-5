package use_case.recipe_search;

/**
 * The Recipe Search Interactor.
 */
public class RecipeSearchInteractor implements RecipeSearchInputBoundary {
//    private final RecipeSearchDataAccessInterface recipeSearchDataAccessInterface;
    private final RecipeSearchOutputBoundary recipeSearchPresenter;

//    public RecipeSearchInteractor(RecipeSearchDataAccessInterface recipeSearchDataAccessInterface,
//                                  RecipeSearchOutputBoundary recipeSearchPresenter) {
//        this.recipeSearchDataAccessInterface = recipeSearchDataAccessInterface;
//        this.recipeSearchPresenter = recipeSearchPresenter;
//    }

    public RecipeSearchInteractor(RecipeSearchOutputBoundary recipeSearchPresenter) {
        this.recipeSearchPresenter = recipeSearchPresenter;
    }

    @Override
    public void execute(RecipeSearchInputData recipeSearchInputData) {
        final String searchKeyword = recipeSearchInputData.getSearchKeyword();
        final RecipeSearchOutputData recipeSearchOutputData = new RecipeSearchOutputData(searchKeyword);
        // if xxxx prepare success view 条件判断在此省略 稍后添加
        recipeSearchPresenter.prepareSuccessView(recipeSearchOutputData);
    }

//    @Override
//    public void switchToSearchedView() {
//        recipeSearchPresenter.switchToSearchedView();
//    }
}

