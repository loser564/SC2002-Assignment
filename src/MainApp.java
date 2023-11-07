package src;

import java.util.Scanner;
import src.view.*;




public class MainApp{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("==============================");        System.out.println("============Log In============");
        System.out.println("==============================");        // Perform authentication and user role identification
        System.out.println("Enter your username: ");        String username = sc.nextLine();
        System.out.println("Enter your password: ");        String password = sc.nextLine();
        // Replace with your authentication logic
        String userRole = authenticate(username, password);
        
        switch (userRole) {
            case "Student":
                // Perform actions for Student role
                StudentView();
                break;
            case "Staff":
                // Perform actions for Staff role
                StaffView();
                break;
            default:
                System.out.println("Invalid username or password");
        }
        

    }
    
    
    private static String authenticate(String username, String password) {        // Implement your authentication logic here
        // Return the user role as a result        // This is a placeholder, replace with your logic
        if (username.equals("student") && password.equals("password")) {            return "Student";
        } else if (username.equals("committee") && password.equals("password")) {            return "CampCommitteeMember";
        } else if (username.equals("staff") && password.equals("password")) {            return "Staff";
        } else {            return "Invalid";
        }    }
        CampAppView campAppView = new CampAppView();
        campAppView.viewApp();
    }
