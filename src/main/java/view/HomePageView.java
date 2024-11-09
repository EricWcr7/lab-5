package view;

import interface_adapter.change_password.HomePageViewModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.logout.LogoutController;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 * The View for when the user is logged into the program, and it is the main menu page.
 */
public class HomePageView extends JPanel implements PropertyChangeListener {
    private final String viewName = "home page";
    private final HomePageViewModel homePageViewModel;

    private LogoutController logoutController;
    // 还有其它的Controller先没写

    private final JLabel username;
    private final JButton logOut;

    private final JTextField searchField = new JTextField(20); // Search field with a width of 20 characters
    private final JButton searchButton = new JButton("Search"); // Search button
    private final JButton createdMealsButton = new JButton("Created Meals"); // Button to view created meals
    private final JButton favoriteMealsButton = new JButton("My Favorite Meals"); // Button to view favorite meals



    public HomePageView(HomePageViewModel homePageViewModel) {
        this.homePageViewModel = homePageViewModel;

        username = new JLabel("Welcome, ");
        logOut = new JButton("Log Out");

        // Set up the layout
        setupLayout();
        setupListeners();
    }

    private void setupLayout() {
        JFrame frame = new JFrame(viewName);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Top panel for search field and search button on the same line
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));  // Align components to the left
        topPanel.add(searchField);
        topPanel.add(searchButton);

        // Middle panel for createdMealsButton and favoriteMealsButton on the same line
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        middlePanel.add(createdMealsButton);
        middlePanel.add(favoriteMealsButton);

        // Bottom panel for logOut button on its own line
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.add(logOut);

        // Add panels to frame
        frame.add(topPanel, BorderLayout.NORTH);      // Search components at the top
        frame.add(middlePanel, BorderLayout.CENTER);  // Meal buttons in the center
        frame.add(bottomPanel, BorderLayout.SOUTH);   // Log out button at the bottom

        frame.setVisible(true);
    }

    private void setupListeners() {
        // Set up listeners for buttons or other actions
        logOut.addActionListener(
                // This creates an anonymous subclass of ActionListener and instantiates it.
                evt -> {
                    if (evt.getSource().equals(logOut)) {
                        final LoggedInState currentState = HomePageViewModel.getState();
                        this.logoutController.execute(currentState.getUsername());
                    }
                }
        );
        searchButton.addActionListener(e -> handleSearch());
        createdMealsButton.addActionListener(e -> handleCreatedMeals());
        favoriteMealsButton.addActionListener(e -> handleFavoriteMeals());
    }

    private void handleSearch() {
        String query = searchField.getText();
        // Perform search with the query
        System.out.println("Searching for: " + query);
    }

    private void handleCreatedMeals() {
        // Handle the "Created Meals" button action
        System.out.println("Viewing created meals...");
    }

    private void handleFavoriteMeals() {
        // Handle the "My Favorite Meals" button action
        System.out.println("Viewing favorite meals...");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Handle any property change events
    }



    public void setLogoutController(LogoutController logoutController) {
        // TODO: save the logout controller in the instance variable.
        this.logoutController = logoutController;
    }

//    public void setCreatedMealsController(CreatedMealsController createdMealsController) {
//        this.createdMealsController = createdMealsController;
//    }
//
//
//    public void setFavoriteMealsController(FavoriteMealsController favoriteMealsController) {
//        this.favoriteMealsController = favoriteMealsController;
//    }

}
