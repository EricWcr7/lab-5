package view;

import interface_adapter.recipe_search.RecipeSearchViewModel;
import interface_adapter.recipe_searched.RecipeSearchedViewModel;

import javax.swing.*;
import java.awt.event.ActionListener;

public class RecipeSearchedView extends JPanel {

    private final String viewName = "recipe searched";
    private final RecipeSearchedViewModel recipeSearchedViewModel;
    private final JTextField dishInputField = new JTextField(50);
    private final JButton dishName;
    private final JButton back;

    public RecipeSearchedView(RecipeSearchedViewModel recipeSearchedViewModel) {
        this.recipeSearchedViewModel = recipeSearchedViewModel;

        final JFrame frame = new JFrame("Recipe Searched View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        final JPanel searchPanel = new JPanel();
        final JLabel search = new JLabel("Search");
        searchPanel.add(search);
        searchPanel.add(dishInputField);

        final JPanel dishplayPanel = new JPanel();
        dishName = new JButton("dis name 1");
        dishplayPanel.add(dishName);

        final JPanel buttons = new JPanel();
        back = new JButton("Back");
        this.add(back);

        this.add(searchPanel);
        this.add(dishplayPanel);
        this.add(buttons);

        // Set layout for this main panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // Arrange components vertically
        add(searchPanel);
        add(dishplayPanel);
        add(buttons);

        // Add this main panel to the JFrame
        frame.add(this);

        // Make the JFrame visible
        frame.setVisible(true);

    }

    public String getViewName() {
        return viewName; }

}
