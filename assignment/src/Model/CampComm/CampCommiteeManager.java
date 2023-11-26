package Model.CampComm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import Model.Camp.Camp;

public class CampCommiteeManager {
    private static final File CampCommFile= new File("assignment/src/Database/CampCommitee.txt");
    
    public static boolean readCampCommFile(String CampName, String studentID)  {
        try (BufferedReader reader = new BufferedReader(new FileReader(CampCommFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines and read the next one
                }
                String[] tokens = line.split(",");
                if (tokens[0].equals(CampName)) { 
                    for (int i = 1; i < tokens.length; i++) {
                        if (tokens[i].equals(studentID)) { 
                            return true; // Found the student ID for the given camp name
                        }
                    }
                  // At this point, the student ID was not found for this camp name on this line
                // Loop will continue to the next line automatically
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false; // Student ID not found for any line with the given camp name
    }
    
    
    public static boolean writeCampCommitee(ArrayList<Camp> camps, String campName, String studentID) throws IOException {
    Map<String, Set<String>> campStudents = new HashMap<>();

    // Read existing data
    try (BufferedReader reader = new BufferedReader(new FileReader(CampCommFile))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                String[] tokens = line.split(",");
                Set<String> students = new HashSet<>(Arrays.asList(tokens).subList(1, tokens.length));
                campStudents.put(tokens[0], students);
            }
        }
    }

    // Add new student ID if not already present
    if (campStudents.containsKey(campName) && campStudents.get(campName).contains(studentID)) {
        return false; // Student already registered
    }
    campStudents.computeIfAbsent(campName, k -> new HashSet<>()).add(studentID);

    // Write updated data back to file
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(CampCommFile, false))) {
        for (Map.Entry<String, Set<String>> entry : campStudents.entrySet()) {
            String campEntry = entry.getKey() + "," + String.join(",", entry.getValue());
            writer.write(campEntry);
            writer.newLine();
        }
    }
    return true;
}


    public static String findCampForCommitteeMember(String userID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CampCommFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] tokens = line.split(",");
                if (tokens.length > 1) {
                    String campName = tokens[0].trim();
                    for (int i = 1; i < tokens.length; i++) {
                        if (tokens[i].trim().equals(userID)) {
                            return campName; // User ID found; return the camp name
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }
        return null; // Return null or an appropriate value if the user ID is not found
    }

}
