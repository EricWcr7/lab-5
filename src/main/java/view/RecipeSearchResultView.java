package view;

import interface_adapter.choose_recipe.ChooseRecipeViewModel;
import interface_adapter.choose_recipe.ChooseRecipeState;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class RecipeSearchResultView extends JPanel implements PropertyChangeListener {
    private final ChooseRecipeViewModel chooseRecipeViewModel;

    public RecipeSearchResultView(ChooseRecipeViewModel chooseRecipeViewModel) {
        this.chooseRecipeViewModel = chooseRecipeViewModel;
        this.chooseRecipeViewModel.addPropertyChangeListener(this);
        setLayout(new FlowLayout());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("state".equals(evt.getPropertyName())) {
            updateRecipeButtons();
        }
    }

    /**
     * Generates buttons based on the list of recipe names in ChooseRecipeState.
     */
    private void updateRecipeButtons() {
        this.removeAll(); // Clear existing buttons

        ChooseRecipeState state = chooseRecipeViewModel.getState();
        List<String> recipeNames = state.getRecipeNames();

        for (String recipeName : recipeNames) {
            JButton recipeButton = new JButton(recipeName);
            add(recipeButton);
        }

        revalidate(); // Refresh the UI
        repaint();
    }
}


