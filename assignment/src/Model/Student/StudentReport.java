package Model.Student;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import Model.Camp.Camp;

public class StudentReport {
    private List<Student> students;

    public StudentReport(List<Student> students) {
        this.students = students;
    }

    // public List<Student> filter(String faculty, boolean isCampComm, String campNameFilter) {
    //     return students.stream()
    //             .filter(student -> faculty == null || student.getFaculty().equals(faculty))
    //             .filter(student -> !isCampComm || student.getCampCommittee())
                
    //             .collect(Collectors.toList());
    // }
    

    public static void generateReport(ArrayList<Student> filteredStudents, boolean isCampComm, String faculty) throws IOException{
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("assignment/src/Database/student_report.txt"))){
            for (Student student : filteredStudents) {
                System.out.println("Processing student: " + student.getStudentID()); // Debugging line
                System.out.println("Student camp committee: " + student.getCampCommittee()); // Debugging line

                boolean facultyMatch = (faculty == null || "null".equalsIgnoreCase(faculty) || student.getFaculty().equals(faculty));
                System.out.println("FacultyMatch: " + facultyMatch); // Debugging line
                if (facultyMatch){
                    if (isCampComm == false && student.getCampCommittee() == false){
                        System.out.println("Student is not camp committee");
                        System.out.println("Writing student to report");
                        writer.write("Student ID: " + student.getStudentID() + "\n");
                        writer.write("Faculty: " + student.getFaculty() + "\n");
                        
                        writer.write("\n");
                    }
                    else if (isCampComm == true && student.getCampCommittee() == true){
                        System.out.println("Student is camp committee");
                        System.out.println("Writing student to report");
                        writer.write("Student ID: " + student.getStudentID() + "\n");
                        writer.write("Faculty: " + student.getFaculty() + "\n");
                        writer.write("Camp Committee: " + student.getCampCommittee() + "\n");
                        writer.write("\n");
                    }
                }
            }
            writer.flush(); // Make sure to flush the stream
        } catch(IOException e){
            System.out.println("Error writing report: " + e.getMessage());
            e.printStackTrace(); // This will print the stack trace to help with debugging
        }
    }
    

    

}
