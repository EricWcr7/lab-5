package use_case.edit_recipe;


public class EditRecipeInteractor implements EditRecipeInputBoundary{
    private final EditRecipeOutputBoundary editRecipePresenter;

    public EditRecipeInteractor(EditRecipeOutputBoundary editRecipePresenter) {
        this.editRecipePresenter = editRecipePresenter;
    }

    @Override
    public void switchToCreateView() {
    }
}
