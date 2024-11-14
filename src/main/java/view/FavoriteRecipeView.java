package view;

import interface_adapter.logout.LogoutController;
import interface_adapter.recipe_search.RecipeSearchController;
import interface_adapter.recipe_search.RecipeSearchState;
import interface_adapter.recipe_search.RecipeSearchViewModel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FavoriteRecipeView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "my favorite recipe";
    private final FavoriteRecipeViewModel favoriteRecipeViewModel;
    private FavoriteRecipeController favoriteRecipeController;

    public FavoriteRecipeView(FavoriteRecipeViewModel favoriteRecipeViewModel) {
        this.favoriteRecipeViewModel = favoriteRecipeViewModel;
        this.favoriteRecipeViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("my favorite recipe");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(title);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public String getViewName() {
        return viewName;
    }

    public void setLogoutController(LogoutController logoutController) {
        this.logoutController = logoutController;
    }

    public void setFavoriteRecipeController(FavoriteRecipeController favoriteRecipeController) {
        this.favoriteRecipeController = favoriteRecipeController;
    }
}
