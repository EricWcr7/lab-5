package use_case.display_recipe;

/**
 * Input Boundary for actions which are related to recipe search.
 */
public interface DisplayRecipeInputBoundary {

    /**
     * Executes the display recipe use case.
     * @param displayRecipeInputData the input data
     */
    void execute(DisplayRecipeInputData displayRecipeInputData);

}

