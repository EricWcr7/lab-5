package use_case.recipe_search;

public class RecipeSearchInputData {
    private final String searchKeyword;

    public RecipeSearchInputData(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }
}
