package use_case.ReturnToSearchMenu;

public class ReturnToSearchMenuOutputData {
    private final String searchKeyword;

    public ReturnToSearchMenuOutputData(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }
}
