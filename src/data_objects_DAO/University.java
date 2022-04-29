package data_objects_DAO;

import composite.colleges.CollegeImpl;

import java.util.List;

/**
 * university dao class
 */
public class University {
    private static University universitySingleInstance = null;

    String universityName;
    List<CollegeImpl> collegeList;

    public static University getInstance()
    {
        if (universitySingleInstance == null)
            universitySingleInstance = new University();

        return universitySingleInstance;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public List<CollegeImpl> getCollegeList() {
        return collegeList;
    }

    public void setCollegeList(List<CollegeImpl> collegeList) {
        this.collegeList = collegeList;
    }
}
