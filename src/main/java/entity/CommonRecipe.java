package entity;

import java.util.Map;

public class CommonRecipe implements Recipe {

    private String recipeName;
    private String instruction;
    private Map<String, String> ingredientsWithAmounts;
    private String creator;
    private int likeNumbers;
    private int dislikeNumbers;

    public CommonRecipe(String recipeName, Map<String, String> ingredientsWithAmounts, String instruction) {
        this.recipeName = recipeName;
        this.instruction = instruction;
        this.ingredientsWithAmounts = ingredientsWithAmounts;
        this.likeNumbers = 0;
        this.dislikeNumbers = 0;
        this.creator = "";
    }

    @Override
    public String getRecipeName() {
        return this.recipeName;
    }

    @Override
    public Map<String, String> getRecipeIngredients() {
        return this.ingredientsWithAmounts;
    }

    @Override
    public String getRecipeInstructions() {
        return this.instruction;
    }

    @Override
    public String getCreator() {
        return this.creator;
    }

    public void like() {
        this.likeNumbers++;
    }

    public void dislike() {
        this.dislikeNumbers++;
    }
}
