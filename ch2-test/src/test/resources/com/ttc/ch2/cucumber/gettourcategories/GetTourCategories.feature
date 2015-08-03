Feature: Get tour categories 
	
Scenario Outline: Tour categories are searched for the following parameters
	Given Selling Company code <sellingCompanyCode>
	Given number of records <numberOfRecords>
	Given token is equal <token>
	When I request for the getTourCategories
	Then Test result is valid
	
Examples:
        |sellingCompanyCode |numberOfRecords |token    |
        |CHCANS             |100             |token-xxx|
		|CHSYDS				|100             |token-xxx|
		|CHUSAS				|100             |token-xxx|
		|CHAKLS				|100             |token-xxx|
		|CHSINS				|100             |token-xxx|