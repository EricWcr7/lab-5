package interface_adapter.choose_recipe;

import interface_adapter.choose_recipe.ChooseRecipeViewModel;

import java.util.List;

public class ChooseRecipeController {

    private final ChooseRecipeViewModel viewModel;

    public ChooseRecipeController(ChooseRecipeViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Sets the selected recipe name in the ViewModel.
     * @param recipeName the name of the selected recipe
     */
    public void selectRecipe(String recipeName) {
        viewModel.getState().setSearchKeyword(recipeName);  // Update view model state directly
        viewModel.firePropertyChanged(); // Notify the view of changes
    }

    /**
     * Updates the list of recipe names in the ViewModel.
     * @param recipeNames the list of recipe names to display
     */
    public void updateRecipeList(List<String> recipeNames) {
        viewModel.getState().setRecipeNames(recipeNames);
        viewModel.firePropertyChanged();
    }

    /**
     * Clears the selection or navigates back to the search view.
     */
    public void clearSelection() {
        viewModel.getState().setSearchKeyword("");  // Clear any selected recipe
        viewModel.firePropertyChanged();
    }
}

