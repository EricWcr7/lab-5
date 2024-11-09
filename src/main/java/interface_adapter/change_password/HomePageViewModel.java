package interface_adapter.change_password;

import interface_adapter.ViewModel;

public class HomePageViewModel extends ViewModel<LoggedInState> {
    public HomePageViewModel() {
        super("home page");
        setState(new LoggedInState());
    }
}
