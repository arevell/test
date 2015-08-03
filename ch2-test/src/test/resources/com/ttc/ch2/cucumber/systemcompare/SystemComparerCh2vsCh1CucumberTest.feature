@CH2SystemsCompare
Feature: Test compares Ch2 data and CH1 data (class:SystemComparerCh2vsCh1CucumberTest)

Scenario Outline: Test checks that tours stored in Content hub 1 exist in content hub 2.  
				  Test compares percent data for given selling company code.
	Given selling companies: <SellingCompanyCode>
	Given percent for data: <percent>
	
	Given appropriately configured test environment
	When the compare process is invoked
	Then the result is checked and possible differences are presented 
	
Examples:
    |	SellingCompanyCode	|	percent	|

	|	BVUSAS	|	10	|
	|	CHAKLS	|	10	|
	|	CHCANS	|	10	|
	|	CHEUOS	|	10	|
	|	CHJBGS	|	10	|
	|	CHSINS	|	10	|
	|	CHSYDS	|	10	|
	|	CHUKLS	|	10	|
	|	CHUSAS	|	10	|
	|	IVAKLS	|	10	|
	|	IVCANS	|	10	|
	|	IVEUOS	|	10	|
	|	IVGSAS	|	10	|
	|	IVJBGS	|	10	|
	|	IVSINS	|	10	|
	|	IVSYDS	|	10	|
	|	IVUKLS	|	10	|
	|	IVUSAS	|	10	|
	|	TTAKLS	|	10	|
	|	TTCANS	|	10	|
	|	TTEUOS	|	10	|
	|	TTGSAS	|	10	|
	|	TTJBGS	|	10	|
	|	TTOTHS	|	10	|
	|	TTSINS	|	10	|
	|	TTSYDS	|	10	|
	|	TTUKLS	|	10	|
	|	TTUSAS	|	10	|
	
	