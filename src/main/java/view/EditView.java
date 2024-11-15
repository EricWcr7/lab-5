package view;

import interface_adapter.edit.EditController;
import interface_adapter.edit.EditViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class EditView extends JPanel implements ActionListener, PropertyChangeListener {
    private final String viewName = "Edit recipe";
    private final EditViewModel editViewModel;
    private EditController editController;
    private final JButton back;

    public EditView(EditViewModel editViewModel) {
        this.editViewModel = editViewModel;
        this.editViewModel.addPropertyChangeListener(this);

        final JLabel title = new JLabel("my edit recipe");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        final JPanel buttons = new JPanel();
        back = new JButton("Back");
        buttons.add(back);

        this.add(title);
        this.add(buttons);
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

    public void setEditController(EditController editController) {
        this.editController = editController;
    }
}
