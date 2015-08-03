Feature: Get tour Details Full: Authentication CCAPI 
	
Scenario Outline: Check Authentication for CCAPI gettourdetailsfull
	Given Security key <securityKey>	
	Given Company code <companyCode>	
	Given Check type assert <assertType>
	When Check permision for request
	Then Response has flag <responseflag>
	And Response Message should have <responseMessage>

Examples:
        |securityKey  |companyCode|assertType|responseflag|responseMessage                      |
        |token-xxx1   |CHUKLS     |EQUALS    |false       |Invalid securityKey permission denied|
        |token:1      |CHUKLS     |EQUALS    |false       |Invalid function permission denied   |
        |token:2      |CHUKLS     |CONTAINS  |false       |Invalid companies                    |      
    