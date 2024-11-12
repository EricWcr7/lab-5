package use_case.display_recipe;

public class DisplayRecipeOutputData {
    private final String searchKeyword;

    public DisplayRecipeOutputData(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }
}
