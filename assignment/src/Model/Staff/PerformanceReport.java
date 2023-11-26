package Model.Staff;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;

import Model.CampComm.CampCommitee;


public class PerformanceReport{
    public PerformanceReport(){}

    public static void generateReport(ArrayList<CampCommitee> committeeMembers, String campName) throws IOException{
    try(BufferedWriter writer = new BufferedWriter(new FileWriter("assignment/src/Database/PerformanceReport.txt"))){
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