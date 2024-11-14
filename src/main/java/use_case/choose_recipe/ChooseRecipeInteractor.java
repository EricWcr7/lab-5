package use_case.choose_recipe;

import data_access.RecipeDataAccessObject;
import entity.CommonRecipe;
import entity.Recipe;

import java.util.List;

// 从controller那准备好的input data知道哪个菜的名字
// 从DAO里找outputdata，菜的ingredients, instructions
// 把outputdata 准备好，也是哪个菜的具体信息
// 知道在什么情况下准备什么view
// successful view就让presenter拿着这些data换页面之类的
public class ChooseRecipeInteractor implements ChooseRecipeInputBoundary{
    private final ChooseRecipeOutputBoundary chooseRecipePresenter;
    private final RecipeDataAccessObject recipeDataAccessObject;
    private boolean recipesLoaded = false;  // Flag to ensure loading from cloud only once

    public ChooseRecipeInteractor(ChooseRecipeOutputBoundary chooseRecipePresenter) {
        this.chooseRecipePresenter = chooseRecipePresenter;
        this.recipeDataAccessObject = new RecipeDataAccessObject();
    }

    @Override
    public void execute(ChooseRecipeInputData chooseRecipeInputData) {
        // Ensure recipes are loaded from the cloud only once
        if (!recipesLoaded) {
            System.out.println("Loading recipes from cloud for the first time...");
            recipeDataAccessObject.loadRecipesFromCloud();
            recipesLoaded = true;
        }

        final String searchDishName = chooseRecipeInputData.getDishName();
        List<CommonRecipe> recipes = recipeDataAccessObject.searchRecipes(searchDishName);
        System.out.println("at choose recipe interactor" + searchDishName);
        // 按理说到这(按照全名搜的)应该只有一个recipe了,还要再检查
        // 但是现在找不到了这个菜了？？？
        if (recipes.isEmpty()) {
            System.out.println("No recipe found for: " + searchDishName);
            return;
        }
        Recipe recipe = recipes.get(0);
        // 得到了想要的recipe，接下来就根据recipe的name，ingredients，instructions来准备view的数据
        String dishName = recipe.getName();
        String dishIngredints = recipe.getIngredients();
        String dishIntroductions = recipe.getInstructions();
        System.out.println("Preparing successful view for: " + dishName);
        ChooseRecipeOutputData chooseRecipeOutputData = new ChooseRecipeOutputData(dishName, dishIngredints, dishIntroductions);
        chooseRecipePresenter.prepareSuccessView(chooseRecipeOutputData);
    }
}
