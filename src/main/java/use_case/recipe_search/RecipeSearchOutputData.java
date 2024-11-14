package use_case.recipe_search;

import entity.CommonRecipe;
import java.util.List;

public class RecipeSearchOutputData {
    private final String searchKeyword;
    private final List<CommonRecipe> recipes;

    public RecipeSearchOutputData(String searchKeyword, List<CommonRecipe> recipes) {
        this.searchKeyword = searchKeyword;
        this.recipes = recipes;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public List<CommonRecipe> getRecipes() {
        return recipes;
    }
}

