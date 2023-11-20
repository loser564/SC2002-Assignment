package View;

import Model.*;

import java.util.Scanner;


public class CampCommView {
    
    public CampCommView(){}

    public static void campCommView(String userID,String password, Camp camp){

        Scanner sc =  new Scanner(System.in);
        CampCommitee campComm = new CampCommitee();
        // ArrayList<Student> campComms = UserManager.readCampComm();

        int choice = 0;

        do{
            System.out.println("Welcome to the Camp Committee Menu!");
            System.out.println("1.View Details");
            System.out.println("2.Make Suggestions");
            System.out.println("3.View My Suggestions");
            System.out.println("4. Edit My Suggestions");
            System.out.println("5. Delete My Suggestions");
            System.out.println("6. Reply to Enquiry");
            System.out.println("7. Generate Report");
            System.out.println("8. Logout");

            System.out.println("Please enter your choice: ");
            try{
                choice = sc.nextInt();
                sc.nextLine();
                CampCommFunctions campCommFunc = new CampCommFunctions();

                switch(choice){
                    case 1:
                        System.out.println("Viewing Details of Camp...");
                        campCommFunc.viewDetails(campComm, camp);

                    case  2:
                        System.out.println("Making Suggestions...");
                        campCommFunc.makeSuggestions(campComm);

                    case 3:
                        System.out.println("Viewing My Suggestions...");
                        campCommFunc.viewMySuggestions(campComm);

                    case 4:
                        System.out.println("Editing My Suggestions...");
                        campCommFunc.editMySuggestions(campComm);

                    case 5:
                        System.out.println("Deleting My Suggestions...");
                        campCommFunc.deleteMySuggestions(campComm);

                    case 6:
                        System.out.println("Replying to Enquiry...");
                        campCommFunc.replyToEnquiry(campComm);

                    case 7:
                        System.out.println("Generating Report...");
                        String campName = camp.getCampName();
                        campCommFunc.generateReport(campComm, camp, campName);

                    case 8:
                        System.out.println("Logging out...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                        break;



                }
            }   catch(Exception e){
                System.out.println("Invalid input!");
                sc.nextLine();
            }

            
        } while(choice != 9);
    sc.close();
    }

}
