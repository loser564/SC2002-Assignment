package Model.EnquirySuggestion;
import java.util.ArrayList;
import java.io.*;


public class EnquiryManager {
    private static final File enquiryFile = new File("src/Database/Enquiry.txt");

    public EnquiryManager(){}

    public static ArrayList<Enquiry> readEnquiries(){
        ArrayList<Enquiry> enquiries = new ArrayList<Enquiry>();
        try(BufferedReader reader = new  BufferedReader(new FileReader (enquiryFile))){

            String line = reader.readLine();
            while(line != null){
                String[] tokens = line.split(",");
                int enquiryID = Integer.parseInt(tokens[0]);
                String studentID = tokens[1];
                String enquiryTitle = tokens[2];
                String enquiryMessage = tokens[3];
                boolean status = Boolean.parseBoolean(tokens[4]);
                String reply = tokens[5];


                Enquiry enquiry1 = new Enquiry( studentID, enquiryTitle, enquiryMessage, status);

                                                                                                                                                                                                                                                            
                enquiry1.setEnquiryID(enquiryID);
                enquiry1.setReply(reply);

                

                enquiries.add(enquiry1);
                line = reader.readLine();

            }

        } catch(Exception e){
            e.printStackTrace(System.out);
        }
        return enquiries;
    }

    public static void writeEnquiries(Enquiry enquiry) throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(enquiryFile, true));
        ArrayList<Enquiry> enquiries = readEnquiries();
        int enquiryID = enquiries.size() + 1;
        String line = enquiryID + "," + enquiry.getStudentID() + ","  
        + enquiry.getTitle() + "," + enquiry.getMessage() 
        + "," + enquiry.getStatus() + "," + enquiry.getReply();
        writer.newLine();
        writer.write(line);
        writer.close();
    }

    public static void editEnquiry(Enquiry enquiry) throws IOException{
        int enquiryID = enquiry.getEnquiryID();
        String newLine = enquiryID + "," + enquiry.getStudentID() + ","  
        + enquiry.getTitle() + "," + enquiry.getMessage() 
        + "," + enquiry.getStatus() + "," + enquiry.getReply();

        File tempFile = new File(enquiryFile.getAbsolutePath() + ".tmp");
        BufferedReader reader = new BufferedReader(new FileReader(enquiryFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while   ((line = reader.readLine()) != null){
            if(line.equals(enquiry.toString())){
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

    public static void deleteEnquiry(Enquiry enquiry) throws IOException{
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

    public static void printAllEnquiries(Enquiry enquiry){
        ArrayList<Enquiry> enquiries = readEnquiries();
        for(Enquiry e: enquiries){
            System.out.println(e.toString());
        }
    }
}

