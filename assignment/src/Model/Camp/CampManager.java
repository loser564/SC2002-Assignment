package Model.Camp;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.text.SimpleDateFormat;

/**
 * The {@code CampManager} class provides methods for managing camp data, including reading and writing camp information to a file.
 * This class allows you to read a list of camps from a file, write new camps to the file, and edit existing camp information in the file.
 * The camp data is stored in a structured format, and this class handles the conversion between camp objects and file records.
 * 
 * <p>The camp data is typically stored in a text file specified by the {@code campFile} constant.
 * Each line in the file represents a camp with various attributes such as name, dates, location, capacity, and more.
 * The methods in this class help in reading, writing, and editing these camp records.
 * 
 * @author Alicia
 * @version 5.0
 * @since 2023-11-19
 */
public class CampManager {
    /**
     * The file where camp data is stored. The data is typically stored in a structured format
     * where each line represents a camp with various attributes.
     */
    private static final File campFile = new File("Database\\Camp.txt");

    /**
     * Constructs a new {@code CampManager} instance.
     */
    public CampManager() {}

    /**
     * Reads and parses camp data from the file, returning a list of camp objects.
     *
     * @return An ArrayList of Camp objects containing the parsed camp data.
     */
    public static ArrayList<Camp> readCamps() {
        ArrayList<Camp> camps = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(campFile))) {
            String line = reader.readLine();

            while (line != null) {
                if (line.trim().isEmpty()) {
                    line = reader.readLine();
                    continue;
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                String[] tokens = line.split(",");
                String campName = tokens[0];
                Date startDate = dateFormat.parse(tokens[1]);
                Date endDate = dateFormat.parse(tokens[2]);
                Date registrationDeadline = dateFormat.parse(tokens[3]);
                String userGroup = tokens[4];
                String location = tokens[5];
                int maxCapacity = Integer.parseInt(tokens[6]);
                int campCommSlots = Integer.parseInt(tokens[7]);
                String campDescription = tokens[8];
                String staffID = tokens[9];
                line = reader.readLine();

                Camp camp = new Camp(campName, startDate, endDate, registrationDeadline, userGroup, location, maxCapacity, campCommSlots, campDescription, staffID);
                camps.add(camp);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return camps;
    }

    /**
     * Writes a new camp to the file.
     *
     * @param camp The camp object to be written to the file.
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public static void writeNewCamps(Camp camp) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(campFile, true));
        String line = camp.getCampName() + "," + camp.getStartDate() + "," + camp.getEndDate() + "," + camp.getRegistrationDeadline() + "," + camp.getUserGroup() + "," + camp.getLocation() + "," + camp.getMaxCapacity() + "," + camp.getCampCommSlots() + "," + camp.getCampDescription() + "," + camp.getStaffID();
        writer.newLine();
        writer.write(line);
        writer.close();
    }

    

    /**
     * Edits an existing camp in the file.
     *
     * @param camp The modified camp object to be saved in place of the original camp data.
     * @throws IOException If an I/O error occurs while editing the file.
     */
    public static void editCamps(Camp camp) throws IOException {
        String newLine = camp.getCampName() + "," + camp.getStartDate() + "," + camp.getEndDate() + "," + camp.getRegistrationDeadline() + "," + camp.getUserGroup() + "," + camp.getLocation() + "," + camp.getMaxCapacity() + "," + camp.getCampCommSlots() + "," + camp.getCampDescription() + "," + camp.getStaffID();
        File tempFile = new File(campFile.getAbsolutePath() + ".tmp");
        BufferedReader reader = new BufferedReader(new FileReader(campFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while ((line = reader.readLine()) != null) {
            if (line.equals(camp.toString())) {
                writer.write(newLine);
                writer.newLine();
            } else {
                writer.write(line);
                writer.newLine();
            }
        }

        reader.close();
        writer.close();

        if (!campFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }

        if (!tempFile.renameTo(campFile)) {
            System.out.println("Could not rename file");
            return;
        }
    }
/**
 * The {@code Camp} class represents a camp and its attributes.
 * @param camp The camp object to be deleted from the file.
 * @throws IOException If an I/O error occurs while deleting the file.
 * 
 */
public static void deleteCamp(Camp camp) throws IOException {
    ArrayList<Camp> camps = readCamps();
    Map<String, Set<String>> campDelete   = new HashMap<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(campFile))) {
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                String[] tokens = line.split(",");
                Set<String> campsSet = new HashSet<>(Arrays.asList(tokens).subList(1, tokens.length));
                campDelete.put(tokens[0], campsSet);
            }
        }
    }

    // Remove camp if present
    if (campDelete.containsKey(camp.getCampName())) {
        campDelete.remove(camp.getCampName()); // This removes the entire entry for the camp
    }

    // Write updated data back to file
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(campFile, false))) {
        for (Map.Entry<String, Set<String>> entry : campDelete.entrySet()) {
            String campEntry = entry.getKey() + "," + String.join(",", entry.getValue());
            writer.write(campEntry);
            writer.newLine();
        }
    }

}
    
}
