package entity;

import java.util.Map;

/**
 * Implementation of the Recipe interface representing a common recipe.
 */
public class CommonRecipe implements Recipe {
    private final String id;
    private final String name;
    private final String category;
    private final String instructions;
    private final Map<String, String> ingredientMeasureMap;

    /**
     * Constructs a CommonRecipe object with specified details.
     *
     * @param id the ID of the recipe
     * @param name the name of the recipe
     * @param category the category of the recipe
     * @param instructions the cooking instructions
     * @param ingredientMeasureMap the ingredient and measurement of the recipe
     */
    public CommonRecipe(String id, String name, String category, String instructions,
                        Map<String, String> ingredientMeasureMap) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.instructions = instructions;
        this.ingredientMeasureMap = ingredientMeasureMap;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getCategory() {
        return category;
    }

    @Override
    public String getInstructions() {
        return instructions;
    }

}
