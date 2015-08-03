Feature: Rest to get brochure file  
	
Scenario Outline: Rest to get brochure file  
	Given User token <token>			
	Given Part path <partPath>	
	When Downolad file brochure
	Then Response return pdf file <responseflag>	
Examples:
|token       |partPath 				            |responseflag|
|token-xxx	 |brochure_engine/brochure.pdf 		|true        |
