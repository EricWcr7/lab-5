package use_case.display_recipe;

public class DisplayRecipeInputData {
    private final String dishname;
    private final String ingredients;
    private final String instructions;
    public DisplayRecipeInputData(String dishname, String ingredients, String instructions, String) {
        this.dishname = dishname;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }

    public String getDishname() {
        return dishname;
    }

    public String getIngredients() {
        return ingredients;
    }

    public String getInstructions() {
        return instructions;
    }

}
