package interface_adapter.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInState;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.recipe_search.RecipeSearchViewModel;
import use_case.logout.LogoutOutputBoundary;
import use_case.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                          RecipeSearchViewModel recipeSearchViewModel,
                           LoginViewModel loginViewModel) {
        // TODO: assign to the three instance variables.
        this.viewManagerModel = viewManagerModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView() {

        this.viewManagerModel.setState(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // No need to add code here. We'll assume that logout can't fail.
        // Thought question: is this a reasonable assumption?
    }
}
