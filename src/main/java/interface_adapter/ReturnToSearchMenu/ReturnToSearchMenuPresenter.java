package interface_adapter.ReturnToSearchMenu;

import interface_adapter.ViewManagerModel;
import interface_adapter.choose_recipe.ChooseRecipeState;
import interface_adapter.choose_recipe.ChooseRecipeViewModel;
import interface_adapter.edit.EditState;
import interface_adapter.edit.EditViewModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import use_case.ReturnToSearchMenu.ReturnToSearchMenuOutputBoundary;
import use_case.ReturnToSearchMenu.ReturnToSearchMenuOutputData;
import view.ChooseRecipeView;

public class ReturnToSearchMenuPresenter implements ReturnToSearchMenuOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private RecipeSearchViewModel recipeSearchViewModel;
    private ChooseRecipeViewModel chooseRecipeViewModel;
    private EditViewModel editViewModel;

    public ReturnToSearchMenuPresenter(ViewManagerModel viewManagerModel,
                                       RecipeSearchViewModel recipeSearchViewModel,
                                       ChooseRecipeViewModel chooseRecipeViewModel,
                                       EditViewModel editViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.recipeSearchViewModel = recipeSearchViewModel;
        this.chooseRecipeViewModel = chooseRecipeViewModel;
        this.editViewModel = editViewModel;
    }

    @Override
    public void prepareSuccessView() {
        final ChooseRecipeState chooseRecipeState = chooseRecipeViewModel.getState();
        chooseRecipeState.setSearchKeyword("");

        this.chooseRecipeViewModel.setState(chooseRecipeState);
        this.chooseRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setState(recipeSearchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void fromEditRecipeBackToSearchMenu() {
        final EditState editRecipeState = editViewModel.getState();

        this.editViewModel.setState(editRecipeState);
        this.editViewModel.firePropertyChanged();

        this.viewManagerModel.setState(recipeSearchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }
}
