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
import interface_adapter.choose_recipe.ChooseRecipeViewModel;

import javax.swing.*;

public class ChooseRecipeView extends JPanel {

    private final String viewName = "choose recipe";
    private final ChooseRecipeViewModel chooseRecipeViewModel;
    private ChooseRecipeController chooseRecipeController;

    private final JButton goBack;
    private final JTextField dishInputField = new JTextField(50);



    public ChooseRecipeView(ChooseRecipeViewModel chooseRecipeViewModel) {
        this.chooseRecipeViewModel = chooseRecipeViewModel;
        goBack = new JButton("Go back to Search View ");
        final JPanel searchPanel = new JPanel();
        searchPanel.add(dishInputField);
        searchPanel.add(goBack);
        this.add(searchPanel);
    }

    public String getViewName() {
        return viewName;
    }
}