package leaf;

import component.UniversityInterface;
import data_objects_DAO.Student;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utilities.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class StudentLeafClass extends Student implements UniversityInterface {

    public StudentLeafClass() {
    }

    public StudentLeafClass(String studentName, String studentId, String departmentName, String yearOfAdmission) {
        super(studentName, studentId, departmentName, yearOfAdmission);
    }

    @Override
    public void notifyObserver(String msg, String senderName, List<String> notificationLevel) {
        System.out.println("Notification received from "+ senderName);
        System.out.println("Notification: "+ msg);
        System.out.println("Message delivered to "+ this.getStudentName());
    }

    @Override
    public void emergencyNotification(String msg, String senderName) {
        System.out.println("IT IS AN ALERT FROM "+ senderName);
        System.out.println(msg);
    }


    public List<StudentLeafClass> getStudentListFromStaticData() {
        JSONArray jsonArray = JsonParser.getJsonArray("studentData", "src/static_data_json/studentDataList.json");
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
}
