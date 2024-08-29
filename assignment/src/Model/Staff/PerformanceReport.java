package Model.Staff;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;

import Model.CampComm.CampCommitee;
/**
 * The {@code PerformanceReport} class provides functionality to generate a performance report
 * for camp committee members. It allows you to generate a report containing points information
 * for each committee member associated with a specific camp.
 * 
 * @author Alicia
 * @version 4.0
 * @since 2023-11-19
 */

public class PerformanceReport{
    /**
     * Constructs a new {@code PerformanceReport} instance.
     */
    public PerformanceReport(){}
    /**
     * Generates a performance report for camp committee members and writes it to a file.
     *
     * @param committeeMembers An {@code ArrayList} of {@link CampCommitee} objects representing
     *                         the committee members.
     * @param campName         The name of the camp for which the report is generated.
     * @throws IOException    If an I/O error occurs while writing the report to a file.
     */

    public static void generateReport(ArrayList<CampCommitee> committeeMembers, String campName) throws IOException{
    try(BufferedWriter writer = new BufferedWriter(new FileWriter("src/Database/PerformanceReport.txt"))){
        System.out.println("Camp Name: " + campName);
        System.out.println("Points Report:");
        writer.write("Camp Name: " + campName + "\n");
        writer.write("Points Report:" + "\n");
        
        for (CampCommitee committeeMember : committeeMembers) {

            System.out.println("Committee Member: " + committeeMember.getUserID());
            System.out.println("Points: " + committeeMember.getPoints());
            System.out.println();
            writer.write("Committee Member: " + committeeMember.getUserID() + "\n");
            writer.write("Points: " + committeeMember.getPoints() + "\n");
            writer.write("\n");
        }
    } catch(IOException e){
        System.out.println("Error writing report: " + e.getMessage());
        e.printStackTrace(); // This will print the stack trace to help with debugging
    }
}

    
}