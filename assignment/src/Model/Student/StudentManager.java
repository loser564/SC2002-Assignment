package Model.Student;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import Model.Camp.Camp;

/**
* Manages student-related operations, including reading/writing student files and handling registrations.
* @author SCEX Group 3
*/

public class StudentManager {
    // dictionary of student and blackListStatus
    static Map<String, Boolean> blackList = new HashMap<>();
    // assignment\src\Database\RegisteredStudents.txt
    private static final File studentFile = new File("Database\\RegisteredStudents.txt");
    
    /**
    * Reads the student file to check if a student with the specified ID is registered for a given camp.
    *
    * @param campName  The name of the camp.
    * @param studentID The ID of the student.
    * @return True if the student is registered for the camp, false otherwise.
    * @throws IOException If an I/O error occurs during file reading.
    */
    public static boolean readStudentFile( String CampName, String studentID ) throws IOException {
        try(BufferedReader reader = new BufferedReader(new FileReader(studentFile))){
            String line = reader.readLine();
            while (line != null){
                if(line.trim().isEmpty()){
                    line = reader.readLine();
                    continue;
                }
                String[] tokens = line.split(",");
                if (CampName.equals(tokens[0])) {
   
                    for(int i = 1; i < tokens.length; i++){
              
                        if(tokens[i].equals(studentID)){
                          
                            return true;
                        }
                    }  
                }
                line = reader.readLine();
                
            }
        } catch(Exception e){
            e.printStackTrace(System.out);
        }
        return false;
        
    }
    
     /**
    * Writes a new student registration entry for a given camp and student ID to the student file.
    *
    * @param camps      The list of camps.
    * @param campName   The name of the camp.
    * @param studentID  The ID of the student to be registered.
    * @return True if the registration is successful, false otherwise.
    * @throws IOException If an I/O error occurs during file writing.
    */
    public static boolean writeStudentFile(ArrayList<Camp> camps, String campName, String studentID) throws IOException {
        System.out.println("Student ID: " + studentID);
    Map<String, Set<String>> campStudents = new HashMap<>();

    // Read existing data
    try (BufferedReader reader = new BufferedReader(new FileReader(studentFile))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                String[] tokens = line.split(",");
                Set<String> students = new HashSet<>(Arrays.asList(tokens).subList(1, tokens.length));
                campStudents.put(campName, students);
            }
        }
    }

    if (blackList.containsKey(studentID) && blackList.get(studentID)) {
        System.out.println("Student is blacklisted");
        return false; // Student is blacklisted
    }

    // Add new student ID if not already present
    if (campStudents.containsKey(campName) && campStudents.get(campName).contains(studentID)) {
        System.out.println("Student already registered");
        return false; // Student already registered
    }
    // check if campName is a key already
    // if yes, add studentID to the set
    campStudents.computeIfAbsent(campName, k -> new HashSet<>()).add(studentID);

    // Write updated data back to file
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(studentFile, false))) {
        for (Map.Entry<String, Set<String>> entry : campStudents.entrySet()) {
            String campEntry = entry.getKey() + "," + String.join(",", entry.getValue());
            writer.write(campEntry);
            writer.newLine();
        }
    }
    blackList.put(studentID, false); // false means not blacklisted
    return true;
    }

    /**
    * Removes a student registration for a given camp and student ID from the student file.
    *
    * @param camps     The list of camps.
    * @param campName  The name of the camp.
    * @param studentID The ID of the student to be removed.
    * @throws IOException If an I/O error occurs during file writing.
    */
    public static void removeStudent (ArrayList<Camp> camps, String campName, String studentID) throws IOException {
        Map<String, Set<String>> campStudents = new HashMap<>();

        // Read existing data
        try (BufferedReader reader = new BufferedReader(new FileReader(studentFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    String[] tokens = line.split(",");
                    Set<String> students = new HashSet<>(Arrays.asList(tokens).subList(1, tokens.length));
                    campStudents.put(tokens[0], students);
                }
            }
        }

        // Remove student ID if present
        if (campStudents.containsKey(campName) && campStudents.get(campName).contains(studentID)) {
            campStudents.get(campName).remove(studentID);
        }

        // Write updated data back to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(studentFile, false))) {
            for (Map.Entry<String, Set<String>> entry : campStudents.entrySet()) {
                String campEntry = entry.getKey() + "," + String.join(",", entry.getValue());
                writer.write(campEntry);
                writer.newLine();
            }
        }
        blackList.put(studentID, true); // true means blacklisted
    }

   


    

}
