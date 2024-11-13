package use_case.edit_recipe;

public class EditRecipeInteractor implements EditRecipeInputBoundary {
    private final EditRecipeOutputBoundary outputBoundary;

    public EditRecipeInteractor(EditRecipeOutputBoundary outputBoundary) {
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void switchToEditView() {
        outputBoundary.showEditScreen(); // P3 to P4, call outputboundary
    }

    @Override
    public void switchToCreateRecipe() {
        outputBoundary.showCreateRecipeScreen(); // P4 to P6, call outputboundary
    }

    @Override
    public void switchToMainMenu() {
        outputBoundary.showMainMenu(); // P4 to P3, call outputBoundary
    }
}