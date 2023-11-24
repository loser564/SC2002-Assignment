package Model.EnquirySuggestion;

import java.io.*;
import java.util.ArrayList;


public class SuggestionManager {
    private static final File suggestionFile = new File("src/Database/Suggestion.txt");

    public SuggestionManager(){}

    public static ArrayList<Suggestion> readSuggestions(){
        ArrayList<Suggestion> suggestions = new ArrayList<Suggestion>();
        try(BufferedReader reader = new BufferedReader(new FileReader (suggestionFile))){
            String line = reader.readLine();
            while(line != null){
                String[] tokens = line.split(",");
                int suggestionID = Integer.parseInt(tokens[0]);
                String studentID = tokens[1];
                String suggestionText = tokens[2];
                boolean status = Boolean.parseBoolean(tokens[3]);

                Suggestion suggestion1 = new Suggestion(studentID, suggestionText, status);

                suggestion1.setSuggestionID(suggestionID);

                suggestions.add(suggestion1);
                line = reader.readLine();
            }
        } catch(Exception e){
            e.printStackTrace(System.out);
        }
        return suggestions;
    }

    public static void writeSuggestion(Suggestion suggestion) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(suggestionFile, true));
        ArrayList<Suggestion> suggestions = readSuggestions();
        int suggestionID = suggestions.size() + 1;
        String line = suggestionID + "," + suggestion;
        writer.newLine();
        writer.write(line);
        writer.close();

    }

    public static void editSuggestion(Suggestion suggestion) throws IOException{
        int suggestionID = suggestion.getSuggestionID();
        String newLine = suggestionID + "," + suggestion.getSuggestionText() + "," + suggestion.getStatus();

        File tempFile = new File(suggestionFile.getAbsolutePath() + ".tmp");
        BufferedReader reader = new BufferedReader(new FileReader(suggestionFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

        String line;
        while((line = reader.readLine()) != null){
            if(line.equals(suggestion.toString())){
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
}
