package interface_adapter.choose_recipe;

import java.util.List;

public class ChooseRecipePresenter {

    private final ChooseRecipeViewModel viewModel;

    public ChooseRecipePresenter(ChooseRecipeViewModel viewModel) {
        this.viewModel = viewModel;
    }

    /**
     * Prepares the recipe list view by updating the ViewModel with the given recipe names.
     * @param recipeNames the list of recipe names to be displayed in the view
     */
    public void presentRecipeList(List<String> recipeNames) {
        viewModel.getState().setRecipeNames(recipeNames);  // Update the recipe names in the view model state
        viewModel.firePropertyChanged();  // Notify any listeners that the view model has changed
    }

    /**
     * Prepares the view for a selected recipe by setting the selected recipe name.
     * @param selectedRecipe the name of the selected recipe
     */
    public void presentSelectedRecipe(String selectedRecipe) {
        viewModel.getState().setSearchKeyword(selectedRecipe);  // Update the selected recipe in the view model state
        viewModel.firePropertyChanged();  // Notify any listeners that the view model has changed
    }

    /**
     * Prepares a failure view if there is an error message to display.
     * @param errorMessage the error message to display in the view
     */
    public void presentError(String errorMessage) {
        viewModel.getState().setErrorMessage(errorMessage);  // Set an error message in the view model state
        viewModel.firePropertyChanged();  // Notify any listeners that the view model has changed
    }
}

