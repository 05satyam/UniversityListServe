package data_objects_DAO;

import leaf.StudentLeafClass;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utilities.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * student dao class
 */
public class Student {
    String studentName;
    String studentId;
    String departmentName;
    String yearOfAdmission;
    Department department;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getYearOfAdmission() {
        return yearOfAdmission;
    }

    public void setYearOfAdmission(String yearOfAdmission) {
        this.yearOfAdmission = yearOfAdmission;
    }

    public Student(String studentName, String studentId, String departmentName, String yearOfAdmission) {
        this.studentName = studentName;
        this.studentId = studentId;
        this.departmentName = departmentName;
        this.yearOfAdmission = yearOfAdmission;
    }

    public Student() {
    }

    public List<StudentLeafClass> getStudentListFromStaticData() {
        JSONArray jsonArray = JsonParser.getJsonArray("students", "src/static_data_json/studentDataList.json");
        if (jsonArray == null)
            return null;
        List<StudentLeafClass> studentList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonobject = (JSONObject) jsonArray.get(i);
            StudentLeafClass st = new StudentLeafClass((String) jsonobject.get("studentName"), (String) jsonobject.get("studentId"),
                    (String) jsonobject.get("departmentName"), (String) jsonobject.get("yearOfAdmission"));
            studentList.add(st);

        }
        return studentList;
    }

    @Override
    public String toString() {
        return "Student Name='" + studentName + '\'' +
                ", Student Id='" + studentId + '\'' +
                ", Department Name='" + departmentName + '\'' +
                ", Year Of Admission='" + yearOfAdmission + '\'' ;
    }

    public static void printCurrentStudentData(List<StudentLeafClass> studentList){
        int i=1;
        for(Student st : studentList){
            System.out.println("sequenceId : "+ i + "   student data : " + st.toString());
            i++;
        }
    }
}

