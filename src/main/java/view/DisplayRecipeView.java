package view;

import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.choose_recipe.ChooseRecipeState;
import interface_adapter.display_recipe.DisplayRecipeState;
import interface_adapter.display_recipe.DisplayRecipeViewModel;
import interface_adapter.like_a_recipe.LikeRecipeController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DisplayRecipeView extends JPanel implements PropertyChangeListener {
    private final String viewName = "display the recipe";
    private DisplayRecipeViewModel displayRecipeViewmodel;

    private ReturnToSearchMenuController returnToSearchMenuController;
    private LikeRecipeController likeRecipeController;

    // Example: Dynamic data loaded into variables
    private String dishname;
    private String ingredients;
    private String introduction;

    private final JTextArea ingredientsArea;
    private final JTextArea instructionArea;

    private final JButton returnToSearchMenu;
    private final JButton like;
    private final JButton dislike;
    private final JButton favorite;



    public DisplayRecipeView(DisplayRecipeViewModel displayRecipeViewmodel) {
        this.displayRecipeViewmodel = displayRecipeViewmodel;
        this.displayRecipeViewmodel.addPropertyChangeListener(this);

//        dishname = displayRecipeViewmodel.getDishName(); // null???
//        ingredients = displayRecipeViewmodel.getIngredients(); // empty???
//        introduction = displayRecipeViewmodel.getInstructions(); // empty???

        System.out.println("At DisplayRecipeView, the dishname is: " + dishname);

        ingredientsArea = new JTextArea(ingredients);
        ingredientsArea.setEditable(false);
        instructionArea = new JTextArea(introduction);
        instructionArea.setEditable(false);

        returnToSearchMenu = new JButton("Return to Search View ");
        like = new JButton("Like");
        dislike = new JButton("Dislike");
        favorite = new JButton("Favorite");

        // Use BoxLayout for a cleaner vertical layout
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Panel for recipe details
        JPanel recipePanel = new JPanel();
        recipePanel.setLayout(new BoxLayout(recipePanel, BoxLayout.Y_AXIS)); // Vertical layout for recipe details
        recipePanel.setAlignmentX(Component.LEFT_ALIGNMENT); // Align it to the left

        // Dish name
        JLabel dishNameLabel = new JLabel("Dish Name: ");
        dishNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        recipePanel.add(dishNameLabel);

        JLabel dishNameValue = new JLabel(dishname);
        recipePanel.add(dishNameValue);

        // Ingredients
        JLabel ingredientsLabel = new JLabel("Ingredients: ");
        ingredientsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        recipePanel.add(ingredientsLabel);
        recipePanel.add(ingredientsArea);

        // Instructions
        JLabel instructionsLabel = new JLabel("Instructions: ");
        instructionsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        recipePanel.add(instructionsLabel);
        recipePanel.add(instructionArea);

        // Panel for buttons (aligned horizontally)
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        buttonsPanel.add(returnToSearchMenu);
        buttonsPanel.add(like);
        buttonsPanel.add(dislike);
        buttonsPanel.add(favorite);

        // Add recipe details and buttons panels to the main view
        add(recipePanel);
        add(buttonsPanel);


        returnToSearchMenu.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(returnToSearchMenu)) {
                        this.returnToSearchMenuController.execute();
                    }
                }
        );

//        like.addActionListener(
//                // This creates an anonymous subclass of ActionListener and instantiates it.
//                evt -> {
//                    if (evt.getSource().equals(like)) {
//                        this.likeRecipeController.execute();
//                    }
//                }
//        )
    }

    public String getViewName() {
        return viewName;
    }

    public void setReturnToSearchMenuController(ReturnToSearchMenuController returnToSearchMenuController) {
        this.returnToSearchMenuController = returnToSearchMenuController;
    }

    public void setLikeRecipeController(LikeRecipeController likeRecipeController) {
        this.likeRecipeController = likeRecipeController;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("Property Change Event received with new state: " + evt.getNewValue());
        // Update view when DisplayRecipeState changes
        final DisplayRecipeState displayRecipeState = (DisplayRecipeState) evt.getNewValue();

        // Update UI components with the new state values
        dishname = displayRecipeState.getDishName();
        ingredients = displayRecipeState.getIngredients();
        introduction = displayRecipeState.getInstructions();

        // Update the labels and text areas with the new values
        ((JLabel) ((JPanel) this.getComponent(0)).getComponent(0)).setText("Dish Name: " + dishname);
        ingredientsArea.setText(ingredients);
        instructionArea.setText(introduction);

    }
}
