package use_case.recipe_search;

/**
 * The output boundary for the RecipeSearch Use Case.
 */
public interface RecipeSearchOutputBoundary {

    /**
     * Prepares the success view for the Signup Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(RecipeSearchOutputData outputData);

    /**
     * Prepares the failure view for the Signup Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Login View.
     */
    void switchToLoginView();
}
