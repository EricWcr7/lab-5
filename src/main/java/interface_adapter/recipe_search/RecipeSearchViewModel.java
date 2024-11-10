package interface_adapter.recipe_search;

import interface_adapter.ViewModel;

public class RecipeSearchViewModel extends ViewModel<RecipeSearchState> {
    public RecipeSearchViewModel() {
        super("search recipe");
        setState(new RecipeSearchState());
    }
}


