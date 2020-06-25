package tddPart2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;



public class WeatherTask extends Task {

	public WeatherTask() {}
	
	//api with valletta as a city.
	//http://api.openweathermap.org/data/2.5/weather?q=Valletta&units=metric&appid=d12e761b653064784c9d028522c92fe9
		
	private String urlPt1 = "http://api.openweathermap.org/data/2.5/weather?q=";
	private String city = "\0"; //Valletta
	private String urlPt2 = "&units=metric&appid=";
	private String apiKey = "d12e761b653064784c9d028522c92fe9";
	private String apiFullUrl = null;
	private double temp;
	
	protected String getFullApiUrl() {
		return apiFullUrl;
	}	
	
	protected double getTemp() {
		return temp;
	}
	
	@Override
	public boolean configure(List<String> configList) { //cityName
		
		if(configList == null) {
			return false;
		}

		else if(configList.size() < 1 || configList.size() > 1 ) {
			return false;
		}
			
			
			if(configList.get(0) != null) {
				if(configList.get(0).isEmpty()) {
					return false;
				}
				
				else {
					city = configList.get(0).toLowerCase();
					apiFullUrl = urlPt1 + city + urlPt2 + apiKey;
				}
				
			}
	
		return true;
	}
	

	//https://dzone.com/articles/how-to-parse-json-data-from-a-rest-api-using-simpl
	//https://www.baeldung.com/java-http-request
	protected JSONObject getAllApiValues() {
		
		JSONObject values = null;
		
		if(apiFullUrl != null) {
			try {
				
				URL url = new URL(apiFullUrl);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				con.setRequestMethod("GET");
				con.connect(); //should be stubbed without
				
				int responseCode = con.getResponseCode();
				
				
				if (responseCode != 200) {
					throw new RuntimeException("HttpResponseCode: " + responseCode);
				} else {
				
					BufferedReader br = new BufferedReader(
							new InputStreamReader(con.getInputStream()));
					String inputLine;
					StringBuffer content = new StringBuffer();
					while ((inputLine = br.readLine()) != null) {
					    content.append(inputLine);
					}
					
					
					JSONParser jParser = new JSONParser(); 
					values = (JSONObject)jParser.parse(content.toString()); 
					
					
					br.close();
					con.disconnect(); //should be stubbed without
				}
			} 
			catch (Exception e) {
				//e.printStackTrace();
				throw new RuntimeException();
			}
		}
		
		return values;
	}
	
	
	
	protected void setTempFromApi() {
		//will get the temperature
		JSONObject values = getAllApiValues();
		JSONObject mainStats = null;
			
		if(values != null) {
			 mainStats = (JSONObject) values.get("main");
			 temp = (double) mainStats.get("temp");
		}
	
	}
	
	
	@Override
	public String run() {
		setTempFromApi();
		return categorizeTemperature((float)temp); // returns the category
	}
	
	
	
	
	protected String categorizeTemperature(float temp) {  
		//calculate what range will the temp fall under 

		/* Ranges :
		 * <6 very cold
		 * 6 - 14 cold
		 * 15 - 25 room temp
		 * 26- 31 hot
		 * 32 - 36 very hot
		 * >=37 heatWave 
		*/
		int nt = Math.round(temp);
		
		if(nt < 6) {
			return "Very Cold";
		}
		else if(nt >=6 && nt <=14) {
			return "Cold";
		}
		else if(nt >=15 && nt <= 25) {
			return "Room Temp";
		}
		else if(nt>= 26 && nt <= 31) {
			return "Hot";
		}
		else if(nt>= 32 && nt <= 36) {
			return "Very Hot";
		}
		else if(nt >= 37) {
			return "Heat Wave";
		}
		
		else {
			return "Cannot Read temperature.";
		}
	}
	
	
	
	
}
