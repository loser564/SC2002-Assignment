package Model.Student;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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

    public List<Student> filter(String faculty, boolean isCampComm, Camp camp, String CampName) {
    return students.stream()
            .filter(student -> (faculty == null || student.getFaculty().equals(faculty)))
            .filter(student -> !isCampComm || student.getCampCommittee())
            .filter(student -> camp == null || student.getCamp(CampName).equals(camp))
            .collect(Collectors.toList());
    }

    public void generateReport(List<Student> filteredStudents) throws IOException{
        Collections.sort(filteredStudents, Comparator.comparing(Student::getStudentID));

        try(BufferedWriter writer = new BufferedWriter(new FileWriter("student_report.txt"))){
            for (Student student : filteredStudents) {
                writer.write("Student ID: " + student.getStudentID() + "\n");
                writer.write("Faculty: " + student.getFaculty() + "\n");
                writer.write("Camp Committee: " + student.getCampCommittee() + "\n");
                writer.write("\n");
            }
        }
    }

}
