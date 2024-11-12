package use_case.recipe_search;

import data_access.RecipeDataAccessObject;
import entity.CommonRecipe;

import java.util.List;

/**
 * The Recipe Search Interactor.
 */
public class RecipeSearchInteractor implements RecipeSearchInputBoundary {
    private final RecipeSearchOutputBoundary recipeSearchPresenter;
    private final RecipeSearchDataAccessInterface recipeDataAccessObject;

    /**
     * Constructor for RecipeSearchInteractor.
     *
     * @param recipeSearchPresenter the output boundary (presenter) to display results
     */
    public RecipeSearchInteractor(RecipeSearchOutputBoundary recipeSearchPresenter) {
        this.recipeSearchPresenter = recipeSearchPresenter;
        this.recipeDataAccessObject = new RecipeDataAccessObject(); // Instantiate internally
    }

    /**
     * Executes the search use case based on a search keyword.
     *
     * @param recipeSearchInputData the input data containing the search keyword
     */
    @Override
    public void execute(RecipeSearchInputData recipeSearchInputData) {
        final String searchKeyword = recipeSearchInputData.getSearchKeyword();
        System.out.println("Interactor received search keyword: " + searchKeyword);

        try {
            // Fetch recipes from the shared file (assuming all recipes have been uploaded)
            List<CommonRecipe> recipes = recipeDataAccessObject.fetchRecipesByKeyword(searchKeyword);

            // Check if any recipes were found
            if (recipes.isEmpty()) {
                System.out.println("No recipes found for keyword: " + searchKeyword);
                recipeSearchPresenter.prepareFailureView("No recipes found for keyword: " + searchKeyword);
            } else {
                System.out.println("Recipes found: " + recipes.size());
                RecipeSearchOutputData recipeSearchOutputData = new RecipeSearchOutputData(searchKeyword, recipes);
                recipeSearchPresenter.prepareSuccessView(recipeSearchOutputData);
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            recipeSearchPresenter.prepareFailureView("An error occurred while searching for recipes: " + e.getMessage());
        }
    }

    /**
     * Fetches all recipes from the API and stores them to the shared file for global access.
     */
    public void initializeRecipeStorage() {
        System.out.println("Initializing shared recipe storage...");
        try {
            // Fetch all recipes (from 'a' to 'z')
            List<CommonRecipe> allRecipes = recipeDataAccessObject.fetchAllRecipes();
            System.out.println("Total recipes fetched: " + allRecipes.size());

            // Write all recipes to a shared JSON file and upload it
            recipeDataAccessObject.writeRecipesToFile(allRecipes);
            System.out.println("Shared recipe storage initialized successfully.");
        } catch (Exception e) {
            System.err.println("Failed to initialize recipe storage: " + e.getMessage());
        }
    }
}




