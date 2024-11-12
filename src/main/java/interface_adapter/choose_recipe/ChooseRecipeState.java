package interface_adapter.choose_recipe;

public class ChooseRecipeState {
    private String searchKeyword;

    public ChooseRecipeState(ChooseRecipeState copy) {
        this.searchKeyword = copy.searchKeyword;
    }


    public ChooseRecipeState() {

    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

}
