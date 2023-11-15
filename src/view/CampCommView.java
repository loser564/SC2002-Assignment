package src.view;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import src.basic.Camp;
import src.basic.CampCommittee;

'''
• A camp committee member can view the details of the camp that they oversee.
• A camp committee member can view and reply to enquiries from students to the 
camp they oversee. 
• A camp committee member can view, edit, and delete the details of his/her 
suggestions before being processed. 
• A camp committee member can generate a report of the list of students attending 
each camp that they have created. The list will include details of the camp as well as 
the roles of the participants. There should be filters for how the camp committee 
member would want to generate the list. (attendee, camp committee, etc.) (generate in 
either txt or csv format). 
• A camp committee member can get one point for each enquiry replied and each 
suggestion given. One extra point will be granted for each accepted suggestion. 
• A camp committee member cannot quit from camp. 
'''

public class CampCommView extends MainView {
    public CampCommView(){
        super();
    }

    static int choice;
    private static Scanner sc = new Scanner(System.in);

    @Override
    public void printMenu(){
        System.out.println("1. View details of camp.");
        System.out.println("2. View, edit, delete and submit suggestions.");
        System.out.println("3. View and reply to enquiries.");
        System.out.println("4. Generate report. ");
        System.out.println("5. View Points");
        System.out.println("6. Change password");
        System.out.println("7. Logout");


    }

    @Override
    public void viewApp(){
        do{
            printMenu();
            choice = sc.nextInt();
            CampCommittee campComm = (CampCommittee) MainView.user;

            switch(choice){
                case 1:
                    viewCampDetails();
                    break;

                case 2:
                    suggestionStuff();
                    break;
                case 3:
                    enquiryStuff();
                    break;
                case 4:
                    generateReport();
                    break;

            }
        }
    }

    private static void viewCampDetails(){

    }

    private static void suggestionStuff(){

    }

    private static void enquiryStuff(){

    }

    private static void generateReport(){

    }

}
