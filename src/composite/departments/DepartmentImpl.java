package composite.departments;

import component.UniversityInterface;
import data_objects_DAO.Department;
import data_objects_DAO.Faculty;
import leaf.FacultyLeafClass;
import leaf.StudentLeafClass;

import java.util.ArrayList;
import java.util.List;

public class DepartmentImpl extends Department implements UniversityInterface {


    public DepartmentImpl() {
    }

    public DepartmentImpl(String departmentName, String collegeName, List<FacultyLeafClass> facultyList, List<StudentLeafClass> studentList) {
        super(departmentName, collegeName, facultyList, studentList);
    }

    public void addStudent(StudentLeafClass st) {
        this.getStudentList().add(st);
    }

    public void addFaculty(FacultyLeafClass ft) {
           this.getFacultyList().add(ft);
    }

    public void removeStudent(int seqId) {
        this.getStudentList().remove(seqId);
    }

    public void removeFaculty(int seqId) {
        getFacultyList().remove(seqId);
    }

    @Override
    public void notifyObserver(String msg, String senderName, List<String> notificationLevel) {
        boolean sendToFacultyOnly=false;
        boolean sendStudentsOnly= false;
        if(notificationLevel==null){
            sendToFacultyOnly=true;
            sendStudentsOnly=true;
        }else if(notificationLevel.size()>0 && "Faculty".equalsIgnoreCase(notificationLevel.get(0))){
            sendToFacultyOnly=true;
        }else if(notificationLevel.size()>0 && "Students".equalsIgnoreCase(notificationLevel.get(0))){
            sendStudentsOnly=true;
        }
            if(sendStudentsOnly) {
                System.out.println("-----------------STUDENTS----------------------");
                for (StudentLeafClass st : getStudentList())
                    st.notifyObserver(msg, senderName, null);
            }
            if(sendToFacultyOnly) {
                System.out.println("-----------------FACULTY----------------------");
                for (FacultyLeafClass ft : getFacultyList())
                    ft.notifyObserver(msg, senderName, null);
            }

    }

    @Override
    public void emergencyNotification(String msg, String senderName) {
        for (StudentLeafClass st : getStudentList()) {
            st.emergencyNotification(msg, senderName);
        }

        for (FacultyLeafClass ft : getFacultyList()) {
            ft.emergencyNotification(msg, senderName);
        }
    }
}
