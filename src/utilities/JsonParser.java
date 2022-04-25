package utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class JsonParser {
    public static JSONArray getJsonArray(String jsonArrayObjName, String filePathName) {
        try {
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(new FileReader(filePathName));
            JSONObject jsonObject = (JSONObject) obj;
            // loop array

            return (JSONArray) jsonObject.get(jsonArrayObjName);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
