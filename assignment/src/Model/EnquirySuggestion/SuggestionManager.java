package Model.EnquirySuggestion;

import java.io.*;
import java.util.ArrayList;


public class SuggestionManager {
    private static final File suggestionFile = new File("assignment/src/Database/Suggestion.txt");

    public SuggestionManager(){}

    public static ArrayList<Suggestion> readSuggestions() {
        //System.out.println("Debug: readSuggestions entered");
        ArrayList<Suggestion> suggestions = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(suggestionFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
    
                String[] tokens = line.split(",");
                if (tokens.length < 4) {
                    System.out.println("Skipping line due to insufficient data: " + line);
                    continue;
                }
                try {
                    int suggestionID = Integer.parseInt(tokens[0].trim());
                    String studentID = tokens[1].trim();
                    String suggestionText = tokens[2].trim();
                    boolean status = Boolean.parseBoolean(tokens[3].trim());
    
                    Suggestion suggestion = new Suggestion(studentID, suggestionText, status);
                    suggestion.setSuggestionID(suggestionID);
    
                    suggestions.add(suggestion);
                    //System.out.println("Loaded suggestion: " + suggestion); // Adjusted for clarity
                    
                    //System.out.println("Debug: Suggestion ID - " + suggestionID + ", Student ID - " + studentID + ", Text - " + suggestionText + ", Status - " + status);
                } catch (NumberFormatException e) {
                    System.out.println("Error parsing suggestion ID on line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
        return suggestions;
    }
    

    public static void writeSuggestion(Suggestion suggestion) throws IOException {
        ArrayList<Suggestion> suggestions = readSuggestions();
        int maxSuggestionID = 0;
        for(Suggestion existingSuggestion: suggestions){
            maxSuggestionID = Math.max(maxSuggestionID, existingSuggestion.getSuggestionID());
        }
        int suggestionID = maxSuggestionID + 1;
        System.out.println("Debug studentID: " + suggestion.getStudentID());
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(suggestionFile, true))){
            String line = suggestionID + "," + suggestion.getStudentID() + "," + suggestion.getSuggestionText() + "," + suggestion.getStatus();
            writer.newLine();
            writer.write(line);
            writer.flush();
        } catch(IOException e){
            e.printStackTrace();
        }
        
       

    }

    public static void editSuggestion(Suggestion suggestion) throws IOException{
        int suggestionID = suggestion.getSuggestionID();
        String newLine = suggestionID + "," + suggestion.getStudentID()+ "," + suggestion.getSuggestionText() + "," + suggestion.getStatus();

        File tempFile = new File(suggestionFile.getAbsolutePath() + ".tmp");
        BufferedReader reader = new BufferedReader(new FileReader(suggestionFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while((line = reader.readLine()) != null){
            if(line.split(",")[0].equals(Integer.toString(suggestion.getSuggestionID()))){
                writer.write(newLine);
                writer.newLine();
            } else {
                writer.write(line);
                writer.newLine();
            }
        }

        reader.close();
        writer.close();

        if(!suggestionFile.delete()){
            System.out.println("Could not delete file");
            return;
        }

        if(!tempFile.renameTo(suggestionFile)){
            System.out.println("Could not rename file");
        }


    }

    public static void deleteSuggestion(Suggestion suggestion) throws IOException{
        int suggestionID = suggestion.getSuggestionID();
        File tempFile = new File(suggestionFile.getAbsolutePath() + ".tmp");

        try(BufferedReader reader = new BufferedReader(new FileReader(suggestionFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))){
            String line;
            while((line = reader.readLine()) != null){
                int currentSuggestionID = Integer.parseInt(line.split(",")[0]);
                if (currentSuggestionID == suggestionID){
                    continue; // skip the line to delete
                }
                writer.write(line);
                writer.newLine();
            }
        } 
        if (!suggestionFile.delete()){
            System.out.println("Could not delete file");
            return;
        }

        if(!tempFile.renameTo(suggestionFile)){
            System.out.println("Could not rename file");
        }
    }

    public static void printAllSuggestions(Suggestion suggestion){
        ArrayList<Suggestion> suggestions = readSuggestions();
        for(Suggestion s: suggestions){
            System.out.println(s.toString());
        }
    }

    public static void printMySuggestions(Suggestion suggestion){
        ArrayList<Suggestion> suggestions = readSuggestions();
        for(Suggestion s: suggestions){
            if(s.getStudentID().equals(suggestion.getStudentID())){
                System.out.println(s.toString());
            }
        }
    }

   
}
