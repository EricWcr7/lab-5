package use_case.edit_recipe;

public interface EditRecipeInputBoundary {
    void switchToEditView(); //P3 to P4
    void switchToCreateRecipe(); //P4 to P6
    void switchToMainMenu(); //P4 to P3
}

