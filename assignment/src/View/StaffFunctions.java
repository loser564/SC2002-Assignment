package View;
import Model.Camp.*;
import Model.EnquirySuggestion.*;
import Model.Staff.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * The StaffFunctions class encapsulates various functionalities related to a Staff member's interaction
 * with the CAMs. It provides methods for managing camps, handling enquiries, processing suggestions, 
 * and generating reports. This class serves as part of the View layer in the CAMs application.
 * @author Alicia
 * @version 7.0
 * @since 2023-11-19
 */

public class StaffFunctions {
    /**
     * Default constructor for the StaffFunctions class.
     */
    public StaffFunctions(){}

    /**
     * Converts a string representation of a date to a Date object.
     *
     * @param dateString The string representation of the date (format: dd/mm/yyyy).
     * @return The parsed Date object or null if parsing fails.
     */

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
    /**
     * Adds a camp to the system with user input for camp details.
     *
     * @param staff The Staff instance responsible for managing camps.
     */
    public void addCamp(Staff staff){
        boolean reg = true;
        while (reg){

        
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
            System.out.println("Camp created successfully!");
        }
        catch(IOException e){
            System.out.println("Error creating camp!");
        }

        System.out.println("Do you want to add another camp? (Y/N)");
        String choice = sc.nextLine();
        if(choice.equals("Y")){
            addCamp(staff);
        }
        else{
            reg = false;
            System.out.println("Exiting to main menu...");
            return; // return to student menu
            
        }
    }

        
    }

    /**
     * Displays the camps owned by the Staff member.
     *
     * @param staff The Staff instance responsible for managing camps.
     * @throws IOException If an I/O error occurs during camp retrieval.
     */
    public void viewMyCamps(Staff staff) throws IOException{
        System.out.println("Entering viewMyCamps");
        ArrayList<Camp> camps;
        try{
            camps = staff.viewOwnCamps();
            System.out.println("List of camps:");
            for(Camp c: camps){
                staff.printCampDetails(c);
            }
        }
        catch(Exception e){
            System.out.println("Error viewing camps!");
        }
        System.out.println("Press enter to return to Staff Menu...");
        while(true){
            Scanner sc = new Scanner(System.in);
            String enter = sc.nextLine();
            if(enter.isEmpty()){
                // sc.close();
                return;
                // return to student menu
            }
        }
        
    }

    /**
     * Displays all camps in the system.
     *
     * @param staff The Staff instance responsible for managing camps.
     */
    public void viewAllCamps(Staff staff){
        ArrayList<Camp> camps;
        camps = staff.viewAllCamps();
        try{
            camps = staff.viewAllCamps();
            System.out.println("List of camps:");
            for(Camp c: camps){
                staff.printCampDetails(c);
            }
        }
        catch(Exception e){
            System.out.println("Error viewing camps!");
        }
        System.out.println("Press enter to return to Staff Menu...");
        while(true){
            Scanner sc = new Scanner(System.in);
            String enter = sc.nextLine();
            if(enter.isEmpty()){
                // sc.close();
                return;
                // return to student menu
            }
        }
    }

    /**
     * Allows the Staff member to edit the details of a camp based on user input.
     *
     * @param staff The Staff instance responsible for managing camps.
     * @param camp The Camp instance to be edited.
     * @throws IOException If an I/O error occurs during the editing process.
     */
    
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

    /**
     * Deletes a camp from the system based on the provided Staff and Camp instances.
    *
    * @param staff The Staff instance responsible for managing camps.
    * @param camp The Camp instance to be deleted.
    * @throws IOException If an I/O error occurs during the deletion process.
    */
    public void deleteCamp(Staff staff, Camp camp) throws IOException{
        staff.deleteCamp(camp);
    }

    /**
    * Changes the visibility status of a camp based on the provided Staff and Camp instances.
    *
    * @param staff The Staff instance responsible for managing camps.
    * @param camp The Camp instance whose visibility is to be changed.
    * @throws IOException If an I/O error occurs during the visibility change process.
    */
    public void changeCampVisibility(Staff staff, Camp camp) throws IOException{
        staff.changeCampVisibility(camp);
    }

    /**
    * Displays all enquiries related to camps owned by the Staff member.
    *
    * @param staff The Staff instance responsible for managing camps.
    * @throws IOException If an I/O error occurs during the enquiry retrieval process.
    */
    public void viewAllEnquiries(Staff staff) throws IOException{
        ArrayList<Enquiry> myCampEnquiries  = staff.viewMyCampsEnquiries();
        System.out.println("List of enquiries for my camp(s):");
        try{
            for(Enquiry e: myCampEnquiries){
                staff.printEnquiryDetails(e);
            }
        }
        catch(Exception e){
            System.out.println("Error viewing enquiries!");
        }
        
        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.println("Press enter to return to Staff Menu...");
            String enter = sc.nextLine();
            if(enter.isEmpty()){
                // sc.close();
                return;
                // return to student menu
            }
        }
    }

    /**
    * Allows the Staff member to reply to a camp-related enquiry based on user input.
    *
    * @param staff The Staff instance responsible for managing enquiries.
    * @throws IOException If an I/O error occurs during the reply process.
    */
    public void replyEnquiry(Staff staff) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter enquiry ID: ");
        int enquiryID = sc.nextInt();
        System.out.println("Enter reply: ");
        String reply = sc.nextLine();
        staff.replyEnquiry(enquiryID, reply);
    }

    /**
    * Displays all suggestions related to camps owned by the Staff member.
    *
    * @param staff The Staff instance responsible for managing suggestions.
    * @throws IOException If an I/O error occurs during the suggestion retrieval process.
    */
    public void viewSuggestions(Staff staff) throws IOException{
        ArrayList<Suggestion> suggestions = staff.viewMyCampsSuggestions();
        System.out.println("List of suggestions:");
        try{
            for(Suggestion s: suggestions){
                staff.printSuggestionDetails(s);
            }
        }
        catch(Exception e){
            System.out.println("Error viewing suggestions!");
        }
        
        while (true){
            Scanner sc = new Scanner(System.in);
            System.out.println("Press enter to return to Staff Menu...");
            String enter = sc.nextLine();
            if(enter.isEmpty()){
                // sc.close();
                return;
                // return to student menu
            }
        }
    }

    /**
    * Allows the Staff member to approve a suggestion based on user input.
    *
    * @param staff The Staff instance responsible for managing suggestions.
    * @throws IOException If an I/O error occurs during the approval process.
    */
    public void approveSuggestions(Staff staff) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter suggestion ID: ");
        int suggestionID = sc.nextInt();
        staff.approveSuggestion(suggestionID);
    }

    /**
    * Generates a report for a specific camp based on the provided filters and Staff instance.
    *
    * @param staff The Staff instance responsible for managing reports.
    * @param camp The Camp instance for which the report is generated.
    * @param campName The name of the camp for which the report is generated.
    * @throws IOException If an I/O error occurs during the report generation process.
    */
    public void generateReport(Staff staff, Camp camp, String campName) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your filters...");    
        System.out.println("If you do not wish to filter by a certain field, enter null");
        System.out.println("Facualty filter: ");
        String faculty = sc.nextLine();

        System.out.println("Role filter: ");
        Boolean role = sc.nextBoolean();

        staff.generateReport(faculty, role, camp, campName);
    }

    /**
    * Generates a performance report for a specific camp based on user input and the Staff instance.
    *
    * @param staff The Staff instance responsible for managing reports.
    * @throws IOException If an I/O error occurs during the performance report generation process.
    */
    public void generatePerformanceReport(Staff staff) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter camp name: ");
        String campName = sc.nextLine();
        Staff.generatePerformanceReport(staff, campName);
    }









    
}
