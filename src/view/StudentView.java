package src.view; 
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import src.basic.Camp;

public class StudentView extends MainView {
    public StudentView(){
        super();
    }

    @Override
    public void printMenu(){
        System.out.println("1. View and Register for Camps");
        System.out.println("2. Make, edit or delete Enquiries");
        System.out.println("3. View and Withdraw from Registered Camps");
        System.out.println("4. Change Password");
        System.out.println("5. Logout");
        
    }

    @Override
    public void viewApp(){
        int choice;
        printMenu();
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // View and Register for Camps
                    System.out.println("1. View Camps");
                    System.out.println("2. Register for Camps");

                    int choice2 = sc.nextInt();
                    switch (choice2) {
                        case 1:
                            // View Camps
                            Camp.getListOfCamps();
                            
                            break;
                        case 2:
                            // Register for Camps
                            break;
                        default:
                            System.out.println("Invalid choice");
                    }
                    break;
                case 2:
                    // Make, edit or delete Enquiries
                    break;
                case 3:
                    // View and Withdraw from Registered Camps
                    break;
                case 4:
                    // Change Password
                    break;
                case 5:
                    // Logout
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 5);
    }
}
