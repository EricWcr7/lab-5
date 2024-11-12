package interface_adapter.recipe_search;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_recipe.ChooseRecipeState;
import interface_adapter.choose_recipe.ChooseRecipeViewModel;
import use_case.recipe_search.RecipeSearchOutputBoundary;
import use_case.recipe_search.RecipeSearchOutputData;
import view.ChooseRecipeView;

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

    public void prepareSuccessView(RecipeSearchOutputData outputData) {
//        final ChooseRecipeState chooseRecipeState = chooseRecipeViewModel.getState();
//        chooseRecipeState.setSearchKeyword(outputData.getSearchKeyword());
//        this.chooseRecipeViewModel.setState(chooseRecipeState);
//        this.chooseRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(chooseRecipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
