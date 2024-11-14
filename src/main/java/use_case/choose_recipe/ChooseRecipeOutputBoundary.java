package use_case.choose_recipe;

import use_case.choose_recipe.ChooseRecipeInteractor;

/**
 * The output boundary for the Login Use Case.
 */
public interface ChooseRecipeOutputBoundary {
    /**
     * Prepares the success view for the Login Use Case.
     * @param outputData the output data
     */
    void prepareSuccessView(ChooseRecipeOutputData outputData);

//    /**
//     * Prepares the failure view for the Login Use Case.
//     * @param errorMessage the explanation of the failure
//     */
//    void prepareFailView(String errorMessage);


}
