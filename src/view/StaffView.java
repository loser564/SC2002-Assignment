package src.view;
import src.basic.Staff;
import src.basic.Camp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import src.basic.Staff;



public class StaffView extends MainView {
        public StaffView(){
            super();
        }
        // global variables
        static int choice;
        static int choice2;
        private static Scanner sc = new Scanner(System.in);

        public void printMenu() {
            choice = 0;
            System.out.println("1. Create, edit, delete and edit visibility camp");
            System.out.println("2. View all camps");
            System.out.println("3. See camps I have created");
            System.out.println("4. View and reply to enquiries");
            System.out.println("5. View and approve suggestions");
            System.out.println("6. Generate Reports");
            System.out.println("7. Change password");
            System.out.println("8. Exit Staff View");
        }
    
    public void campStuff(Staff staff){
        do{
            System.out.println("1. Create new camp");
            System.out.println("2. Edit camp");
            System.out.println("3. Delete camp");
            System.out.println("4. Edit camp visibility");
            System.out.println("5. Quit");

            choice2 = sc.nextInt();
            switch (choice2) {
                case 1:
                    createNewCamp(staff);
                    break;
                case 2:
                    System.out.println("Enter camp name: ");
                    String campEntered = sc.next();
                    Camp campEdit = staff.getCampByName(campEntered);
                    if (campEdit != null){
                        editCreatedCamp(staff, campEdit);
                    }
                    else{
                        System.out.println("Camp not found");
                    }
                    break;
                case 3:
                    System.out.println("Enter camp name: ");
                    String campToDelete = sc.next();
                    Camp campDelete = staff.getCampByName(campToDelete);
                    if (campDelete != null){
                        deleteCamp(staff, campDelete);
                    }
                    else{
                        System.out.println("Camp not found");
                    }
                    break;
                case 4:
                    System.out.println("Enter camp name: ");
                    String campToEditVisibility = sc.next();
                    Camp campEditVisibility = staff.getCampByName(campToEditVisibility);
                    if (campEditVisibility != null){
                        editVisibility(staff, campEditVisibility);
                    }
                    else{
                        System.out.println("Camp not found");
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 3");
                    break;
                    
            }
            break;
        } while (choice2 != 4);
    }
        
    
    public void viewApp(){
        do{
            printMenu();
            choice = sc.nextInt();
            Staff staff = new Staff(userID,faculty);
            switch(choice){
                case 1:
                    campStuff(staff);
                    break;
                case 2:
                    // print all camps
                    System.out.println("Viewing all camps...");
                    break;
                case 3:
                    // print camps created by me
                    System.out.println("Viewing camps created by me...");
                    staff.printCreatedCamps();
                    break;
                case 4:
                    // view and reply to enquiries
                    
                    break;
                case 5:
                    // view and approve suggestions
                    
                    break;
                case 6:
                    // generate reports
                    
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 7");
                    break;
            }
        } while (choice != 7);

    }       
            
    private static void createNewCamp(Staff staff) {
        int campCommitteeSlots = 0;
        System.out.println("Enter camp name: ");
        String campName = sc.next();
        System.out.println("Date format must be yyyy-MM-dd");

        Date startDate;
        do {
            System.out.println("Enter start date: ");
            String date1 = sc.next();
            startDate = stringToDate(date1);
        } while (startDate == null);

       Date endDate;
        do {
            System.out.println("Enter start date: ");
            String date2 = sc.next();
            endDate = stringToDate(date2);
        } while (endDate == null);
        Date registrationCloseDate;
		do {
			System.out.println("Enter registration close date: ");
			String date3 = sc.next();
			registrationCloseDate = stringToDate(date3);
		} while (registrationCloseDate == null);
        System.out.println("Enter group camp available to: ");
        String userGroup = sc.next();
        System.out.println("Enter location: ");
        String location = sc.next();
        System.out.println("Enter total slots: ");
        int totalSlots = sc.nextInt();

        if (campCommitteeSlots < 10) {
            System.out.println("Enter camp committee slots (max 10): ");
            campCommitteeSlots = sc.nextInt();
            if (campCommitteeSlots > 10) {
                System.out.println("Invalid input. Please enter a number less than 10");
                return;
            }
        }

        System.out.println("Enter description: ");
        String description = sc.next();
        System.out.println("Enter staff ID: ");
        String userID = sc.next();
        System.out.println("Enter faculty: ");
        String faculty = sc.next();

        // Now you can use the gathered information to create the camp using the staff instance
        staff.createCamp(campName, startDate, endDate, registrationCloseDate, userGroup,
                location, totalSlots, campCommitteeSlots, description, userID, faculty);
    }

    private static Date stringToDate(String dateString) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(dateString);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter the date in the correct format.");
            return null;
        }
    }

    private static void editCreatedCamp(Staff staff, Camp camp){
        
        do{
            choice = 0;
            System.out.println("What aspect of the camp do you wish to edit");
            System.out.println("1. Camp Name");
            System.out.println("2. Start Date");
            System.out.println("3. End Date");
            System.out.println("4. Registration Closing Date");
            System.out.println("5. User Group");
            System.out.println("6. Location");
            System.out.println("7. Total Slots");
            System.out.println("8. Camp Committee Slots");
            System.out.println("9. Description");
            System.out.println("10. Quit")
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter new camp name: ");
                    String newCampName = sc.next();
                    staff.editCamp(camp, newCampName, null, null, newCampName, newCampName, newCampName, null, null, newCampName);
                    break;
                case 2:
                    System.out.println("Enter new start date: ");
                    String newStartDate = sc.next();
                    staff.editCamp(camp, null, stringToDate(newStartDate), null, null, null, null, null, null, null);
                case 3:
                    System.out.println("Enter new end date: ");
                    String newEndDate = sc.next();
                    staff.editCamp(camp, null, null, stringToDate(newEndDate), null, null, null, null, null, null);
                case 4:
                    System.out.println("Enter new registration closing date: ");
                    String newRegistrationClosingDate = sc.next();
                    staff.editCamp(camp, null, null, null, newRegistrationClosingDate, null, null, null, null, null);
                case 5:
                    System.out.println("Enter new user group: ");
                    String newUserGroup = sc.next();
                    staff.editCamp(camp, null, null, null, null, newUserGroup, null, null, null, null);
                case 6:
                    System.out.println("Enter new location: ");
                    String newLocation = sc.next();
                    staff.editCamp(camp, null, null, null, null, null, newLocation, null, null, null);
                case 7:
                    System.out.println("Enter new total slots: ");
                    int newTotalSlots = sc.nextInt();
                    staff.editCamp(camp, null, null, null, null, null, null, newTotalSlots, null, null);
                case 8:
                    System.out.println("Enter new camp committee slots: ");
                    int newCampCommitteeSlots = sc.nextInt();
                    staff.editCamp(camp, null, null, null, null, null, null, null, newCampCommitteeSlots, null);
                case 9:
                    System.out.println("Enter new description: ");
                    String newDescription = sc.next();
                    staff.editCamp(camp, null, null, null, null, null, null, null, null, newDescription);
                case 10:
                    break;
                default:
                    System.out.println("Invalid input. Please enter a number between 1 and 10");
                    break;
                }       
            } while (choice != 10);
}

    private static void deleteCamp(Staff staff, Camp camp){
        staff.deleteCamp(camp);
    }

    private static void editVisibility(Staff staff, Camp camp){
        System.out.print("Current visibility: ");
        System.out.println(camp.isVisible(camp));
        staff.toggleCampVisibility(camp);
        System.out.print("New visibility: ");
        System.out.println(camp.isVisible(camp));
    }

}

