package src.view;

import java.util.Scanner;
import src.basic.Staff;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StaffView extends MainView {
    public StaffView() {
        super();
    }

    @Override
    public void printMenu() {
        System.out.println("1. Create, edit, delete camps");
        System.out.println("2. Toggle camp visibility");
        System.out.println("3. View all camps");
        System.out.println("4. View and reply enquiries");
        System.out.println("5. View and approve suggestions");
        System.out.println("6. Generate attendance or performance report");
        System.out.println("7. Change password");
        System.out.println("8. Logout");
    }

    @Override
    public void viewApp() {
        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            printMenu();
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1:
                    // Create, edit, delete camps:
                    System.out.println("1. Create camp");
                    System.out.println("2. Edit Camp");
                    System.out.println("3. Delete camp");

                    int choice2 = sc.nextInt();
                    switch (choice2) {
                        case 1:
                            // Create camp
							SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Date formatter
							try{
								System.out.println("Enter camp name: ");
								String campName = sc.nextLine();
								System.out.println("Enter start date (dd-MM-yyyy): ");
								String startDateStr = sc.nextLine();
								Date startDate = sdf.parse(startDateStr);
								System.out.println("Enter end date (dd-MM-yyyy): ");
								String endDateStr = sc.nextLine();
								Date endDate = sdf.parse(endDateStr);
								System.out.println("Enter registration closing date (dd-MM-yyyy): ");
								String registrationCloseDateStr = sc.nextLine();
								Date registrationCloseDate = sdf.parse(registrationCloseDateStr);
								System.out.println("Enter user group: ");
								String userGroup = sc.nextLine();
								System.out.println("Enter location: ");
								String location = sc.nextLine();
								System.out.println("Enter total slots: ");
								int totalSlots = sc.nextInt();
								System.out.println("Enter description: ");
								String description = sc.nextLine();
								Staff.createCamp(campName, startDate, endDate, registrationCloseDate, userGroup, 
												location, totalSlots, description);
							} catch (ParseException e){
								// Handle parsing exception
								System.out.println("Invalid date format. Please enter the date in the format dd-MM-yyyy.");
								e.printStackTrace(); // You might want to log the exception or handle it appropriately
							}
                            break;

                        case 2:
                            // Edit camp
                            break;

                        case 3:
                            // Delete camp
                            break;

                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                    break;

                case 2:
                    // Toggle camp visibility
                    break;

                case 3:
                    // View all camps
                    break;

                case 4:
                    // View and reply enquiries
                    System.out.println("1. View enquiries");
                    System.out.println("2. Delete enquiries");
                    int choice3 = sc.nextInt();
                    switch (choice3) {
                        case 1:
                            // View enquiries
                            break;

                        case 2:
                            // Delete enquiries
                            break;

                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                    break;

                case 5:
                    // View and approve suggestions
                    System.out.println("1. View suggestions");
                    System.out.println("2. Approve suggestions");
                    int choice4 = sc.nextInt();
                    switch (choice4) {
                        case 1:
                            // View suggestions
                            break;

                        case 2:
                            // Delete suggestions
                            break;

                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                    break;

                case 6:
                    // Generate attendance or performance report
                    System.out.println("1. Generate attendance report");
                    System.out.println("2. Generate performance report");
                    int choice5 = sc.nextInt();
                    switch (choice5) {
                        case 1:
                            // Generate attendance report
                            break;

                        case 2:
                            // Generate performance report
                            break;

                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                    break;

                case 7:
                    // Change password
                    break;

                case 8:
                    // Logout
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        } while (choice != 8); // Adjust the condition based on your intended loop exit condition
    }
}
