package data_objects_DAO;

import leaf.FacultyLeafClass;
import leaf.StudentLeafClass;

import java.util.List;

public class Department {

    String departmentName;
    String collegeName;
    List<FacultyLeafClass> facultyList;
    List<StudentLeafClass> studentList;

    public Department() {
    }

    public Department(String departmentName, String collegeName, List<FacultyLeafClass> facultyList, List<StudentLeafClass> studentList) {
        this.departmentName = departmentName;
        this.collegeName = collegeName;
        this.facultyList = facultyList;
        this.studentList = studentList;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public List<FacultyLeafClass> getFacultyList() {
        return facultyList;
    }

    public void setFacultyList(List<FacultyLeafClass> facultyList) {
        this.facultyList = facultyList;
    }

    public List<StudentLeafClass> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentLeafClass> studentList) {
        this.studentList = studentList;
    }
}