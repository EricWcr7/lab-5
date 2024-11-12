package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.choose_recipe.ChooseRecipeController;
import interface_adapter.choose_recipe.ChooseRecipeState;
import interface_adapter.choose_recipe.ChooseRecipeViewModel;

public class ChooseRecipeView extends JPanel implements ActionListener, PropertyChangeListener  {


    private final String viewName = "choose recipe";
    private final ChooseRecipeViewModel chooseRecipeViewModel;
    private ChooseRecipeController chooseRecipeController;
    private ReturnToSearchMenuController returnToSearchMenuController;
    private final JButton returnToSearchMenu;
    private final JTextField dishInputField = new JTextField(50);

    public ChooseRecipeView(ChooseRecipeViewModel chooseRecipeViewModel) {
        this.chooseRecipeViewModel = chooseRecipeViewModel;
        this.chooseRecipeViewModel.addPropertyChangeListener(this);
        returnToSearchMenu = new JButton("Return to Search View ");
        final JPanel searchPanel = new JPanel();
        searchPanel.add(dishInputField);
        searchPanel.add(returnToSearchMenu);
        this.add(searchPanel);

        returnToSearchMenu.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(returnToSearchMenu)) {
                        final ChooseRecipeState chooseRecipeState = chooseRecipeViewModel.getState();
                        final String searchKeyword = chooseRecipeState.getSearchKeyword();
                        this.returnToSearchMenuController.execute(searchKeyword);
                    }
                }
        );

    }




    public String getViewName() {
        return viewName;
    }

    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ChooseRecipeState chooseRecipeState = (ChooseRecipeState) evt.getNewValue();
        setFields(chooseRecipeState);
    }

    private void setFields(ChooseRecipeState chooseRecipeState) {
        dishInputField.setText(chooseRecipeState.getSearchKeyword());
    }

    public void setReturnToSearchMenuController(ReturnToSearchMenuController returnToSearchMenuController) {
        this.returnToSearchMenuController = returnToSearchMenuController;
    }
}