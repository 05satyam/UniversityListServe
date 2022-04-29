package utilities;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * this is a uitility class to parse json and return json array from any json file
 * THis is generic class.
 */
public class JsonParser {

    /**
     *
     * @param jsonArrayObjName
     * @param filePathName
     * @return
     *
     * this accepts json object name and path of json files and return json array. It is a generic function
     */
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
