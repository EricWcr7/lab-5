package use_case.display_recipe;

/**
 * The output boundary for the RecipeSearch Use Case.
 */
public interface DisplayRecipeOutputBoundary {

    /**
     * Prepares the success view for the Display Recipe Case.
     * @param outputData the output data
     */
    void prepareSuccessView(DisplayRecipeOutputData outputData);

//    /**
//     * Prepares the failure view for the Signup Use Case.
//     * @param errorMessage the explanation of the failure
//     */
//    void prepareFailView(String errorMessage);


}
