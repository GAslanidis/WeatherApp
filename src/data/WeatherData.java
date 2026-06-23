package data;

import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author georgiosaslanidis
 */

public class WeatherData{

    private String JSONStringData;
    private CDictionary<String, Object> oWeatherDict;

//------------------------------------------------------------------------------
    //Constructor
    public WeatherData(String p_sJSONStringData) {
        
        this.JSONStringData = p_sJSONStringData;
        this.oWeatherDict = new CDictionary<>(new CCompare());
        this.SaveWeatherData(this.JSONStringData);
    }
    
//------------------------------------------------------------------------------
    //Create Dictionary
    private void SaveWeatherData(String jsonString) {

        try {
            // Parse JSON string
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);

            // Populate CDictionary with key-value pairs
            this.populateDictionary("", jsonObject, oWeatherDict);

            // Checking the print of the dictionary
            System.out.println(oWeatherDict.toString());

        } catch (ParseException e) {
            
            e.printStackTrace();
        }
    }

    //Recurse Create Dictionary - Called in the public method above
    private static void populateDictionary(String p_sPrefix, JSONObject p_oJSONObject, CDictionary<String, Object> p_oDictionary) {
        
        for (Object entry : p_oJSONObject.entrySet()) {
            
            Map.Entry<String, Object> mapEntry = (Map.Entry<String, Object>) entry;
            String key = mapEntry.getKey();
            Object value = mapEntry.getValue();

            if (value instanceof JSONObject) {
                
                // Recursively handle nested dictionaries
                //p_sPrefix is here in case there more than one object
                //inside the starter object
                populateDictionary(p_sPrefix + key + ".", (JSONObject) value, p_oDictionary);
            } 
            else if (value instanceof JSONArray) {
                
                // Handle arrays if necessary
                // You may need to iterate through the array elements and handle them accordingly
                for(int i=0; i < ((JSONArray) value).size(); i++){
                    
                    populateDictionary(p_sPrefix + key + ".", (JSONObject) ((JSONArray) value).get(i), p_oDictionary);
                }
            } 
            else {
                
                // Add key-value pair to CDictionary
                p_oDictionary.setValue(p_sPrefix + key, value);
            }
        }
    }

//------------------------------------------------------------------------------
    //Find Entry - Any information of the weather you need
    public CKeyValueEntry GetEntry(String p_sKey){
        
        return this.oWeatherDict.FindEntry(p_sKey);
    }
    
//------------------------------------------------------------------------------
    //Clear
    public void ClearData(){
        
        if(this.oWeatherDict.itemCount != 0)
            this.oWeatherDict.Clear();
    }
//------------------------------------------------------------------------------
    //print Dictionary
    public String DisplayWeather() {

        return this.oWeatherDict.toString();
    }
    /*
    
    //This is how the info i get from api will be
    //I need to make simple strings or int for Objects that already have a value like "visibility" :"10000",
    //but i will need to make a dictionary or something like that for information
    //that are such "main" :"{"temp":282.38,"temp_min":280.76,"humidity":67,"pressure":1031,"feels_like":279.36,"temp_max":283.33}"
    //such data will be JSONArray, while simple ones will be JSONObjects 
    //each position in JSONArray will have Key: Temp - Value: 282.38 etc.
    {"coord":{"lon":23.7162,"lat":37.9795}
    
    ,"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02n"}]
    
    ,"base":"stations"
    
    ,"main":{"temp":286.41,"feels_like":285.7,"temp_min":284,"temp_max":287.73,"pressure":1005,"humidity":73}
    
    ,"visibility":10000
    
    ,"wind":{"speed":4.02,"deg":303,"gust":6.71}
    
    ,"clouds":{"all":20}
    
    ,"dt":1703175318
    
    ,"sys":{"type":2,"id":2005332,"country":"GR","sunrise":1703137034,"sunset":1703171322}
    
    ,"timezone":7200
    ,"id":264371
    ,"name":"Athens"
    ,"cod":200}

     */

}
