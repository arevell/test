Feature: Upload: Authentication CCAPI 
	
Scenario Outline: Check Authentication for CCAPI upload tour info
	Given Security key <securityKey>	
	When Check permision for request
	Then Response has flag <responseflag>
	And Response Message should have <responseMessage>

Examples:
        |securityKey  |responseflag|responseMessage                      |
        |token-xxx1   |false       |Invalid securityKey permission denied|
        |token:1      |false       |Invalid function permission denied   |
        |token-bv     |false       |Permission denied for brand          |  
#       |token:2      |false       |Invalid companies                    |
          
    