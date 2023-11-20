package Model;


import java.util.ArrayList;


public class UserLogin {
    private String userType;

    public UserLogin(){}

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

    public String getUserType() { return userType; }
}
