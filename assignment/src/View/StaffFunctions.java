package View;
import Model.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;


public class StaffFunctions {
    public StaffFunctions(){}

    private Date StringToDate(String dateString) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/mm/yyyy");

        try {
            Date date = formatter.parse(dateString);
            return date;
        } catch (ParseException e) {
            System.out.println("Error parsing date: " + dateString);
            return null;
        }
    }
    /////////////// CAMP FUNCTIONS ///////////////  
    public void addCamp(Staff staff){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter camp name: ");
        String campName = sc.nextLine();
        System.out.println("Enter camp description: ");
        String description = sc.nextLine();
        System.out.println("Enter start date (dd/mm/yyyy): ");
        Date startDate = StringToDate(sc.nextLine());
        System.out.println("Enter end date (dd/mm/yyyy): ");
        Date endDate = StringToDate(sc.nextLine());
        System.out.println("Enter registration deadline (dd/mm/yyyy): ");
        Date registrationDeadline = StringToDate(sc.nextLine());
        System.out.println("Enter user group (FACULTY/ALL): ");
        String userGroup = sc.nextLine();
        System.out.println("Enter location: ");
        String location = sc.nextLine();
        System.out.println("Enter max capacity: ");
        int maxCapacity = sc.nextInt();
        System.out.println("Enter number of camp committee slots (max 10): ");
        int campCommitteeSlots = sc.nextInt();
        try{
            staff.createCamp(campName, startDate, endDate, registrationDeadline, userGroup, location, maxCapacity, campCommitteeSlots, description);
        }
        catch(IOException e){
            System.out.println("Error creating camp!");
        }
    }

    public void viewMyCamps(Staff staff){
        ArrayList<Camp> camps = staff.viewOwnCamps();
        System.out.println("List of camps:");
        for(Camp c: camps){
            staff.printCampDetails(c);
        }
    }

    public void viewAllCamps(Staff staff){
        ArrayList<Camp> camps = staff.viewAllCamps();
        System.out.println("List of camps:");
        for(Camp c: camps){
            staff.printCampDetails(c);
        }
    }

    public void editCamp(Staff staff, Camp camp) throws IOException{

        System.out.println("What do you want to edit?");
        System.out.println("Note camp name cannot be edited");
        System.out.println("1. Camp description");
        System.out.println("2. Start date");
        System.out.println("3. End date");
        System.out.println("4. Registration deadline");
        System.out.println("5. User group");
        System.out.println("6. Location");
        System.out.println("7. Max capacity");
        System.out.println("8. Camp committee slots"); 
        System.out.println("9. Exit to main menu");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        sc.nextLine();

        do{
            switch (choice){
                case 1: // camp description
                    System.out.println("Enter new camp description: ");
                    String description = sc.nextLine();
                    staff.editCampDescription(camp, description);
                    break;
                
                case 2: // start date
                    System.out.println("Enter new start date (dd/mm/yyyy): ");
                    Date startDate = StringToDate(sc.nextLine());
                    staff.editStartDate(camp, startDate);
                    break;

                case 3: // end date
                    System.out.println("Enter new end date (dd/mm/yyyy): ");
                    Date endDate = StringToDate(sc.nextLine());
                    staff.editEndDate(camp, endDate);
                    break;

                case 4: // registration deadline
                    System.out.println("Enter new registration deadline (dd/mm/yyyy): ");
                    Date registrationDeadline = StringToDate(sc.nextLine());
                    staff.editRegistrationDeadline(camp, registrationDeadline);
                    break;

                case 5: // user group
                    System.out.println("Enter new user group (FACULTY/ALL): ");
                    String userGroup = sc.nextLine();
                    staff.editUserGroup(camp, userGroup);
                    break;

                case 6: // location
                    System.out.println("Enter new location: ");
                    String location = sc.nextLine();
                    staff.editLocation(camp, location);
                    break;
                
                case 7: // max capacity
                    System.out.println("Enter new max capacity: ");
                    int maxCapacity = sc.nextInt();
                    staff.editMaxCapacity(camp, maxCapacity);
                    break;

                case 8: // camp committee slots
                    System.out.println("Enter new number of camp committee slots (max 10): ");
                    int campCommitteeSlots = sc.nextInt();
                    staff.editCampCommitteeSlots(camp, campCommitteeSlots);
                    break;

                case 9: // exit
                    break;
                
                default:
                    System.out.println("Invalid choice!");
                    break;




            }
        } while (choice != 9);
        

    }

    public void deleteCamp(Staff staff, Camp camp) throws IOException{
        staff.deleteCamp(camp);
    }

    public void changeCampVisibility(Staff staff, Camp camp) throws IOException{
        staff.changeCampVisibility(camp);
    }

    public void viewAllEnquiries(Staff staff) throws IOException{
        ArrayList<Enquiry> myCampEnquiries  = staff.viewMyCampsEnquiries();
        System.out.println("List of enquiries for my camp(s):");
        for(Enquiry e: myCampEnquiries){
            staff.printEnquiryDetails(e);
        }
    }

    public void replyEnquiry(Staff staff) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter enquiry ID: ");
        int enquiryID = sc.nextInt();
        System.out.println("Enter reply: ");
        String reply = sc.nextLine();
        staff.replyEnquiry(enquiryID, reply);
    }


    public void viewSuggestions(Staff staff) throws IOException{
        ArrayList<Suggestion> suggestions = staff.viewMyCampsSuggestions();
        System.out.println("List of suggestions:");
        for(Suggestion s: suggestions){
            staff.printSuggestionDetails(s);
        }
    }

    public void approveSuggestions(Staff staff) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter suggestion ID: ");
        int suggestionID = sc.nextInt();
        staff.approveSuggestion(suggestionID);
    }

    public void generateReport(Staff staff, Camp camp, String campName){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your filters...");    
        System.out.println("If you do not wish to filter by a certain field, enter null");
        System.out.println("Facualty filter: ");
        String faculty = sc.nextLine();

        System.out.println("Role filter: ");
        Boolean role = sc.nextBoolean();

        staff.generateReport(faculty, role, camp, campName);
    }

    public void generatePerformanceReport(Staff staff) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter camp name: ");
        String campName = sc.nextLine();
        Camp camp = staff.getCamp(campName);
        String filePath = "src/View/PerformanceReport.txt";
        Staff.generatePerformanceReport(staff, camp, filePath);
    }









    
}
