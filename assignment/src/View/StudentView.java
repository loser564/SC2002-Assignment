package View;

import java.util.Scanner;
import Model.Camp.Camp;
import Model.CampComm.CampCommiteeManager;
import Model.Student.Student;
import Model.User.UserRole;

/**
* The StudentView class represents the view for students in the CAMs.
* It provides a menu for students to interact with various features such as changing passwords, viewing camps,
* registering for camps, quitting camps, applying for camp committees, viewing registered camps, submitting and managing
* enquiries, and accessing the camp committee menu.
*
* @author Alicia
* @version 1.0
* @since 2023-11-19
*/
public class StudentView implements MainView {
    /**
    * Constructs a StudentView instance with the given user ID and password.
    *
    * @param userID The user ID of the student.
    * @param password The password of the student.
    */
    public StudentView(String UserID, String password ){
        

    }

    /**
    * Prints the menu options available to the student.
    */
    @Override
    public void printMenu(){
        System.out.println("1. Change Password");
        System.out.println("2. View Camps");
        System.out.println("3. Register for a camp");
        System.out.println("4. Quit a camp");
        System.out.println("5. Apply to be a camp committee");
        System.out.println("6. View registered camps");
        System.out.println("7. Submit Enquiry");
        System.out.println("8. Edit and Delete Enquiry");
        System.out.println("9. Camp Committee Menu");
        System.out.println("10. Logout");
    }

    /**
    * Displays the main application view for students, allowing them to interact with different features based on their choices.
    *
    * @param userID The user ID of the student.
    * @param password The password of the student.
    */
    @Override
    public void viewApp(String userID, String password){
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        Student student = new Student();
        student.setUserID(userID);
        student.setPassword(password);
        student.setRole(UserRole.STUDENT);
        System.out.println("Student Role: " + student.getUserRole());

        System.out.println("Welcome " + userID + "!");
        boolean pwChange = false;
        boolean loggedIn = true;
        while(loggedIn == true){
            try{
                printMenu();
                choice = sc.nextInt();
                sc.nextLine();
                StudentFunctions studentFunc = new StudentFunctions();

                    switch(choice){
                            case 1: // change password
                                System.out.println("Changing password ....");
                                if(pwChange == false){
                                    pwChange = studentFunc.changePassword(student,userID,password);
                                    break;
                                }
                                else{
                                    System.out.println("You have already changed your password!");
                                    System.out.println("Returning to Student Menu...");
                                    break;
                                }
                                
                            case 2: // view camps
                                System.out.println("Viewing camps available ....");
                                studentFunc.viewCamps(student);
                                break;
                            case 3: // register for a camp
                                System.out.println("Registering for a camp ....");
                                System.out.println("Debug: " + student.getUserID());    
                                studentFunc.registerForCamp(student);
                                break;
                            case 4: // quit a camp
                                System.out.println("Quitting a camp ....");
                                studentFunc.quitCamp(student);
                                break;
                            case 5: // apply to be a camp committee
                                System.out.println("Applying to be a camp committee ....");
                                studentFunc.applyCampCommittee(student);
                                break;
                            case 6: // view registered camps
                                System.out.println("Viewing registered camps ....");
                                studentFunc.viewRegisteredCamps(student);

                                break;
                            case 7: // view existing enquiries and submit enquiry
                                System.out.println("Submitting an enquiry ....");
                                System.out.println("List of existing enquiries:");
                                studentFunc.viewAllEnquiries(student);
                                System.out.println("Do you still want to submit an enquiry? (Y/N): ");
                                char choice2 = sc.nextLine().charAt(0);
                                if (choice2 == 'Y'){
                                    studentFunc.submitEnquiry(student);
                                    System.out.println("Returning to Student Menu...");
                                    break;
                                } else {
                                    System.out.println("Enquiry not submitted!");
                                    System.out.println("Returning to Student Menu...");
                                    break;
                                }

                            case 8: // edit and delete enquiry
                                System.out.println("Editing and deleting an enquiry ....");
                                System.out.println("Do you want to edit or delete an enquiry (E/D): ");
                                char choice3 = sc.nextLine().charAt(0);
                                if (choice3 == 'E')
                                    studentFunc.editEnquiry(student);
                                else if (choice3 == 'D')
                                    studentFunc.deleteEnquiry(student);
                                else{
                                    System.out.println("Invalid input! Please enter E or D.");
                                }


                                break;

                            case 9: // camp committee menu
                                System.out.println("Loading Camp Committee Menu...");
                                // check if student is a camp committee
                                System.out.println("What camp are you a camp committee member for?");
                                System.out.print("Enter camp name: ");
                                String campName = sc.nextLine();
                                Camp camp = student.getCamp(campName);
                                if(CampCommiteeManager.readCampCommFile(campName, student.getUserID())){
                                    CampCommView campCommView = new CampCommView(userID, password);
                                    campCommView.viewApp(student.getUserID(), student.getPassword());
                                }
                                else{
                                    System.out.println("You are not a camp committee member for this camp!");
                                }
                                break;


                                
                            case 10: // logout
                                loggedIn = false;
                                System.out.println("Logging out...");
                                break;
                            default:
                                System.out.println("Invalid input! Please enter a number from 1 to 9.");
                                break;
                                
                            } 

                } catch(Exception e){
                    System.out.println("An error occurred: " + e.getMessage());
                    return;
                    
                }
        
        


    }
    }
}