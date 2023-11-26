package View;

import Model.Camp.Camp;
import Model.CampComm.CampCommitee;
import Model.CampComm.CampCommiteeManager;

import java.util.Scanner;


public class CampCommView implements MainView {

    public CampCommView(String userID, String password){}

    @Override
    public void printMenu(){
        System.out.println("1.View Details");
        System.out.println("2.Make Suggestions");
        System.out.println("3.View My Suggestions");
        System.out.println("4. Edit My Suggestions");
        System.out.println("5. Delete My Suggestions");
        System.out.println("6. Reply to Enquiry");
        System.out.println("7. Generate Report");
        System.out.println("8. Logout");
    }

    @Override
    public void viewApp(String userID, String password){
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        CampCommitee campComm = new CampCommitee();
        campComm.setUserID(userID);
        campComm.setPassword(password);
        String campName = CampCommiteeManager.findCampForCommitteeMember(userID);
        Camp camp = campComm.getCamp(campName);


        System.out.println("Welcome " + userID + " to Camp Committee Menu!");
        boolean loggedIn = true;
        while(loggedIn == true){
            try{
                printMenu();
                choice = sc.nextInt();
                sc.nextLine();
                CampCommFunctions campCommFunc = new CampCommFunctions();

                switch(choice){
                    case 1:
                        System.out.println("Viewing Details of Camp...");
                        campCommFunc.viewDetails(campComm, camp);
                        break;

                    case  2:
                        System.out.println("Making Suggestions...");
                        campCommFunc.makeSuggestions(campComm);
                        break;

                    case 3:
                        System.out.println("Viewing My Suggestions...");
                        campCommFunc.viewMySuggestions(campComm);
                        break;

                    case 4:
                        System.out.println("Editing My Suggestions...");
                        campCommFunc.editMySuggestions(campComm);
                        break;

                    case 5:
                        System.out.println("Deleting My Suggestions...");
                        campCommFunc.deleteMySuggestions(campComm);
                        break;

                    case 6:
                        System.out.println("Replying to Enquiry...");
                        campCommFunc.replyToEnquiry(campComm);
                        break;
                    case 7:
                        System.out.println("Generating Report...");
                        campCommFunc.generateReport(campComm, camp, campName);

                    case 8:
                        loggedIn = false;
                        System.out.println("Logging out...");
                        break;

                    default:
                        System.out.println("Invalid choice!");
                        break;
                }


            } catch(Exception e){
                System.out.println("An error occurred: " + e.getMessage());
                return;
            }
        }
    }


}
