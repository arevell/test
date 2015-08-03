@CH2_API
Feature: Get continents and countries visited
	
Scenario Outline: Continents are searched for the following parameters
	Given Selling Company code <sellingCompanyCode>
	Given number of records <numberOfRecords>
	Given token is equal <token>
	When I request for getContinentsAndCountriesVisited
	Then Test result is valid
	
Examples:
        |sellingCompanyCode |numberOfRecords |token    |
        |CHCANS             |5               |token-xxx|
        |CHSYDS				|5               |token-xxx|
		|CHUSAS				|5               |token-xxx|
		|CHAKLS				|5               |token-xxx|
		|CHSINS				|5               |token-xxx|
