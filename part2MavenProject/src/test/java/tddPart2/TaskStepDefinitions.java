package tddPart2;


import java.util.ArrayList;
import java.util.List;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TaskStepDefinitions {

	private WeatherTask wt;
	private List<String> configList = new ArrayList<String>();
	private int temp;
	
	private FileTask ft;
	private List<String> fileSettings = new ArrayList<String>();
	
	@Given("^I have (\\d+) cities in configuration$")
	public void i_have_cities_in_configuration(String arg1) {
	   wt = new WeatherTask();
	   
	}

	@When("^I add (\\w+) valid city name to the configuration$")
	public void i_add_valid_city_name_to_the_configuration(String arg1) {
		   configList.add(arg1);		
	}

	@Then("^the city is added to configuration$")
	public void the_city_is_added_to_configuration() {
		   wt.configure(configList);
	}
	

	@When("^I add (\\d+) invalid city name to the configuration$")
	public void i_add_invalid_city_name_to_the_configuration(String arg1) {
		  wt = new WeatherTask();
	}

	@Then("^return false$")
	public boolean return_false_1()  {
		return false;
	}

	@When("^I add (\\d+) city names in configuration$")
	public void i_add_city_names_in_configuration(String[]names) {
		wt = new WeatherTask();
		for(int i =0; i<names.length; i++) {
			configList.add(names[i]);
		}
	}

	@Then("^return false$")
	public boolean return_false_2() {
		if(configList.size() > 1) {
			return false;
		}
		return true;
	}

	@When("^I add (\\d+) city in configuration$")
	public void i_add_city_in_configuration(int arg1) {
		 wt = new WeatherTask();
	}

	@Given("^temperature is (\\d+)$")
	public void temperature_is_(int arg1){
		 wt = new WeatherTask();
	}

	@When("^temperature is (\\d+)$")
	public void temperature_is(int arg1) {
		this.temp = arg1;
	}

	@Then("^it is very cold$")
	public String it_is_very_cold() {
	    if (this.temp < 6) {
	    	return "Very Cold";
	    }
	    return "Cannot Read temperature.";
	}

	@Given("^temperature is null$")
	public void temperature_is_null_()  {
		 wt = new WeatherTask();
	}

	@When("^temperature is null$")
	public void temperature_is_null()  {
	}

	@Then("^return cannot read temperature$")
	public String return_cannot_read_temp() {
		return "Cannot Read temperature.";
	}

	@Given("^I have (\\d+) temperatures as arguments$")
	public void i_have_temperatures_as_arguments(int arg1) throws Throwable {
	    if(arg1 > 1) {
	    	throw new Exception();
	    }
	}

	@When("^I have (\\d+) temperatures$")
	public void i_have_temperatures(int arg1) throws Throwable {
		if(arg1 > 1) {
	    	throw new Exception();
	    }
	}

	
	@Given("^I have (\\d+) files in configuration$")
	public void i_have_files_in_configuration(String arg1) {
		ft = new FileTask();
		fileSettings.add(arg1);
		ft.configure(fileSettings);
	}

	@When("^I add null to configuration$")
	public boolean i_add_null_to_configuration() {
	   return false;
	}

	@When("^I add file location only to configuration$")
	public void i_add_file_location_only_to_configuration() {
		ft = new FileTask();
		fileSettings.add("src/test/resources");
		ft.configure(fileSettings);
	}

	@When("^I add file location$")
	public void i_add_file_location() {
		fileSettings.add("src/test/resources");
	}

	@When("^I add file name$")
	public void i_add_file_name() {
		fileSettings.add("file.txt");
	}

	@Then("^I have (\\d+) file in configuration$")
	public void i_have_file_in_configuration(int arg1) {
		ft.configure(fileSettings);
	}

	
	
}
