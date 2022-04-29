package composite.departments;

import component.UniversityInterface;
import data_objects_DAO.Department;
import data_objects_DAO.Faculty;
import leaf.FacultyLeafClass;
import leaf.StudentLeafClass;

import java.util.ArrayList;
import java.util.List;

/**
 * main department  class working as composite in composite design pattern
 *
 * extending department dao and implementing University interface
 */
public class DepartmentImpl extends Department implements UniversityInterface {


    public DepartmentImpl() {
    }

    /**
     *
     * @param departmentName
     * @param collegeName
     * @param facultyList
     * @param studentList
     *
     * parametrized constructor
     */
    public DepartmentImpl(String departmentName, String collegeName, List<FacultyLeafClass> facultyList, List<StudentLeafClass> studentList) {
        super(departmentName, collegeName, facultyList, studentList);
    }

    /**
     *
     * @param st
     *
     * add student into existing students list of the department
     */
    public void addStudent(StudentLeafClass st) {
        getStudentList().add(st);
    }

    /**
     *
     * @param ft
     * add faculty into existing faculty list of the department
     */
    public void addFaculty(FacultyLeafClass ft) {
           getFacultyList().add(ft);
    }

    /**
     *
     * @param seqId
     * remove student from existing students list of the department
     */
    public void removeStudent(int seqId) {
        getStudentList().remove(seqId);
    }


    /**
     *
     * @param seqId
     * remove faculty into existing faculty list of the department
     */
    public void removeFaculty(int seqId) {
        getFacultyList().remove(seqId);
    }

    /**
     *
     * @param msg
     * @param senderName
     * @param notificationLevel
     *
     * send announcement
     * cases covered:
     *    1. Send to students
     *    2. Send to faculty
     *    3. Send to Both
     */
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
            if(sendStudentsOnly) {
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
