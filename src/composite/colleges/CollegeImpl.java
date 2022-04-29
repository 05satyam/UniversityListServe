package composite.colleges;

import component.UniversityInterface;
import composite.departments.DepartmentImpl;
import data_objects_DAO.College;
import data_objects_DAO.Department;
import data_objects_DAO.University;

import java.util.ArrayList;
import java.util.List;

/**
 * main college class working as composite in composite design pattern
 *
 * extending college dao and implementing University interface
 */
public class CollegeImpl extends College implements UniversityInterface {

    /**
     *
     * @param collegeName
     * @param departmentList
     * @param university
     *
     * parametrized constructor
     */
    public CollegeImpl(String collegeName, List<DepartmentImpl> departmentList, University university) {
        super(collegeName, departmentList, university);
    }

    /**
     *
     * @param dpt
     * add new department to a college
     */
    public void addDepartment(DepartmentImpl dpt){
            this.getDepartmentList().add(dpt);
    }

    public void removeDepartment(){

    }

    /**
     *
     * @param msg
     * @param senderName
     * @param notificationLevel
     *
     * notify departments
     *
     * Scenario covered:
     *   1. Send announcment to all departments
     *   2. Send announcment to some departments
     */
    @Override
    public void notifyObserver(String msg, String senderName, List<String> notificationLevel) {
        for (String collegeName : notificationLevel) {

            for (DepartmentImpl dept : this.getDepartmentList()) {
                if(dept.getCollegeName().equalsIgnoreCase(collegeName) || collegeName.equalsIgnoreCase("00")){
                    System.out.println("Sending notification to student and faculties of department :" + dept.getDepartmentName().toUpperCase());
                    dept.notifyObserver(msg, senderName, notificationLevel);
                }
            }
        }
    }


    /**
     *
     * @param msg
     * @param senderName
     *
     * send emergency notification
     */
    @Override
    public void emergencyNotification(String msg, String senderName) {
        for(DepartmentImpl dept : this.getDepartmentList()){
            dept.emergencyNotification(msg, senderName);
        }
    }
}
