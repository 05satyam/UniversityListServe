package composite.colleges;

import component.UniversityInterface;
import composite.departments.DepartmentImpl;
import data_objects_DAO.College;
import data_objects_DAO.Department;
import data_objects_DAO.University;

import java.util.ArrayList;
import java.util.List;

public class CollegeImpl extends College implements UniversityInterface {

    public CollegeImpl(String collegeName, List<DepartmentImpl> departmentList, University university) {
        super(collegeName, departmentList, university);
    }

    public void addDepartment(DepartmentImpl dpt){
            this.getDepartmentList().add(dpt);
    }

    public void removeDepartment(){

    }

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

    @Override
    public void emergencyNotification(String msg, String senderName) {
        for(DepartmentImpl dept : this.getDepartmentList()){
            dept.emergencyNotification(msg, senderName);
        }
    }
}
