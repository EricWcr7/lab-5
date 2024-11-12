package view;

import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.display_recipe.DisplayRecipeViewModel;
import interface_adapter.like_a_recipe.LikeRecipeController;

import javax.swing.*;

public class DisplayRecipeView extends JPanel {
    private final String viewName = "display the recipe";
    private DisplayRecipeViewModel displayRecipeViewmodel;

    private ReturnToSearchMenuController returnToSearchMenuController;
    private LikeRecipeController likeRecipeController;

    // Example: Dynamic data loaded into variables
    private final String dishname;
    private final String ingredients;
    private final String introduction;

    private final JTextArea ingredientsArea;
    private final JTextArea introductionArea;

    private final JButton returnToSearchMenu;
    private final JButton like;
    private final JButton dislike;
    private final JButton favorite;



    public DisplayRecipeView(DisplayRecipeViewModel displayRecipeViewmodel) {
        this.displayRecipeViewmodel = displayRecipeViewmodel;
        //this.displayRecipeViewmodel.addPropertyChangeListener(this);

        dishname = "need to call a method";
        ingredients = "need to call a method";
        introduction = "need to call a method";

        ingredientsArea = new JTextArea(ingredients);
        ingredientsArea.setEditable(false);
        introductionArea = new JTextArea(introduction);
        introductionArea.setEditable(false);

        returnToSearchMenu = new JButton("Return to Search View ");
        like = new JButton("Like");
        dislike = new JButton("Dislike");
        favorite = new JButton("Favorite");

        // Example: Add components to view
        final JPanel recipePanel = new JPanel();
        recipePanel.add(new JLabel("Dish Name: " + dishname));
        recipePanel.add(ingredientsArea);
        recipePanel.add(introductionArea);

        final JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(returnToSearchMenu);
        buttonsPanel.add(like);
        buttonsPanel.add(dislike);
        buttonsPanel.add(favorite);

        this.add(recipePanel);
        this.add(buttonsPanel);

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

}
