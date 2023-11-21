package Model;
import java.io.*;
import java.util.ArrayList;

public class UserManager {
    // assignment\src\Database\Student.txt
    private static final File studentFile = new File("assignment/src/Database/Student.txt");
    private static final File staffFile = new File("assignment/src/Database/Staff.txt");

    public UserManager(){}

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
    

    public static void writeStudentsPassword(String userID, String newPassword) throws IOException{
        ArrayList<Student> students = readStudents();
        Student student = new Student();
        for(Student s: students){
            if(s.getUserID().equals(userID)){
                student = s;
                break;
            }
        }

        String newLine = student.getStudentID() + "," + newPassword + "," + student.getFaculty() + "," + student.getUserRole();

        File tempFile = new File(studentFile.getAbsolutePath() + ".tmp");
        BufferedReader reader = new BufferedReader(new FileReader(studentFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while   ((line = reader.readLine()) != null){
            if(line.equals(student.toString())){
                writer.write(newLine);
                writer.newLine();
            } else {
                writer.write(line);
                writer.newLine();
            }
        }

        reader.close();
        writer.close();

        if(!studentFile.delete()){
            System.out.println("Could not delete file");
            return;
        }

        if(!tempFile.renameTo(studentFile)){
            System.out.println("Could not rename file");
            return;
        }

        
    }

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

    public static void writeStaffPassword(String userID, String newPassword) throws IOException{
        ArrayList<Staff> staffs = readStaff();
        Staff staff = new Staff();
        for(Staff s: staffs){
            if(s.getUserID().equals(userID)){
                staff = s;
                break;
            }
        }

        String newLine = staff.getStaffID() + "," + newPassword + "," + staff.getFaculty() + "," + staff.getUserRole();

        File tempFile = new File(staffFile.getAbsolutePath() + ".tmp");
        BufferedReader reader = new BufferedReader(new FileReader(staffFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while   ((line = reader.readLine()) != null){
            if(line.equals(staff.toString())){
                writer.write(newLine);
                writer.newLine();
            } else {
                writer.write(line);
                writer.newLine();
            }
        }

        reader.close();
        writer.close();

        if(!staffFile.delete()){
            System.out.println("Could not delete file");
            return;
        }

        if(!tempFile.renameTo(staffFile)){
            System.out.println("Could not rename file");
            return;
        }
    }

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
