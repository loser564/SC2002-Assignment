import src.view.MainView;
import src.basic.User;
import src.basic.UserManager;
import src.basic.UserRole;

import java.io.IOException;
import java.util.Scanner;
public class MainApp {
    public MainApp(UserManager userManager) {
        this.userManager = userManager;
    }
    private UserManager userManager;

    // login display
    public void displayLogin() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Welcome to the NTU Camp Management System!");
            System.out.println("==============================");
            System.out.println("============Log In============");
            System.out.println("==============================");
            // Perform authentication and user role identification
            System.out.print("Enter your user ID: ");
            String userID = scanner.nextLine();
            System.out.print("Enter your password: ");
            String password = scanner.nextLine();
    
            String role = userManager.authenticate(userID, password);
            if (role != null) {
                System.out.println("Login successful!");

                // Additional logic after successful login
                // For example, you can check the user role and redirect to the appropriate view
                switch (role) {
                    case UserRole.STUDENT:
                        // Redirect to student view
                        break;
                    case UserRole.CAMP_COMMITTEE:
                        // Redirect to camp committee view
                        break;
                    case UserRole.STAFF:
                        // Redirect to staff view
                        break;
                    // Add other cases as needed
                }
            } else {
                System.out.println("Login failed. Please check your credentials.");
            }
    }

    }

    //Application 
    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        try {
            userManager.loadUsersFromCSV("staff_list.csv");
            userManager.loadUsersFromCSV("student_list.csv");
        } catch (IOException e) {
            System.out.println("Error loading user data.");
            return;
        }

        MainApp mainApp = new MainApp(userManager);
        mainApp.displayLogin(); // Call the login display method
    }

    
}

