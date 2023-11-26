package Model.Student;

import java.io.IOException;
import java.util.ArrayList;
import Model.Camp.Camp;


public interface CampCommInterface {
    int getRemainderCampCommSlots();
    void setCampCommittee(boolean isCampCommittee);
    boolean getCampCommittee();
    void applyCampCommittee(Camp camp) throws IOException;

}
