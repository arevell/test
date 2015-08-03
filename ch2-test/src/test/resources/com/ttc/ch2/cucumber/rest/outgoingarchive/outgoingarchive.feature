Feature: Rest to get file from outgoing archive  
	
Scenario Outline: Rest to get file from outgoing archive  
	Given User token <token>
	Given File Version <version>	
	Given Part path <partPath>	
	When Downolad file from outgoing archive repository
	Then Response return zip file <responseflag>	

Examples:
|token       |partPath 				|version|responseflag|
|token-xxx	 |outgoing_archives/ 		|       |true        |
|token-xxx	 |outgoing_archives/    	|V3     |true        |