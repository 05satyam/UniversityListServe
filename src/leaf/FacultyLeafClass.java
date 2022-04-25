package leaf;

import component.UniversityInterface;
import data_objects_DAO.Faculty;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utilities.JsonParser;

import java.util.ArrayList;
import java.util.List;

public class FacultyLeafClass extends  Faculty implements UniversityInterface {

    public FacultyLeafClass() {
    }

    public FacultyLeafClass(String facultyName, String departmentName, String facultyId, String yearOfJoining) {
        super(facultyName, departmentName, facultyId, yearOfJoining);
    }

    @Override
    public void notifyObserver(String msg, String senderName, List<String> notificationLevel) {
        System.out.println("Notification received from "+ senderName);
        System.out.println("Notification: "+ msg);
        System.out.println("Message delivered to "+ this.getFacultyName());
    }

    @Override
    public void emergencyNotification(String msg, String senderName) {
        System.out.println("IT IS AN ALERT FROM "+ senderName);
        System.out.println(msg);
    }

    private void printFacultyData(Faculty ft){
        ft.toString();
    }


    public static List<FacultyLeafClass> getFacultyListFromStaticData() {
        JSONArray jsonArray = JsonParser.getJsonArray("faculties","src/static_data_json/facultyDataList.json");
        if (jsonArray == null)
            return null;
        List<FacultyLeafClass> facultyList = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonobject = (JSONObject) jsonArray.get(i);
            FacultyLeafClass ft = new FacultyLeafClass((String) jsonobject.get("facultyName"), (String) jsonobject.get("departmentName"),
                    (String) jsonobject.get("facultyId"), (String) jsonobject.get("yearOfJoining"));
            facultyList.add(ft);

        }
        return facultyList;
    }
}
