package Model;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;

public class CampManager {
    // assignment\src\Database\Camp.txt
    private static final File campFile = new File("assignment/src/Database/Camp.txt");

    public CampManager(){}

    public static ArrayList<Camp> readCamps(){
        ArrayList<Camp> camps = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(campFile))){
            String line = reader.readLine();

            while (line != null){
                if(line.trim().isEmpty()){
                    continue;
                }
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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

            Camp camp = new Camp(campName, startDate, endDate, registrationDeadline, userGroup, location, maxCapacity, campCommSlots, campDescription, staffID);
            camps.add(camp);
        }
    } catch(Exception e){
        e.printStackTrace(System.out);
    }
    return camps;
   
    }

    public static void writeCamps(Camp camp)  throws IOException{
        BufferedWriter writer = new BufferedWriter(new FileWriter(campFile, true));
        String line = camp.getCampName() + "," + camp.getStartDate() + "," + camp.getEndDate() + "," + camp.getRegistrationDeadline() + "," + camp.getUserGroup() + "," + camp.getLocation() + "," + camp.getMaxCapacity() + "," + camp.getCampCommSlots() + "," + camp.getCampDescription() + "," + camp.getStaffID();
        writer.newLine();
        writer.write(line);        
        writer.close();
    }

    public static void editCamps(Camp camp) throws IOException{

        String newLine = camp.getCampName() + "," + camp.getStartDate() + "," + camp.getEndDate() + "," + camp.getRegistrationDeadline() + "," + camp.getUserGroup() + "," + camp.getLocation() + "," + camp.getMaxCapacity() + "," + camp.getCampCommSlots() + "," + camp.getCampDescription() + "," + camp.getStaffID();

        File tempFile = new File(campFile.getAbsolutePath() + ".tmp");
        BufferedReader reader = new BufferedReader(new FileReader(campFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while((line = reader.readLine()) != null){
            if(line.equals(camp.toString())){
                writer.write(newLine);
                writer.newLine();
            } else {
                writer.write(line);
                writer.newLine();
            }
        }



        reader.close();
        writer.close();

        if (!campFile.delete()){
            System.out.println("Could not delete file");
            return;
        }

        if(!tempFile.renameTo(campFile)){
            System.out.println("Could not rename file");
            return;
        }
        campFile.delete();
        tempFile.renameTo(campFile);
    }


}
