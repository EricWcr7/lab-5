package use_case.choose_recipe;

import entity.CommonRecipe;

import java.util.List;

/**
 * DAO for the RecipeSearch Use Case.
 */
public interface ChooseRecipeDataAccessInterface {



    List<CommonRecipe> searchRecipes(String keyword);


}


