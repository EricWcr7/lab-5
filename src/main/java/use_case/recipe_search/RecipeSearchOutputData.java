package use_case.recipe_search;
import java.util.ArrayList;
import java.util.List;
import entity.Recipe;

public class RecipeSearchOutputData {
    private final String searchKeyword;
    private final List<Recipe> recipes;

    public RecipeSearchOutputData(List<Recipe> recipes, String searchKeyword) {
        this.searchKeyword = searchKeyword;
        this.recipes = recipes;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
