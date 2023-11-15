package src.view;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import src.basic.Camp;
import src.basic.CampCommittee;



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

    

    private static void suggestionStuff(){

    }

    private static void enquiryStuff(){

    }

    private static void generateReport(){

    }

}
