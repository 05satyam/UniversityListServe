package component;

import composite.colleges.CollegeImpl;

import java.util.ArrayList;
import java.util.List;

public class UniversityInterfaceImpl implements UniversityInterface {
    List<CollegeImpl> collegeList = new ArrayList<>();

    @Override
    public void notifyObserver(String msg, String senderName, List<String> notificationLevel) {
        for (String collegeName : notificationLevel) {
            for (CollegeImpl college : collegeList) {
                if (college.getCollegeName().equalsIgnoreCase(collegeName) || collegeName.equalsIgnoreCase("0"))
                    college.notifyObserver(msg, senderName, notificationLevel);
            }
        }
    }

    @Override
    public void emergencyNotification(String msg, String senderName) {
        for (CollegeImpl college : collegeList) {
            college.notifyObserver(msg, senderName, null);
        }
    }
}
