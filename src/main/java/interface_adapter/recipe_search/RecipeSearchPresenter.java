package interface_adapter.recipe_search;

import interface_adapter.ViewManagerModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.searchresult.SearchResultState;
import interface_adapter.searchresult.SearchResultViewModel;
import interface_adapter.signup.SignupState;
import interface_adapter.signup.SignupViewModel;
import use_case.recipe_search.RecipeSearchOutputBoundary;
import use_case.recipe_search.RecipeSearchOutputData;
import use_case.signup.SignupOutputBoundary;
import use_case.signup.SignupOutputData;

public class RecipeSearchPresenter implements RecipeSearchOutputBoundary {

    private final RecipeSearchViewModel recipeSearchViewModel;
    private final SearchResultViewModel searchResultViewModel;
    private final ViewManagerModel viewManagerModel;
    // private final LoginViewModel loginViewModel;

    public RecipeSearchPresenter(ViewManagerModel viewManagerModel,
                                 RecipeSearchViewModel recipeSearchViewModel,
                                 SearchResultViewModel searchResultViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.recipeSearchViewModel = recipeSearchViewModel;
        // this.loginViewModel = loginViewModel;
        this.searchResultViewModel = searchResultViewModel;
    }

    @Override
    public void prepareSuccessView(RecipeSearchOutputData outputData) {
        // final RecipeSearchState recipeSearchState = recipeSearchViewModel.getState();

        // this.recipeSearchViewModel.setState(recipeSearchState);
        // this.recipeSearchViewModel.firePropertyChanged();

        // final SearchResultState searchResultState = searchResultViewModel.getState();
        // searchResultState.setSearchKeyWord(outputData.getSearchKeyword());
        // this.searchResultViewModel.setState(searchResultState);
        // searchResultViewModel.firePropertyChanged();

        // viewManagerModel.setState(searchResultViewModel.getViewName());
        // viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

    @Override
    public void switchToLoginView() {

    }

    @Override
    public void switchToSearchResultView() {
        viewManagerModel.setState(searchResultViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
