import java.util.Scanner;

// UserInterface.java
public class UserInterface {
    private UserManager userManager;

    public UserInterface(UserManager userManager) {
        this.userManager = userManager;
    }

    public void displayLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the CAMS Login System");
        System.out.print("Enter your user ID: ");
        String userID = scanner.nextLine();
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        if (userManager.authenticate(userID, password)) {
            System.out.println("Login successful!");
            // Additional logic after successful login
        } else {
            System.out.println("Login failed. Please check your credentials.");
        }
        // Add logic for changing password if needed
        scanner.close();
    }

    public static void main(String[] args) {
        UserManager userManager = new UserManager();
        // Replace the file paths with the actual paths to your CSV files
        try {
            userManager.loadUsersFromCSV("staff_list.csv");
            userManager.loadUsersFromCSV("student_list.csv");
        } catch (IOException e) {
            System.out.println("Error loading user data.");
            return;
        }

        UserInterface ui = new UserInterface(userManager);
        ui.displayLogin(); // Call the login display method
    }
}



  