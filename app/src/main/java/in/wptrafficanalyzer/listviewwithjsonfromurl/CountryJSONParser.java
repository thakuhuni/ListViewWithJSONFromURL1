package in.wptrafficanalyzer.listviewwithjsonfromurl;

import android.annotation.TargetApi;
import android.os.Build;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/** A class to parse json data */
public class CountryJSONParser {

	public static String title = null;

	// Receives a JSONObject and returns a list
	public List<HashMap<String,Object>> parse(JSONObject jObject){
		
		JSONArray jRows = null;

		try {
			//// Retrieves the title
			title = jObject.getString("title");


			// Retrieves all the elements in the 'rows' array
			jRows = jObject.getJSONArray("rows");
		} catch (JSONException e) {
			e.printStackTrace();
		}



		 // Invoking getRow with the array of json object
		 // where each json object represent a row
		return getRows(jRows);
	}
	
	
	private List<HashMap<String, Object>> getRows(JSONArray jRows){
		int rowCount = jRows.length();
		List<HashMap<String, Object>> rowList = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> row = null;

		// Taking each row, parses and adds to list object
		for(int i=0; i<rowCount;i++){
			try {
				// Call getRow with row JSON object to parse the row
				row = getRow((JSONObject)jRows.get(i));
				rowList.add(row);

			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		return rowList;
	}
	
	// Parsing the Country JSON object 
	private HashMap<String, Object> getRow(JSONObject jRow){

		HashMap<String, Object> row = new HashMap<String, Object>();
		String rowTitle = "";
		String image_path="";
		String description = "";
		/*String capital = "";
		String currencyCode = "";
		String currencyName = "";	*/
		
		try {
            rowTitle = jRow.getString("title");
            image_path = jRow.getString("imageHref");
            description = jRow.getString("description");

            //if the title is not empty then load the HashMap
            if (rowTitle.trim().equals("null"))
            {
                //do nothing
            } else
                {
                row.put("title", rowTitle);
                row.put("image", R.drawable.blank);


                if (rowTitle.trim().equals("null") && description.equals("null")) {
                    //do nothing
                }

                if (description.equals("null")) {
                    row.put("description", "");
                } else {
                    row.put("description", description);
                }

                if (image_path.equals("null")) {
                    row.put("image_path", "");
                } else {
                    row.put("image_path", image_path);
                }

            }

			
		} catch (JSONException e) {			
			e.printStackTrace();
		}		
		return row;
	}
}