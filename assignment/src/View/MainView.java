package View;

/**
 * The MainView interface represents the main user interface for different roles in the CAMs.
 * Classes implementing this interface are expected to provide methods for viewing the application and printing the menu.
 * This interface serves as a foundation for creating specific views for different user roles.
 *
 * Implementing classes should define the behavior of viewing the application and printing the menu based on the role's requirements.
 * The methods defined in this interface are intended to be part of a common structure shared by various user views in the system.
 *
 * @author Alicia
 * @version 1.0
 * @since 2023-11-19
 */

interface MainView {
    
    /**
     * Displays the main application view based on the provided user ID and password.
     * The specific implementation should define the behavior of the application view for the corresponding user role.
     *
     * @param userID The user ID associated with the user.
     * @param password The password associated with the user ID.
     */

    public abstract void viewApp(String userID, String password);

    /**
     * Prints the menu options for the main application view.
     * The specific implementation should define the menu items based on the user role's functionalities.
     */

    public abstract void printMenu();

    
}
