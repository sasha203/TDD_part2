package tddPart2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tddPart2.StubWeather;
import tddPart2.Task;
import tddPart2.WeatherTask;


public class WeatherTest {
	
	@Test
	public void adding_one_City() {
		List<String> configList = new ArrayList<String>();
		Task wt = new WeatherTask();
		configList.add("Valletta");
		assertTrue(wt.configure(configList));
	}
	
	
	@Test
	public void adding_more_than_one_City() {
		List<String> configList = new ArrayList<String>();
		Task wt = new WeatherTask();
		configList.add("Valletta");
		configList.add("Tokyo");
		assertFalse(wt.configure(configList));
	}
	
	
	@Test
	public void check_for_valid_apiKey() {
		List<String> configList = new ArrayList<String>();
		WeatherTask wt = new WeatherTask();
		configList.add("Valletta");
		wt.configure(configList);
		
		assertEquals("http://api.openweathermap.org/data/2.5/weather?q=valletta&units=metric&appid=d12e761b653064784c9d028522c92fe9",wt.getFullApiUrl());
	}
	

	
	@Test
	public void if_configure_fails_apiKey_is_null() {
		List<String> configList = new ArrayList<String>();
		WeatherTask wt = new WeatherTask();
		assertFalse(wt.configure(configList));
		assertNull(wt.getFullApiUrl());
	}
	
	
	@Test
	public void adding_null_list_to_config() {
		Task wt = new WeatherTask();	
		assertFalse(wt.configure(null));		
	}
	
	
	//0 Items
	@Test
	public void adding_nothing_into_configList_which_is_passed_to_config() {
		Task wt = new WeatherTask();	
		List<String> configList = new ArrayList<String>();
		assertFalse(wt.configure(configList));		
	}
	
	
	@Test
	public void passing_empty_value_in_configList() {
		WeatherTask wt = new WeatherTask();
		List<String> configList = new ArrayList<String>();
		configList.add("");
		assertFalse(wt.configure(configList));
	}
	

	@Test
	public void testing_categorize_temperature_method() {
		 WeatherTask wt = new  WeatherTask();
		 //assertEquals("Boundry testing on cold:", "cold", wt.categorizeTemperature(6).toLowerCase());
		 float tempValue[] = new float[] {4f, 6f, 15f, 26f, 32f, 37f};
		 String tempText[] = new String[] {"very cold", "cold", "room temp", "hot", "very hot", "heat wave"};
		 
		 for(int i=0; i<tempText.length; i++) {
			 assertEquals(tempText[i].toLowerCase(), wt.categorizeTemperature(tempValue[i]).toLowerCase());
		 }
	}

	

	@Test
	public void requesting_all_info_from_api_method() {
		WeatherTask wt = new WeatherTask();
		List<String> configList = new ArrayList<String>();
		configList.add("Valletta");
		wt.configure(configList);
		assertEquals(13,  wt.getAllApiValues().size());
	}
	

	
	
	@Test
	public void Weather_Driver() {
		WeatherTask wt = new WeatherTask();
		List<String> configList = new ArrayList<String>();
		configList.add("Valletta");
		if(wt.configure(configList)) {
			wt.run();
			assertEquals(20.89,  wt.getTemp(),0.50);
		}
	}
	
	
	@Test(expected = RuntimeException.class)
	public void httpResponse_Not_200()  throws RuntimeException{
		WeatherTask wt = new WeatherTask();
		List<String> configList = new ArrayList<String>();
		configList.add("abc");
		wt.configure(configList);
		wt.run();
	}
	
	@Test
	public void weather_stub() {
		StubWeather sw = new StubWeather();
		List<String> configList = new ArrayList<String>();
		configList.add("Valletta");
		sw.configure(configList);
		assertEquals("Room Temp", sw.run());
	}
	

	
	
	
}
