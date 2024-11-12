package interface_adapter.recipe_searched;

import interface_adapter.ViewModel;
import interface_adapter.recipe_search.RecipeSearchState;

public class RecipeSearchedViewModel extends ViewModel<RecipeSearchedState> {
    public RecipeSearchedViewModel() {
        super("recipe searched");
        setState(new RecipeSearchedState());
    }


}
