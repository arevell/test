@CCAPI_Security
Feature: Snapshot Upload Tour Info: Authentication CCAPI 
	
Scenario Outline: Check Authentication for CCAPI GetTourDataUploadStatus
	Given Security key <securityKey>	
	Given Brand code <brandCode>	
	Given Check type assert <assertType>
	When Check permision for request
	Then Response has flag <responseflag>
	And Response Message should have <responseMessage>

Examples:
        |securityKey  |brandCode  |assertType|responseflag|responseMessage                      |
        |token-xxx1   |BV         |EQUALS    |false       |Invalid securityKey permission denied|
        |token:1      |BV         |EQUALS    |false       |Invalid function permission denied   |
        |token:2      |BV         |EQUALS    |false       |Permission denied for brand:BV       |
    