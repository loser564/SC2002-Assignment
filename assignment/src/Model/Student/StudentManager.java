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

public class StudentManager {
    // dictionary of student and blackListStatus
    static Map<String, Boolean> blackList = new HashMap<>();
    // assignment\src\Database\RegisteredStudents.txt
    private static final File studentFile = new File("assignment/src/Database/RegisteredStudents.txt");
    
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
