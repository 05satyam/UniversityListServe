package component;

import composite.colleges.CollegeImpl;
import data_objects_DAO.University;

import java.util.ArrayList;
import java.util.List;

public class UniversityInterfaceImpl extends University implements UniversityInterface {
    private static UniversityInterfaceImpl universitySingleInstance = null;

    /**
     * implementing singleton pattern here as there can be one university for all processing hence one object
     * @return
     */
    public static UniversityInterfaceImpl getInstance()
    {
        if (universitySingleInstance == null)
            universitySingleInstance = new UniversityInterfaceImpl();

        return universitySingleInstance;
    }


    /**
     *
     * @param msg
     * @param senderName
     * @param notificationLevel
     *
     * send notification to all colleges or any one of the college
     */
    @Override
    public void notifyObserver(String msg, String senderName, List<String> notificationLevel) {
        for (String collegeName : notificationLevel) {
            for (CollegeImpl college : this.getCollegeList()) {
                if (college.getCollegeName().equalsIgnoreCase(collegeName) || collegeName.equalsIgnoreCase("00"))
                    college.notifyObserver(msg, senderName, notificationLevel);
            }
        }
    }

    /**
     *
     * @param msg
     * @param senderName
     *
     * send emergency alerts
     */
    @Override
    public void emergencyNotification(String msg, String senderName) {
        for (CollegeImpl college : this.getCollegeList()) {
            college.emergencyNotification(msg, senderName);
        }
    }
}
