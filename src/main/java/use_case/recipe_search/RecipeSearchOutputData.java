package use_case.recipe_search;

public class RecipeSearchOutputData {
    private final String searchKeyword;

    public RecipeSearchOutputData(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }
}
