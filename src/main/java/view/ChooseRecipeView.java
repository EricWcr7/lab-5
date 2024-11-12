package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import interface_adapter.choose_recipe.ChooseRecipeController;
import interface_adapter.choose_recipe.ChooseRecipeState;
import interface_adapter.choose_recipe.ChooseRecipeViewModel;
import interface_adapter.recipe_search.RecipeSearchState;

import javax.swing.*;

//public class ChooseRecipeView extends JPanel implements ActionListener, PropertyChangeListener  {
public class ChooseRecipeView extends JPanel implements PropertyChangeListener {

    private final String viewName = "choose recipe";
    private final ChooseRecipeViewModel chooseRecipeViewModel;
    private ChooseRecipeController chooseRecipeController;

    private final JButton goBack;
    private final JTextField dishInputField = new JTextField(50);

    public ChooseRecipeView(ChooseRecipeViewModel chooseRecipeViewModel) {
        this.chooseRecipeViewModel = chooseRecipeViewModel;
        this.chooseRecipeViewModel.addPropertyChangeListener(this);
        goBack = new JButton("Go back to Search View ");
        final JPanel searchPanel = new JPanel();
        searchPanel.add(dishInputField);
        searchPanel.add(goBack);
        this.add(searchPanel);
//        goBack.addActionListener(
//                // This creates an anonymous subclass of ActionListener and instantiates it.
//                new ActionListener() {
//                    // 监听 点击search 的行为
//                    public void actionPerformed(ActionEvent evt) {
//                        if (evt.getSource().equals(goBack)) {
//                            // 返回一个初始化的search state
//                            // 由于监听器的存在 所以会实时的更新 current state中的内容 然后controller就能
//                            // 根据这些数据开始run usecase
//                            final RecipeSearchState currentState = recipeSearchViewModel.getState();
//
//                            recipeSearchController.execute(
//                                    currentState.getSearchKeyword()
//                            );
//                        }
//                    }
//                }
//        );
    }

    public String getViewName() {
        return viewName;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        final ChooseRecipeState chooseRecipeState = (ChooseRecipeState) evt.getNewValue();
        setFields(chooseRecipeState);
    }

    private void setFields(ChooseRecipeState chooseRecipeState) {
        dishInputField.setText(chooseRecipeState.getSearchKeyword());
    }
}