package Model.EnquirySuggestion;

import java.io.*;
import java.util.ArrayList;

/**
 * The {@code EnquiryManager} class provides methods for reading, writing, editing, deleting,
 * and printing enquiries related to camps. It manages the storage and retrieval of enquiry
 * data from a file.
 * 
 * @author Shao Jie
 * @version 3.0
 * @since 2023-11-19
 */
public class EnquiryManager {
    private static final File enquiryFile = new File("src/Database/Enquiry.txt");

    /**
     * Constructs a new instance of {@code EnquiryManager}.
     */
    public EnquiryManager() {}

    /**
     * Reads and retrieves a list of enquiries from the enquiry file.
     *
     * @return An {@code ArrayList} of {@code Enquiry} objects representing the stored enquiries.
     */
    public static ArrayList<Enquiry> readEnquiries() {
        ArrayList<Enquiry> enquiries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(enquiryFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
    
                String[] tokens = line.split(",");
                if (tokens.length < 7) continue; // Ensure there are enough tokens
    
                try {
                    int enquiryID = Integer.parseInt(tokens[0].trim());
                    String studentID = tokens[1].trim();
                    String camp = tokens[2].trim();
                    String enquiryTitle = tokens[3].trim();
                    String enquiryMessage = tokens[4].trim();
                    boolean status = Boolean.parseBoolean(tokens[5].trim());
                    String reply = tokens[6].trim().equals("null") ? null : tokens[6].trim();
    
                    Enquiry enquiry = new Enquiry(studentID, camp, enquiryTitle, enquiryMessage, status);
                    enquiry.setEnquiryID(enquiryID);
                    enquiry.setReply(reply);
    
                    enquiries.add(enquiry);
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return enquiries;
    }
    
    /**
     * Writes a new enquiry to the enquiry file.
     *
     * @param enquiry The {@code Enquiry} object to be written.
     * @throws IOException If an error occurs while writing to the file.
     */
    public static void writeEnquiries(Enquiry enquiry) throws IOException {
        ArrayList<Enquiry> enquiries = readEnquiries();
        int maxEnquiryID = 0;
        for (Enquiry existingEnquiry : enquiries) {
            maxEnquiryID = Math.max(maxEnquiryID, existingEnquiry.getEnquiryID());
        }
        int enquiryID = maxEnquiryID + 1;
    
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(enquiryFile, true))) {
            String line = enquiryID + "," + enquiry.getStudentID() + "," + enquiry.getCampName() + "," 
                        + enquiry.getTitle() + "," + enquiry.getMessage() 
                        + "," + enquiry.getStatus() + "," + (enquiry.getReply() == null ? "null" : enquiry.getReply());
            writer.newLine();
            writer.write(line);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edits an existing enquiry in the enquiry file.
     *
     * @param enquiry The {@code Enquiry} object representing the edited enquiry.
     * @throws IOException If an error occurs while editing the file.
     */
    public static void editEnquiry(Enquiry enquiry) throws IOException {
        int enquiryID = enquiry.getEnquiryID();
        String newLine = enquiryID + "," + enquiry.getStudentID() + "," +enquiry.getCampName() + ","  
        + enquiry.getTitle() + "," + enquiry.getMessage() 
        + "," + enquiry.getStatus() + "," + enquiry.getReply();

        File tempFile = new File(enquiryFile.getAbsolutePath() + ".tmp");
        BufferedReader reader = new BufferedReader(new FileReader(enquiryFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while   ((line = reader.readLine()) != null){
            if(line.split(",")[0].equals(Integer.toString(enquiry.getEnquiryID()))){
                writer.write(newLine);
                writer.newLine();
                
            } else {
                writer.write(line);
                writer.newLine();
            }
        }


        reader.close();
        writer.close();

        if(!enquiryFile.delete()){
            System.out.println("Could not delete file");
            return;
        }

        if(!tempFile.renameTo(enquiryFile)){
            System.out.println("Could not rename file");
            return;
        }

    }

    /**
     * Deletes an existing enquiry from the enquiry file.
     *
     * @param enquiry The {@code Enquiry} object representing the enquiry to be deleted.
     * @throws IOException If an error occurs while deleting the enquiry.
     */
    public static void deleteEnquiry(Enquiry enquiry) throws IOException {
        int enquiryID = enquiry.getEnquiryID();
        File tempFile = new File(enquiryFile.getAbsolutePath() + ".tmp");

        try(BufferedReader reader = new BufferedReader(new FileReader(enquiryFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))){

            String line;
            while((line = reader.readLine()) != null){
                int currentEnquiryID = Integer.parseInt(line.split(",")[0]);
                if (currentEnquiryID == enquiryID){
                    continue;  // skip line to delete
                }

                writer.write(line);
                writer.newLine();


            }
        }
        if (!enquiryFile.delete()){
            System.out.println("Could not delete file");
            return;
        }

        if(!tempFile.renameTo(enquiryFile)){
            System.out.println("Could not rename file");
            return;
        }
        
    }

    /**
     * Prints details of all enquiries to the console.
     *
     * @param enquiry An {@code Enquiry} object (used for class reference).
     */
    public static void printAllEnquiries(Enquiry enquiry) {
        ArrayList<Enquiry> enquiries = readEnquiries();
        for(Enquiry e: enquiries){
            System.out.println(e.toString());
        }
    }
}
