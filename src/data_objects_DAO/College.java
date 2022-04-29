package data_objects_DAO;

import composite.departments.DepartmentImpl;

import java.util.List;

/**
 * college dao
 */
public class College {
    String collegeName;
    List<DepartmentImpl> departmentList;
    University university;

    public College(String collegeName, List<DepartmentImpl> departmentList, University university) {
        this.collegeName = collegeName;
        this.departmentList = departmentList;
        this.university = university;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public List<DepartmentImpl> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<DepartmentImpl> departmentList) {
        this.departmentList = departmentList;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
