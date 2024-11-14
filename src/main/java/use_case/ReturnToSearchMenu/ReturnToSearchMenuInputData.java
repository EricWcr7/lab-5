package use_case.ReturnToSearchMenu;

public class ReturnToSearchMenuInputData {
    private final String searchKeyword;

    public ReturnToSearchMenuInputData(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }
}
