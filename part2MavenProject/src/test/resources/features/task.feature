Feature: Task

 Scenario: Adding one valid city
 	Given I have 0 cities in configuration
 	When I add 1 valid city name to the configuration
 	Then the city is added to configuration
 	
 Scenario: Adding one invalid city
 	Given I have 0 cities in configuration
 	When I add 1 invalid city name to the configuration
 	return false
 
 Scenario: Adding no cities
 	Given I have 0 cities in configuration
 	When I add 0 city names in configuration
 	Then return false
 
 Scenario: Adding more than one city
 	Given I have 1 cities in configuration
 	When I add 1 city in configuration
 	Then return false

 Scenario: Categorise temperature
 	Given temperature is 5
 	When temperature is 5
 	Then it is very cold
 	
 Scenario: Category temperature is null
 	Given temperature is null
 	When temperature is null
 	Then return cannot read temperature
 	
 Scenario: More than temperature passed as argument
 	Given I have 2 temperatures as arguments
 	When I have 2 temperatures 
 	Then an exception should be thrown
 	
 Scenario: adding no arguments in file configuration
 	Given I have 0 files in configuration
 	When I add null to configuration
 	Then return false
 	
 Scenario: Passing only file location to file configuration
 	Given I have 0 files in configuration
 	When I add file location only to configuration
 	Then return false
 	
 Scenario: Passing both arguments in file configuration
 	Given I have 0 files in configuration
 	When I add file location
 		And I add file name
 	Then I have 1 file in configuration
 	
 	
 