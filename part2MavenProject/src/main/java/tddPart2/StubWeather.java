package tddPart2;

import java.util.List;

import org.json.simple.JSONObject;

public class StubWeather extends Task {
	
	private double temp;

	@Override
	public boolean configure(List<String> params) {
		return true;
	}
	
	
@SuppressWarnings("unchecked")
protected JSONObject getAllApiValues() {
	
	JSONObject values = new JSONObject();
	String t = "temp";
	double v = 19.24;
	values.put(t, v);
	return values;
}
		
	
	
protected void setTempFromApi() {

	JSONObject values = getAllApiValues();
		
	if(values != null) {
		 temp = (double) values.get("temp");
	}

}


protected String categorizeTemperature(float temp) {  
	int nt = Math.round(temp);
	

	if(nt >=15 && nt <= 25) {
		return "Room Temp";
	}

	else {
		return "Cannot Read temperature.";
	}
}
	
	
	

	@Override
	public String run() {
		setTempFromApi();
		return categorizeTemperature((float)temp);
	}

}
