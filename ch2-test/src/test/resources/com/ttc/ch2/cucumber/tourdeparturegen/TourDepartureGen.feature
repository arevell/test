Feature: Map TD 3.0 into TD 1.0

Scenario Outline: the Tours data are returned and mapping process went fine
	Given selling companies with codes <sellingCompaniesCodes>
	And number of tours equal <numberOfTours>
	When call the web service for tours data
	Then valid tours data are returned
	When call the web service for departures data
	Then valid departures data are returned
	And backwards Mapping of Tour Departures succeed
	#And save to file succeed for a given <path>

Examples:
	|	sellingCompaniesCodes	|	numberOfTours	|	path		|
	|	TTUKLS, TTUSAS			|	1				|	C:/tests/	|
