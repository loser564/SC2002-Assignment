package Model.Student;

import java.io.IOException;
import java.util.ArrayList;
import Model.Camp.Camp;


public interface CampInterface {
    ArrayList<Camp> viewCamps() throws IOException;
    void registerCamp(Camp camp, String studentID) throws IOException;
    ArrayList<Camp> viewRegisteredCamps() throws IOException;
    void printCampDetails(Camp camp);
    void quitCamp(Camp camp) throws IOException;
}
   

