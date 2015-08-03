Feature: Get brochures 
	
Scenario Outline: Tour categories are searched for the following parameters
	Given Selling Company code <sellingCompanyCode>
	Given number of records <numberOfRecords>
	Given token is equal <token>
	When I request for the getBrochure
	Then Test result is valid
	
Examples:
        |sellingCompanyCode |numberOfRecords |token    |
        |CHCANS             |5               |token-xxx|
