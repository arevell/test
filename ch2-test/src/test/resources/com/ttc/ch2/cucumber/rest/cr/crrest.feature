Feature: Rest to get file from Content repository   
	
Scenario Outline: Rest to get file from Content repository  
	Given User token <token>
	Given File Version <version>	
	Given Part path <partPath>	
	When Downolad file from contentRepository
	Then Response return xml file <responseflag>	

Examples:
|token       |partPath 				|version|responseflag|
|token-xxx	 |tour_departure/ 		|       |true        |
|token-xxx	 |tour_departure/    	|V3     |true        |
|token-xxx	 |tour_info/ 	     	|       |true        |
|token-xxx	 |tour_info/	     	|V3     |true        |