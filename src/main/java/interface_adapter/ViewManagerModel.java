package interface_adapter;

/**
 * Model for the View Manager. Its state is the name of the View which
 * is currently active. An initial state of "" is used.
 */
public class ViewManagerModel extends ViewModel<String> {

    public ViewManagerModel() {
        super("view manager");
        this.setState(""); // or "sign up" if that should be the default
        System.out.println("Initial ViewManagerModel state set to: " + this.getState()); // Debug line
    }

    /**
     * Method to update the current active view name.
     * @param viewName The name of the view to switch to
     */
    public void switchToView(String viewName) {
        this.setState(viewName);
    }
}

