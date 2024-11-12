package interface_adapter.recipe_search;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
import interface_adapter.recipe_searched.RecipeSearchedViewModel;
import use_case.recipe_search.RecipeSearchOutputBoundary;
import use_case.recipe_search.RecipeSearchInputData;
import use_case.recipe_search.RecipeSearchOutputData;

public class RecipeSearchPresenter implements RecipeSearchOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final RecipeSearchedViewModel recipeSearchedViewModel;

    public RecipeSearchPresenter(ViewManagerModel viewManagerModel,
                                  RecipeSearchedViewModel recipeSearchedViewModel) {

        this.viewManagerModel = viewManagerModel;
        this.recipeSearchedViewModel = recipeSearchedViewModel;
    }

    public void execute(RecipeSearchInputData recipeSearchInputData) {

    }

//    @Override
//    public void prepareSuccessView(RecipeSearchOutputData outputData) {
//
//
//    }
//
//    @Override
//    public void prepareFailView(String errorMessage) {
//
//
//    }
//
//    @Override
//    public void switchToLoginView() {
//
//
//    }

    @Override
    public void switchToSearchedView() {
        this.viewManagerModel.setState(recipeSearchedViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

    }
}
