package use_case.ReturnToSearchMenu;

public class ReturnToSearchMenuInteractor implements ReturnToSearchMenuInputBoundary{
    private ReturnToSearchMenuOutputBoundary returnToSearchMenuPresenter;

    public ReturnToSearchMenuInteractor(ReturnToSearchMenuOutputBoundary returnToSearchMenuPresenter) {
        this.returnToSearchMenuPresenter = returnToSearchMenuPresenter;
    }

    @Override
    public void execute(ReturnToSearchMenuInputData returnToSearchMenuInputData) {
        final ReturnToSearchMenuOutputData returnToSearchMenuOutputData =
                new ReturnToSearchMenuOutputData(returnToSearchMenuInputData.getSearchKeyword());
        returnToSearchMenuPresenter.prepareSuccessView(returnToSearchMenuOutputData);
    }
}
