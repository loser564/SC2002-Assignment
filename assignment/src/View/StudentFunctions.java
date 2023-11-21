package View;
import Model.*;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;


public class StudentFunctions {
    public StudentFunctions(){}

    public boolean changePassword(Student student, String password) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Change Password:");
        System.out.println("Enter old password: ");
        String oldPassword = sc.nextLine();
        if(!oldPassword.equals(password)){
            System.out.println("Old password is incorrect!");
            
        }

        else{
            System.out.println("Enter new password: ");
            String newPassword = sc.nextLine();
            System.out.println("Confirm new password: ");
            String confirmNewPassword = sc.nextLine();
            System.out.println("Changing password ....");
            if(!newPassword.equals(confirmNewPassword)){
                System.out.println("New password does not match!");

            }
            else if(newPassword.equals(password)){
                System.out.println("New password cannot be the same as old password!");
            }
            else if(oldPassword.isEmpty() || newPassword.isEmpty()){
                System.out.println("Password cannot be empty!");
            }
            else{
                boolean pwChange = student.changePassword(newPassword);
                System.out.println("Password changed successfully!");
                return pwChange;
            }
        }
        
        return false;
    }
    

    public void viewCamps(Student student) throws IOException{
        ArrayList<Camp> camps = student.viewCamps();
        System.out.println("List of camps:");
        for(Camp c: camps){
            student.printCampDetails(c);
        }
    }

    public void registerForCamp(Student student) throws IOException{
        ArrayList<Camp> camps = student.viewCamps();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter camp name to register: ");
        String campName = sc.nextLine();
        Camp camp = null;
        for(Camp c: camps){
            if(c.getCampName().equals(campName)){
                camp = c;
                break;
            }
        }
        if(camp == null){
            System.out.println("Camp does not exist!");
        }
        else{
            student.registerCamp(camp);
            System.out.println("Camp registered successfully!");
        }

        sc.close();
    }

    public void quitCamp(Student student) throws IOException{
        ArrayList<Camp> camps = student.viewRegisteredCamps();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter camp name to quit: ");
        String campName = sc.nextLine();
        System.out.println("You will not be able to register for this camp again. Are you sure you want to quit? (Y/N): ");
        char choice = sc.nextLine().charAt(0);
        if (choice == 'Y'){
            Camp camp = null;
            for(Camp c: camps){
                if(c.getCampName().equals(campName)){
                    camp = c;
                    break;
                }
            }
            if(camp == null){
                System.out.println("Camp does not exist!");
                
            }
            else{
                student.quitCamp(camp);
                System.out.println("Camp quit successfully!");
                
            }
        }
        else{
            System.out.println("Camp not quit!");
            
        }
        sc.close();
       

    }

    public void applyCampCommittee(Student student) throws IOException{
        
        ArrayList<Camp> camps = student.viewCamps();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter camp name to apply for camp committee: ");
        String campName = sc.nextLine();
        Camp camp = null;
        for(Camp c: camps){
            if(c.getCampName().equals(campName)){
                camp = c;
                break;
            }
        }
        if(camp == null){
            System.out.println("Camp does not exist!");
        }
        else{
            student.applyCampCommittee(camp);
            System.out.println("Camp committee applied successfully!");
        }
        sc.close();
    }

    public void viewRegisteredCamps(Student student) throws IOException{
        ArrayList<Camp> camps = student.viewRegisteredCamps();
        System.out.println("List of registered camps:");
        for(Camp c: camps){
            student.printCampDetails(c);
        }
    }

    public void viewAllEnquiries(Student student) throws IOException{
        ArrayList<Enquiry> enquiries = student.viewAllEnquiries();
        System.out.println("List of enquiries:");
        for(Enquiry e: enquiries){
            student.printEnquiryDetails(e);
        }
    }

    public void viewOwnEnquiries(Student student) throws IOException{
        ArrayList<Enquiry> enquiries = student.viewAllEnquiries();
        System.out.println("List of own enquiries:");
        for(Enquiry e: enquiries){
            if(e.getStudentID().equals(student.getStudentID())){
                student.printEnquiryDetails(e);
            }
        }

    }

    public void submitEnquiry(Student student) throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter enquiry title: ");
        String enquiryTitle = sc.nextLine();
        System.out.println("Enter enquiry description: ");
        String enquiryDescription = sc.nextLine();
        // int studentID,String message, boolean status
        // String studentID = student.getStudentID();
        student.submitEnquiry(enquiryTitle,enquiryDescription);
        System.out.println("Enquiry submitted successfully!");
        sc.close();
    }


    public void editEnquiry(Student student) throws IOException{
        viewOwnEnquiries(student);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter enquiry ID to edit: ");
        int enquiryID = sc.nextInt();
        sc.nextLine();
        ArrayList<Enquiry> enquiries = student.viewAllEnquiries();
        Enquiry enquiry = null;
        for(Enquiry e: enquiries){
            if(e.getEnquiryID() == enquiryID){
                enquiry = e;
                break;
            }
        }
        if(enquiry == null){
            System.out.println("Enquiry does not exist!");
        }

        else if(enquiry.getStatus()){
            System.out.println("Enquiry has been replied and cannot be edited!");
        }
        else{
            System.out.println("Enter new enquiry title: ");
            String enquiryTitle = sc.nextLine();
            System.out.println("Enter new enquiry description: ");
            String enquiryDescription = sc.nextLine();
            enquiry.setTitle(enquiryTitle);
            enquiry.setMessage(enquiryDescription);
            student.editEnquiry(enquiry);
            System.out.println("Enquiry edited successfully!");
        }
        sc.close();
    }

    public void deleteEnquiry(Student student) throws IOException{
        viewOwnEnquiries(student);
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter enquiry ID to delete: ");
        int enquiryID = sc.nextInt();
        sc.nextLine();
        ArrayList<Enquiry> enquiries = student.viewAllEnquiries();
        Enquiry enquiry = null;
        for(Enquiry e: enquiries){
            if(e.getEnquiryID() == enquiryID){
                enquiry = e;
                break;
            }
        }
        if(enquiry == null){
            System.out.println("Enquiry does not exist!");
        }
        else if(enquiry.getStatus()){
            System.out.println("Enquiry has been replied and cannot be deleted!");
        }
        else{
            student.deleteEnquiry(enquiry);
            System.out.println("Enquiry deleted successfully!");
        }
        sc.close();

    }

   

    
}


