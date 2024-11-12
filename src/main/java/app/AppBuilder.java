package app;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import data_access.InMemoryUserDataAccessObject;
import data_access.RecipeDataAccessObject;
import entity.*;
import interface_adapter.*;
import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuController;
import interface_adapter.ReturnToSearchMenu.ReturnToSearchMenuPresenter;
import interface_adapter.change_password.*;
import interface_adapter.choose_recipe.*;
import interface_adapter.login.*;
import interface_adapter.logout.*;
import interface_adapter.recipe_search.*;
import interface_adapter.signup.*;
import use_case.ReturnToSearchMenu.ReturnToSearchMenuInputBoundary;
import use_case.ReturnToSearchMenu.ReturnToSearchMenuInteractor;
import use_case.ReturnToSearchMenu.ReturnToSearchMenuOutputBoundary;
import use_case.change_password.*;
import use_case.login.*;
import use_case.logout.*;
import use_case.recipe_search.*;
import use_case.signup.*;
import view.*;

/**
 * The AppBuilder class is responsible for putting together the pieces of
 * our CA architecture; piece by piece.
 */
public class AppBuilder {
    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final UserFactory userFactory = new CommonUserFactory();
    private final RecipeFactory recipeFactory = new CommonRecipeFactory();
    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private final InMemoryUserDataAccessObject userDataAccessObject = new InMemoryUserDataAccessObject();

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private LoginViewModel loginViewModel;
    private LoggedInViewModel loggedInViewModel;
    private LoggedInView loggedInView;
    private LoginView loginView;
    private RecipeSearchView recipeSearchView;
    private RecipeSearchViewModel recipeSearchViewModel;
    private ChooseRecipeView chooseRecipeView;
    private ChooseRecipeViewModel chooseRecipeViewModel;

    // Updated reference to the RecipeSearchInteractor to allow initialization
    private RecipeSearchInteractor recipeSearchInteractor;

    public AppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Initializes the shared recipe storage with all recipes from the API.
     */
    private void initializeSharedRecipeStorage() {
        if (recipeSearchInteractor != null) {
            System.out.println("Calling initializeRecipeStorage in RecipeSearchInteractor...");
            recipeSearchInteractor.initializeRecipeStorage();
        } else {
            System.err.println("RecipeSearchInteractor not initialized. Please ensure addRecipeSearchUseCase is called first.");
        }
    }

    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public AppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public AppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the LoggedIn View to the application.
     * @return this builder
     */
    public AppBuilder addLoggedInView() {
        loggedInViewModel = new LoggedInViewModel();
        loggedInView = new LoggedInView(loggedInViewModel);
        cardPanel.add(loggedInView, loggedInView.getViewName());
        return this;
    }

    /**
     * Adds the RecipeSearch View to the application.
     * @return this builder
     */
    public AppBuilder addRecipeSearchView() {
        recipeSearchViewModel = new RecipeSearchViewModel();
        recipeSearchView = new RecipeSearchView(recipeSearchViewModel);
        cardPanel.add(recipeSearchView, recipeSearchView.getViewName());
        return this;
    }

    /**
     * Adds the ChooseRecipe View to the application.
     * @return this builder
     */
    public AppBuilder addChooseRecipeView() {
        chooseRecipeViewModel = new ChooseRecipeViewModel();
        chooseRecipeView = new ChooseRecipeView(chooseRecipeViewModel);
        cardPanel.add(chooseRecipeView, chooseRecipeView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public AppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(viewManagerModel,
                signupViewModel, loginViewModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLoginUseCase() {
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                recipeSearchViewModel, loginViewModel, signupViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Change Password Use Case to the application.
     * @return this builder
     */
    public AppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(loggedInViewModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        loggedInView.setChangePasswordController(changePasswordController);
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public AppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                recipeSearchViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        recipeSearchView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the ReturnToSearchMenu Use Case to the application.
     * @return this builder
     */
    public AppBuilder addReturnToSearchMenuUseCase() {
        final ReturnToSearchMenuOutputBoundary returnToSearchMenuOutputBoundary =
                new ReturnToSearchMenuPresenter(viewManagerModel,
                        recipeSearchViewModel, chooseRecipeViewModel);

        final ReturnToSearchMenuInputBoundary returnToSearchMenuInteractor =
                new ReturnToSearchMenuInteractor(returnToSearchMenuOutputBoundary);

        final ReturnToSearchMenuController returnToSearchMenuController = new ReturnToSearchMenuController(returnToSearchMenuInteractor);
        chooseRecipeView.setReturnToSearchMenuController(returnToSearchMenuController);
        return this;
    }

    /**
     * Adds the RecipeSearch Use Case to the application.
     * @return this builder
     */
    public AppBuilder addRecipeSearchUseCase() {
        final RecipeSearchOutputBoundary recipeSearchOutputBoundary = new RecipeSearchPresenter(
                viewManagerModel, chooseRecipeViewModel, recipeSearchViewModel);

        // Pass the output boundary (presenter) to the interactor and initialize the interactor instance
        recipeSearchInteractor = new RecipeSearchInteractor(recipeSearchOutputBoundary);

        final RecipeSearchController recipeSearchController = new RecipeSearchController(recipeSearchInteractor);
        recipeSearchView.setRecipeSearchController(recipeSearchController);
        return this;
    }

    /**
     * Creates the JFrame for the application and initially sets the SignupView to be displayed.
     * @return the application
     */
    public JFrame build() {
        final JFrame application = new JFrame("Mealmaster");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // Call the initializeSharedRecipeStorage to ensure recipes are loaded and saved
        initializeSharedRecipeStorage();

        application.add(cardPanel);

        viewManagerModel.setState(loginView.getViewName());
        viewManagerModel.firePropertyChanged();

        return application;
    }
}

