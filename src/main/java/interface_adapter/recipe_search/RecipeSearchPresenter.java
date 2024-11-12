package interface_adapter.recipe_search;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_recipe.ChooseRecipeState;
import interface_adapter.choose_recipe.ChooseRecipeViewModel;
import use_case.recipe_search.RecipeSearchOutputBoundary;
import use_case.recipe_search.RecipeSearchOutputData;

public class RecipeSearchPresenter implements RecipeSearchOutputBoundary {
    private final RecipeSearchViewModel recipeSearchViewModel;
    private final ChooseRecipeViewModel chooseRecipeViewModel;
    private final ViewManagerModel viewManagerModel;

    public RecipeSearchPresenter(ViewManagerModel viewManagerModel,
                                 ChooseRecipeViewModel chooseRecipeViewModel,
                                 RecipeSearchViewModel recipeSearchViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.chooseRecipeViewModel = chooseRecipeViewModel;
        this.recipeSearchViewModel = recipeSearchViewModel;
    }

    @Override
    public void prepareSuccessView(RecipeSearchOutputData outputData) {
        final ChooseRecipeState chooseRecipeState = chooseRecipeViewModel.getState();
        final RecipeSearchState recipeSearchState = recipeSearchViewModel.getState();
        recipeSearchState.setSearchKeyWord("");
        this.recipeSearchViewModel.firePropertyChanged();
        chooseRecipeState.setSearchKeyword(outputData.getSearchKeyword());
        this.chooseRecipeViewModel.setState(chooseRecipeState);
        this.chooseRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(chooseRecipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        // Set the error message in RecipeSearchState
        RecipeSearchState recipeSearchState = recipeSearchViewModel.getState();
        recipeSearchState.setErrorMessage(errorMessage);  // Assuming RecipeSearchState has setErrorMessage

        // Notify the view model of the state change
        recipeSearchViewModel.setState(recipeSearchState);
        recipeSearchViewModel.firePropertyChanged();

        // Optionally, update the view manager to reflect the failure state in the UI
        viewManagerModel.setState(recipeSearchViewModel.getViewName()); // Go to the search view with error
        viewManagerModel.firePropertyChanged();
    }
}

