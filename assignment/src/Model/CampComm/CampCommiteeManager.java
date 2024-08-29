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

/**
 * The {@code CampCommiteeManager} class provides methods for managing camp committee members.
 * It allows reading and writing data related to camp committee members in a file.
 * 
 * @author Alicia
 * @version 3.0
 * @since 2023-11-19
 */
public class CampCommiteeManager {
    private static final File CampCommFile = new File("src/Database/CampCommitee.txt");

    /**
     * Checks if a given student ID is registered for a specific camp.
     *
     * @param campName The name of the camp.
     * @param studentID The student ID to check.
     * @return {@code true} if the student ID is registered for the camp, {@code false} otherwise.
     */
    public static boolean readCampCommFile(String campName, String studentID) {
        try (BufferedReader reader = new BufferedReader(new FileReader(CampCommFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue; // Skip empty lines and read the next one
                }
                String[] tokens = line.split(",");
                if (tokens[0].equals(campName)) {
                    for (int i = 1; i < tokens.length; i++) {
                        if (tokens[i].equals(studentID)) {
                            return true; // Found the student ID for the given camp name
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false; // Student ID not found for any line with the given camp name
    }

    /**
     * Writes camp committee member data to the file.
     *
     * @param camps The list of camps.
     * @param campName The name of the camp.
     * @param studentID The student ID to be added as a committee member.
     * @return {@code true} if the student ID is successfully added as a committee member, {@code false} if the student is already registered.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
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

    /**
     * Finds the camp name associated with a committee member's user ID.
     *
     * @param userID The user ID of the committee member.
     * @return The camp name if the user ID is associated with a camp committee, or {@code null} if not found.
     */
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
