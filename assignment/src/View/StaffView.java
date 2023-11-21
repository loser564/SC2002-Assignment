package View;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import Model.*;
public class StaffView extends MainView{

    public StaffView(String UserID, String password){
        super(UserID, password);
    }

    @Override
    public void printMenu(){
        System.out.println("1. Change Password");
        System.out.println("2. Add a new camp");
        System.out.println("3. View my camps");
        System.out.println("4. Edit a camp");
        System.out.println("5. Delete a camp");
        System.out.println("6. Change camp visibility");
        System.out.println("7. View all camps");
        System.out.println("8. View all enquiries for my camp");
        System.out.println("9. Reply to an enquiry");
        System.out.println("10. View suggestions from camp committee");
        System.out.println("11. Approve suggestions from camp committee");
        System.out.println("12. Generate report about camps");
        System.out.println("13. Generate performance report about camp committee");
        System.out.println("14. Logout");
    }

    @Override
    public void viewApp(){
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        Staff staff = new Staff();
        String password = staff.getPassword();
        boolean pwChange = false;
        try{
            printMenu();
            choice = sc.nextInt();
            sc.nextLine();
            StaffFunctions staffFunc = new StaffFunctions();
            switch(choice){
                case 1: // change password
                        System.out.println("Change Password...");
                        System.out.println("Enter old password: ");
                        String oldPassword = sc.nextLine();
                        System.out.println("Enter new password: ");
                        String newPassword = sc.nextLine();
                        System.out.println("Confirm new password: ");
                        String confirmNewPassword = sc.nextLine();

                        if(!oldPassword.equals(password)){
                            System.out.println("Old password is incorrect!");
                        }
                        else if(!newPassword.equals(confirmNewPassword)){
                            System.out.println("New password does not match!");
                        }
                        else if(newPassword.equals(password)){
                            System.out.println("New password cannot be the same as old password!");
                        }
                        else if(oldPassword.isEmpty() || newPassword.isEmpty()){
                            System.out.println("Password cannot be empty!");
                        }
                        else{
                            pwChange = staff.changePassword(newPassword);
                            System.out.println("Password changed successfully!");
                            pwChange = true;
                        }
                        break;

                case 2: // add new camp
                    System.out.println("Adding a new camp...");
                    staffFunc.addCamp(staff);
                    break;
                case 3: // view my camps
                    System.out.println("Viewing my camps...");
                    staffFunc.viewMyCamps(staff);
                    break;

                case 4: // edit a camp
                    System.out.println("Editing a camp...");
                    System.out.println("Which camp do you want to edit");
                    String campNameEdit = sc.nextLine();
                    Camp campEdit = staff.getCamp(campNameEdit);
                    staffFunc.editCamp(staff, campEdit);
                    break;

                case 5: // delete a camp
                    System.out.println("Deleting a camp...");
                    System.out.print("Which camp do you want to edit: ");
                    String campNameDelete = sc.nextLine();
                    Camp campDel = staff.getCamp(campNameDelete);
                    staffFunc.deleteCamp(staff, campDel);
                    break;
                
                case 6: // change camp visibility
                    System.out.println("Changing camp visibility...");
                    System.out.print("Which camp do you want to change the visibility of? : ");
                    String campNameChange = sc.nextLine();
                    Camp campChange = staff.getCamp(campNameChange);
                    staffFunc.changeCampVisibility(staff, campChange);
                    break;
                
                case 7:
                    System.out.println("Viewing all camps...");
                    staffFunc.viewAllCamps(staff);
                    break;
                        
                case 8:
                    System.out.println("Viewing all enquiries for my camp...");
                    staffFunc.viewAllEnquiries(staff);
                    break;

                case 9:
                    System.out.println("Replying to an enquiry...");
                    staffFunc.replyEnquiry(staff);
                    break;
                
                case 10:
                    System.out.println("Viewing suggestions from camp committee...");
                    staffFunc.viewSuggestions(staff);
                    break;
                
                case 11:
                    System.out.println("Approving suggestions from camp committee...");
                    staffFunc.approveSuggestions(staff);
                    break;
                
                case 12:
                    System.out.println("Generating report about camps...");
                    System.out.println("Enter the camp name: ");
                    String campName = sc.nextLine();
                    Camp camp = staff.getCamp(campName);
                    staffFunc.generateReport(staff, camp, campName);
                    break;
                
                case 13:
                    System.out.println("Generating performance report about camp committee...");
                    staffFunc.generatePerformanceReport(staff);
                    break;
                
                case 14:
                    System.out.println("Logging out...");
                    break;
                
                default:
                    System.out.println("Invalid input! Please enter a number from 1 to 14.");
                    break;
            }
        } catch (Exception e){
            System.out.println("Invalid input! Please enter a number from 1 to 14.");
        }
    }
}
