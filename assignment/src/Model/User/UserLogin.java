package Model.User;


import java.util.ArrayList;

import Model.Staff.Staff;
import Model.Student.Student;


/**
* The UserLogin class handles user verification and login functionality.
* @author Ryan
* @version 1.0
* @since 2023-11-3
*/
public class UserLogin {
    private String userType;
    
    /**
    * Default constructor for UserLogin.
    */
    public UserLogin(){}

    /**
    * Verifies if a user with the given userID exists among students or staff.
    *
    * @param userID The user ID to verify.
    * @return True if the user is found, false otherwise.
    */
    public boolean verifyUser(String userID){
        ArrayList<Student> students = UserManager.readStudents();
        // System.out.println("Students: " + students);
        ArrayList<Staff> staffs = UserManager.readStaff();

        for(Student s: students){
            
            String studentID = s.getStudentID();
            System.out.println("Student ID: " + studentID);
            if(studentID != null && studentID.equals(userID)){
                System.out.println("User found in students: " + s.getName());
                return true;
            }
        }

        for(Staff s: staffs){
            String staffID = s.getStaffID();
            System.out.println("Staff ID: " + staffID);
            if(staffID != null && staffID.equals(userID)){
                System.out.println("User found in staff: " + s.getName());
                return true;
            }
        }
        return false;


    }

    /**
    * Verifies the login credentials (userID and password) and determines the user type.
    *
    * @param userID   The user ID for login.
    * @param password The password for login.
    * @return True if the login is successful, false otherwise.
    */
    public boolean verifyLogin(String userID, String password){
        ArrayList<Student> students = UserManager.readStudents();
        ArrayList<Staff> staffs = UserManager.readStaff();

        for(Student s: students){
            if(s.getStudentID().equals(userID) && s.getPassword().equals(password)){
                this.userType = "student";
                return true;
            }
        }

        for(Staff s: staffs){
            if(s.getStaffID().equals(userID) && s.getPassword().equals(password)){
                this.userType = "staff";
                return true;
            }
        }
        return false;
    }

    /**
    * Gets the type of the logged-in user (e.g., "student" or "staff").
    *
    * @return The user type.
    */
    public String getUserType() { return userType; }
}
