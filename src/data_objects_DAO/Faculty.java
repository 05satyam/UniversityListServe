package data_objects_DAO;

import leaf.FacultyLeafClass;
import java.util.List;

/**
 * faculty dao class
 */
public class Faculty {
    String facultyName;
    String departmentName;
    String facultyId;
    String yearOfJoining;
    Department department;

    public String getFacultyName() {
        return facultyName;
    }

    public void setFacultyName(String facultyName) {
        this.facultyName = facultyName;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(String facultyId) {
        this.facultyId = facultyId;
    }

    public String getYearOfJoining() {
        return yearOfJoining;
    }

    public void setYearOfJoining(String yearOfJoining) {
        this.yearOfJoining = yearOfJoining;
    }

    public Faculty(String facultyName, String departmentName, String facultyId, String yearOfJoining) {
        this.facultyName = facultyName;
        this.departmentName = departmentName;
        this.facultyId = facultyId;
        this.yearOfJoining = yearOfJoining;
    }

    public Faculty() {
    }


    @Override
    public String toString() {
        return  "Faculty Name='" + facultyName + '\'' +
                ", Department Name='" + departmentName + '\'' +
                ", Faculty Id='" + facultyId + '\'' +
                ", Year Of Joining='" + yearOfJoining + '\'';
    }

    public static void printCurrentFacultyData(List<FacultyLeafClass> facultyList){
        int i=1;
        for(Faculty ft : facultyList){
            System.out.println("sequenceId : "+ i + "   faculty data : " + ft.toString());
            i++;
        }
    }
}
