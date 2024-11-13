package interface_adapter.choose_recipe;

import interface_adapter.ViewModel;
import java.util.List;

public class ChooseRecipeViewModel extends ViewModel<ChooseRecipeState> {
    public ChooseRecipeViewModel() {
        super("choose recipe");
        setState(new ChooseRecipeState());
    }

    /**
     * Sets the list of recipe names in the ChooseRecipeState
     * and notifies any observers of the change.
     *
     * @param recipeNames the list of recipe names to display
     */
    public void setRecipeNames(List<String> recipeNames) {
        ChooseRecipeState currentState = getState();
        currentState.setRecipeNames(recipeNames);
        firePropertyChanged(); // Notify observers that the state has changed
    }

    /**
     * Sets an error message in the ChooseRecipeState
     * and notifies any observers of the change.
     *
     * @param errorMessage the error message to display
     */
    public void setErrorMessage(String errorMessage) {
        ChooseRecipeState currentState = getState();
        currentState.setErrorMessage(errorMessage);
        firePropertyChanged(); // Notify observers of the error message change
    }

    /**
     * Clears the current state, removing any search keywords, recipe names, and error messages.
     */
    public void clearState() {
        ChooseRecipeState currentState = getState();
        currentState.clearState();
        firePropertyChanged(); // Notify observers of the cleared state
    }
}


