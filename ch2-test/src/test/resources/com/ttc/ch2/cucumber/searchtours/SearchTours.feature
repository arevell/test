Feature: Search for tours
	
Scenario Outline: Tours are searched for the following parameters
	Given Selling Company code <sellingCompanyCode>
	Given number of records <numberOfRecords>
	Given search tours test parameter <searchToursTest>
	Given token is equal <token>
	When I request for the search tours
	Then Test result is valid
	
Examples:
        |sellingCompanyCode |numberOfRecords |searchToursTest |token    |
        |                   |5               |Continents      |token-xxx|
        |                   |5               |Countries       |token-xxx|
        |                   |5               |Durations       |token-xxx|
        |                   |5               |Months          |token-xxx|
#		|CHSINS             |5               |PreferedRoomTypes|token-xxx|
		|                   |5               |Prices          |token-xxx|
#		|asdf               |5               |Months          |token-xxx|