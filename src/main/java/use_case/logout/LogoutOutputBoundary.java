package use_case.logout;

/**
 * The output boundary for the Login Use Case.
 */
public interface LogoutOutputBoundary {
    /**
     * Prepares the success view for the Login Use Case.
     */
    void prepareSuccessView();

    /**
     * Prepares the failure view for the Login Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
