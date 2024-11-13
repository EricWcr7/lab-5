package use_case.recipe_search;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entity.Recipe;
import entity.RecipeFactory;

/**
 * The Recipe Search Interactor.
 */
public class RecipeSearchInteractor implements RecipeSearchInputBoundary {
    private final RecipeSearchDataAccessInterface recipeSearchDataAccessObject;
    private final RecipeSearchOutputBoundary recipeSearchPresenter;
    private final RecipeFactory recipeFactory;

    public RecipeSearchInteractor(RecipeSearchDataAccessInterface recipeSearchDataAccessInterface,
                                  RecipeSearchOutputBoundary recipeSearchPresenter,
                                  RecipeFactory recipeFactory) {
        this.recipeSearchDataAccessObject = recipeSearchDataAccessInterface;
        this.recipeSearchPresenter = recipeSearchPresenter;
        this.recipeFactory = recipeFactory;
    }

    @Override
    public void execute(RecipeSearchInputData recipeSearchInputData) {
        final String searchKeyword = recipeSearchInputData.getSearchKeyword();
        final List<JsonObject> searchedResults = recipeSearchDataAccessObject.fetchRecipesBySearchKeyword(searchKeyword);
        final List<Recipe> searchedRecipes = new ArrayList<>();
        if (searchedResults.size() > 0) {
            for (int i = 0; i < searchedResults.size(); i++) {
                final JsonObject file = searchedResults.get(i).getAsJsonObject();
                searchedRecipes.add(recipeFactory.createRecipe(file));

            }
            final RecipeSearchOutputData recipeSearchOutputData = new RecipeSearchOutputData(searchedRecipes,searchKeyword);
            recipeSearchPresenter.prepareSuccessView(recipeSearchOutputData);
        }
    }
}

