package interface_adapter.ReturnToSearchMenu;

import use_case.ReturnToSearchMenu.ReturnToSearchMenuInputBoundary;
import use_case.ReturnToSearchMenu.ReturnToSearchMenuInputData;

public class ReturnToSearchMenuController {

    private ReturnToSearchMenuInputBoundary returnToSearchMenuInteractor;

    public ReturnToSearchMenuController(ReturnToSearchMenuInputBoundary returnToSearchMenuInteractor) {
        this.returnToSearchMenuInteractor = returnToSearchMenuInteractor;
    }

    public void execute(String searchKeyword) {
        ReturnToSearchMenuInputData returnToSearchMenuInputData = new ReturnToSearchMenuInputData(searchKeyword);
        returnToSearchMenuInteractor.execute(returnToSearchMenuInputData);
    }
}
