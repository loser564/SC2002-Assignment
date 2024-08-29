package Model.User;
import java.io.*;
import java.util.ArrayList;

import Model.Staff.Staff;
import Model.Student.Student;

/**
* The UserManager class manages user information, including reading and writing to files.
* @author Ryan
* @version 1.0
* @since 2023-11-3
*/
public class UserManager {
    // assignment\src\Database\Student.txt
    // Database\Student.txt
    private static final File studentFile = new File("Database\\Student.txt");
    private static final File staffFile = new File("Database\\Staff.txt");

    /**
    * Default constructor for UserManager.
    */
    public UserManager(){}

    /**
     * Reads student information from the file.
     *
     * @return ArrayList of Student objects.
     */
    public static ArrayList<Student> readStudents() {
        ArrayList<Student> students = new ArrayList<Student>();
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFile))) {
    
            String line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split("\\s+");
                if (tokens.length >= 4){
                    String name = tokens[0];
                    String email = tokens[1];
                    String userID = email.split("@")[0];
                    // System.out.println(userID);
                    String faculty = tokens[2];
                    String password = tokens[3];

                    if(userID != null){
                        Student student = new Student(name, userID, password, faculty, UserRole.STUDENT);
                        students.add(student);
                    } else {
                        System.out.println("Invalid userID: " + line);
                    }
        
                    
                }else{
                    System.out.println("Invalid line: " + line);
                }

    
                // Read the next line
                line = reader.readLine();
            }
    
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return students;
    }
    

    
 /**
    * Writes a new password for a student to the file.
    *
    * @param userID      The user ID of the student.
    * @param newPassword The new password to set.
    * @throws IOException If an I/O error occurs.
    */
    public static void writeStudentsPassword(String newPassword, String userID) throws IOException {
        //File studentFile = readFile("Student.txt");
        ArrayList<Student> students = readStudents();
        Student student = new Student();
        for (Student s : students) {
            if (s.getStudentID().equals(userID)) {
                student = s;
                break;
            }
        }
        // System.out.println("Debug: newPassword = " + newPassword);
        String newLine = student.getName() + " " + student.getStudentID() + " " + student.getFaculty() + " " + newPassword;
    
        File tempFile = new File(studentFile.getAbsolutePath() + ".tmp");
        BufferedReader reader = new BufferedReader(new FileReader(studentFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        // Read each line and modify the line corresponding to the student ID
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith(String.valueOf(student.getName()))) {
                writer.write(newLine);
            } else {
                writer.write(line);
            }
            writer.newLine();
        }

        reader.close();
        writer.close();

        // Replace project file with temporary file
        if (!studentFile.delete()) {
            System.out.println("Failed to delete original file");
            return;
        }

        if (!tempFile.renameTo(studentFile)) {
            System.out.println("Failed to rename temporary file");
            return;
        }

    }

    
    
    /**
    * Reads staff information from the file.
    *
    * @return ArrayList of Staff objects.
    */
    public static ArrayList<Staff> readStaff(){
        ArrayList<Staff> staffs = new ArrayList<Staff>();
        try(BufferedReader reader = new BufferedReader(new FileReader(staffFile))){
            String line;

            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(",");
                if(tokens.length >= 4){
                    String name = tokens[0];
                    String email = tokens[1];
                    String userID = tokens[1].split("@")[0];
                    String faculty = tokens[2];
                    String password = tokens[3];
                    // s
                if (userID != null){

                    Staff staff = new Staff(name,userID, password, faculty, UserRole.STAFF);
                    staffs.add(staff);
                } else{
                    System.out.println("Invalid userID: " + line);
                }
                }else{
                System.out.println("Invalid line: " + line);
            } 
            }
        } catch(Exception e){
            e.printStackTrace(System.out);
        }
        return staffs;
    }

   /**
    * Writes a new password for a staff member to the file.
    *
    * @param userID      The user ID of the staff member.
    * @param newPassword The new password to set.
    * @throws IOException If an I/O error occurs.
    */
    public static void writeStaffPassword(String newPassword, String userID) throws IOException{
        //File staffFile = readFile("Staff.txt");
        ArrayList<Staff> staffs = readStaff();
        Staff staff = new Staff();
        for(Staff s: staffs){
            System.out.println("Debug: s.getUserID() = " + s.getStaffID());
            if(s.getStaffID().equals(userID)){
                staff = s;
                break;
            }
        }

        System.out.println("Debug: newPassword = " + newPassword);
        String newLine = staff.getName() + " " + staff.getStaffID() + " " + staff.getFaculty() + " " + newPassword;
        File tempFile = new File(staffFile.getAbsolutePath() + ".tmp");
        BufferedReader reader = new BufferedReader(new FileReader(staffFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.startsWith(String.valueOf(staff.getName()))) {
                writer.write(newLine);
            } else {
                writer.write(line);
            }
            writer.newLine();
        }

        reader.close();
        writer.close();

        // Replace project file with temporary file
        if (!staffFile.delete()) {
            System.out.println("Failed to delete original file");
            return;
        }

        if (!tempFile.renameTo(staffFile)) {
            System.out.println("Failed to rename temporary file");
            return;
        }

        
    }

    /**
    * Reads camp committee information from the file.
    *
    * @return ArrayList of Student objects representing camp committee members.
    * @throws IOException If an I/O error occurs.
    */
    public static ArrayList<Student> readCampComm(){
        ArrayList<Student> campComm = new ArrayList<Student>();
        try(BufferedReader reader = new BufferedReader(new FileReader(staffFile))){
            String line;

            while ((line = reader.readLine()) != null){
                String[] tokens = line.split(";");
                String name = tokens[0];
                String email = tokens[1];
                String userID = tokens[1].split("@")[0];
                String faculty = tokens[2];
                String password = tokens[3];
                // UserRole role = UserRole.valueOf(tokens[4]);

                Student campCommMember = new Student(name, userID, password, faculty, UserRole.CAMP_COMMITTEE);
                campComm.add(campCommMember);
            }
        } catch(Exception e){
            e.printStackTrace(System.out);
        }
        return campComm;
    }

    

}
