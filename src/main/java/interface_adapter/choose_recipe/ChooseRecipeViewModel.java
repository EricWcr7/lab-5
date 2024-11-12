package interface_adapter.choose_recipe;

import interface_adapter.ViewModel;

public class ChooseRecipeViewModel extends ViewModel<ChooseRecipeState> {
    public ChooseRecipeViewModel() {
        super("choose recipe");
        setState(new ChooseRecipeState());
    }

}
