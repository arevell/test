Feature: Rest to get file from Content repository   
	
Scenario Outline: Rest to get file from Content repository  
	Given User token <token>
	Given Format part <formatPart>	
	Given Part path <partPath>			
	When Downolad file from contentRepository
	Then Response return content <responseContent>	
	Then Response return status code <statusCode>	

Examples:
|token       |formatPart|partPath 				            |responseContent                                                    |statusCode|
|token-xxx	 |xml       |tour_departure/ 	 	            |Access denied invalid path                                         |401       |
|token-xxx	 |xml       |tour_departure/XX/	            |Access denied invalid brand code for function:CR_VIEW_V1           |401       |
|token-xxx	 |xml       |tour_departure/XX/V2/	            |Access denied invalid path								           |401       |
|token-xxx	 |xml       |tour_departure/XX/V1/	            |Access denied invalid brand code for function:CR_VIEW_V1           |401       |
|token-xxx	 |xml       |tour_departure/XX/V3/         	|Access denied invalid brand code for function:CR_VIEW_V3           |401       |
|token-xxx	 |xml       |tour_departure/sample/path/XX/V3/	|Access denied invalid path          							   |401       |
|token-xxx	 |xml       |tour_departure/sample/XX/path/V3/	|Access denied invalid path          							   |401       |
|token-xxx	 |xml       |tour_departure/XX/sample/path/V3/	|Access denied invalid path          							   |401       |
|token-xxx	 |xml       |tour_departure/XX/sample/V3/path/	|Access denied invalid path         								   |401       |
|token-xxx	 |xml       |tour_departure/sample/CH/V3/   	|Access denied invalid path								           |401       |
|token-xxx	 |          |tour_departure/CH/V3/file.pdf   	|Access denied invalid path								           |401       |
|token-xxx	 |          |tour_departure/XX/V2/file.xml   	|Access denied invalid brand code						           |401       |
|t-notexist	 |xml       |tour_departure/CH/			   	|Unauthorized											           |401       |
|token-xxx	 |xml       |tour_info/	 	 	            |Access denied invalid path                                         |401       |
|token-xxx	 |xml       |tour_info/XX/	   			        |Access denied invalid brand code for function:CR_VIEW_V1           |401       |
|token-xxx	 |xml       |tour_info/XX/V2/	        	    |Access denied invalid path								           |401       |
|token-xxx	 |xml       |tour_info/XX/V1/	            	|Access denied invalid brand code for function:CR_VIEW_V1           |401       |
|token-xxx	 |xml       |tour_info/XX/V3/         			|Access denied invalid brand code for function:CR_VIEW_V3           |401       |
|token-xxx	 |xml       |tour_info/sample/path/XX/V3/		|Access denied invalid path          							   |401       |
|token-xxx	 |xml       |tour_info/sample/XX/path/V3/		|Access denied invalid path          							   |401       |
|token-xxx	 |xml       |tour_info/XX/sample/path/V3/		|Access denied invalid path          							   |401       |
|token-xxx	 |xml       |tour_info/XX/sample/V3/path/		|Access denied invalid path         								   |401       |
|token-xxx	 |xml       |tour_info/sample/CH/V3/   		|Access denied invalid path								           |401       |
|token-xxx	 |          |tour_info/CH/V3/file.pdf   		|Access denied invalid path								           |401       |
|token-xxx	 |          |tour_info/XX/V2/file.xml   		|Access denied invalid brand code						           |401       |
|t-notexist	 |xml       |tour_info/CH/					   	|Unauthorized											           |401       |
