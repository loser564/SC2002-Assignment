
import java.util.Scanner;
import java.util.ArrayList;
import Model.User.UserLogin;
import View.StaffView;
import View.StudentView;

/**
 * The CAMs class runs the CAMs application
 * It provides a simple command-line interface for user login and role-based access to the system.
 * The application supports two user roles: "student" and "staff," each having a dedicated view with specific functionalities.
 *
 * The main method presents a menu with options to either log in or exit the application. Upon successful login,
 * the user is directed to the appropriate view based on their role. User authentication is handled by the UserLogin class.
 *
 * This class utilizes the Scanner class for user input and interacts with the UserLogin, StudentView, and StaffView classes.
 * It demonstrates the use of exception handling to handle invalid input during user interaction.
 *
 * Example Usage:
 * 1. Login
 * 2. Exit
 *
 * Choosing "1" prompts the user to enter their userID and password. After successful authentication, the user is directed
 * to the corresponding view based on their role ("student" or "staff"). Invalid inputs or credentials are appropriately handled.
 *
 * Choosing "2" exits the application
 * @author Alicia
 * @version 1.0
 * @since 2023-11-19
 */

public class CAMs{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int choice = 0;

        boolean validUser;
        boolean validPassword;
        boolean pwChange;

        do{
            validUser =  false;
            validPassword = false;
            pwChange = false;

            System.out.println("Welcome to CAMs!");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.println("Enter your choice: ");

            try{
                choice = sc.nextInt();
            } catch(Exception e){
                System.out.println("Invalid input!");
                sc.nextLine();
            }

            if (choice == 1){
                sc.nextLine();
                System.out.println("Enter your userID: ");
                String userID = sc.nextLine();
                System.out.println("Enter your password: ");
                String password = sc.nextLine();

                UserLogin login = new UserLogin();
                validUser = login.verifyUser(userID);
                // System.out.println("Valid user: " + validUser);

                if(validUser){
                    validPassword = login.verifyLogin(userID, password);
                    // System.out.println("Valid password: " + validPassword);
                    if(validPassword){
                    // System.out.println("Login successful!");
                    String userRole = login.getUserType();
                    // System.out.println("User role: " + userRole);

                    switch(userRole){
                        // public StaffView(String UserID, String password){
                       //         super(UserID, password);
                        //    }
                        case "student":
                            StudentView studentView = new StudentView(userID, password);
                            studentView.viewApp(userID, password);
                            break;
                        
                        case "staff":
                            StaffView staffView = new StaffView(userID, password);
                            staffView.viewApp(userID, password);
                            break;

                        default:
                            System.out.println("Invalid user role!");
                            break;
                    }
                    }
                    else{
                        System.out.println("Invalid password!");
                    }

                }
                else{
                    System.out.println("Invalid userID!");
                }

                

            }

        } while(choice != 2);

        System.out.println("Thank you for using CAMs!");
        sc.close();
        


    }
}