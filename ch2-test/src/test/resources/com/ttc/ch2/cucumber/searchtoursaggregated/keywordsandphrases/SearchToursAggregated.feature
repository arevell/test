Feature: Search for tours aggregated

Scenario Outline: Tours are searched for the following parameters
	Given Selling Company code <sellingCompanyCode>
	Given number of records <numberOfRecords>
	Given search tours test parameter <searchToursTest>
	Given token is equal <token>
	Given keyword is equal <keyword>
	When I request for the search tours aggregated
	Then Test result is valid
	And TourCode is equal <tourCode>

	
Examples:
        |sellingCompanyCode |numberOfRecords |searchToursTest |token    |keyword|tourCode|
        |CHUSAS             |1               |TOUR_CODE       |token-xxx|       |        |
        |CHUSAS             |1               |OP_CODE         |token-xxx|       |        |
        |CHUSAS             |1               |BROCHURE_CODE   |token-xxx|       |        |
        |CHUSAS             |1               |CATTOUR_CODE    |token-xxx|       |        |
        |CHUSAS             |1               |COUNTRY_NAME    |token-xxx|       |        |
        |CHUSAS             |1               |CONTINENT_NAME  |token-xxx|       |        |
  		|CHSYDS             |0               |TOUR_NAME       |token-xxx|TNChampsÉlysées|66633SPECIAL|
 		|CHSYDS             |0               |DESCRIPTION     |token-xxx|DSCChamps|66633SPECIAL|
 		|CHSYDS             |0               |LOCATION_NAME   |token-xxx|LOCChamps|66633SPECIAL|
 		|CHSYDS             |0               |BROCHURE_NAME   |token-xxx|BRXChamps|66633SPECIAL|
 		|CHSYDS             |0               |LOCATION_CODE   |token-xxx|CCChamps |66633SPECIAL|
 		|CHSYDS             |0               |ITINERARY_TEXT  |token-xxx|ISChamps |66633SPECIAL|
 		|CHSYDS             |0               |ITINERARY_TITLE |token-xxx|ISTChamps|66633SPECIAL|
 		|CHSYDS             |0               |WHATSINCL_TEXT  |token-xxx|WITChamps|66633SPECIAL|
 		|CHSYDS             |0               |WHATSINCL_TITLE |token-xxx|WIChamps |66633SPECIAL|
 		|CHSYDS             |0               |HIGHLIGHTS      |token-xxx|HTChamps |66633SPECIAL|
		|CHSYDS             |0               |TOURCAT_NAME    |token-xxx|TCNChampsÉlysées|66633SPECIAL|
 		|CHSYDS             |0               |TOURCAT_CAT     |token-xxx|CVChampsÉlysées|66633SPECIAL|