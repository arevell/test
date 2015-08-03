@CH2SystemsCompare
Feature: Test compares data between Habs and CH2 using DB API (class:SystemComparerCh2vsHabsCucumberTest)

Scenario Outline: Test compares current Data (Tour departure V3) stored in habs and content hub.  
				  Test compares percent data for given selling company code.
	Given selling companies: <SellingCompanyCode>
	Given percent for data: <percent>
	
	And setting property code as binding for field sellingCompanies.|list|.sellingCompany
	And setting property departureCode as binding for field sellingCompanies.|list|.sellingCompany.departures.|list|.departure
	And setting property code|and|type as binding for field sellingCompanies.|list|.sellingCompany.departures.|list|.departure.associatedProducts.accommodationProducts.|list|.accommodationProduct
	And setting property code as binding for field sellingCompanies.|list|.sellingCompany.departures.|list|.departure.associatedProducts.miscellaneousProducts.|list|.miscellaneousProduct
	And setting property code as binding for field sellingCompanies.|list|.sellingCompany.departures.|list|.departure.tourRules.discounts.|list|.discount
		
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
	
	