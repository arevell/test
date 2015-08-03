<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4"
	xmlns:di="http://www.ttsl.com/MarketVariationDepartureInfo/2010/09/1.1"
	version="1.0"
	> 
	
	<xsl:param name="appName" />	
	<xsl:template match="/">
		<html>
			<head>
				<title>Tour Data</title>
				<link rel="stylesheet" href="{concat($appName, '/css/tourDataView.css')}" />	
			</head>
			<body>
				<xsl:apply-templates select="TourData/ti:TourInfo" />
				<br/>
				<br/>
				<xsl:apply-templates select="TourData/di:MarketVariationDepartureInfo" />				
				<br/>
				<br/>
				<br/>
			</body>
		</html>
	</xsl:template>


	<xsl:template match="ti:TourInfo">
		<div id="TourInfo">
		
		<table class="text_header">
		<tr align="left">
		<th>MV Code:</th> <td><xsl:value-of select="@MVCode" /></td>	
		</tr>
		<tr align="left">
		<th>TourName:</th> <td><xsl:value-of select="ti:TourName" /></td>	
		</tr>
		</table>
		
		<br />
		
		<h2>TourInfo:</h2>
		
		<table class="data" width="100%" cellspacing="0" cellpadding="0" >
		<tr>
		<th align="left" width="20%">BrandCode:</th>
		<td align="left"><xsl:value-of select="@BrandCode" /></td>
		</tr>
		<tr>
		<th align="left">CMSId:</th>
		<td align="left"><xsl:value-of select="@CMSId" /></td>
		</tr>
		<tr>
		<th align="left">CMSTourId:</th>
		<td align="left"><xsl:value-of select="@CMSTourId" /></td>
		</tr>
		<tr>
		<th align="left">CatalogueCode:</th>
		<td align="left"><xsl:value-of select="@CatalogueCode" /></td>
		</tr>
		<tr>
		<th align="left">Duration:</th>
		<td align="left"><xsl:value-of select="@Duration" /></td>
		</tr>
		<tr>
		<th align="left">MVCode:</th>
		<td align="left"><xsl:value-of select="@MVCode" /></td>
		</tr>
		<tr>
		<th align="left">OPCode:</th>
		<td align="left"><xsl:value-of select="@OPCode" /></td>
		</tr>
		<tr>
		<th align="left">TourName:</th>
		<td align="left"><xsl:value-of select="ti:TourName" /></td>
		</tr>
		</table>
		<br />
		<xsl:apply-templates select="ti:Metadata/ti:TourCategories" />
		<br />
		<xsl:apply-templates select="ti:Metadata/ti:SellingCompanies" />
		<br />
		
		<table width="100%">
		<tr valign="top">
		<td width="33%" align="center">	
		<xsl:apply-templates select="ti:ContinentsVisited" />
		</td>
		<td width="33%" align="center">
		<xsl:apply-templates select="ti:CountriesVisited" />
		</td>
		<td align="center">
		<xsl:apply-templates select="ti:LocationsVisited" />
		</td>
		</tr>
		
		</table>
		
		<br />
		<h2>Description:</h2>
		<xsl:value-of select="ti:Description" />
		<br /><br />
		<xsl:apply-templates select="ti:Assets" />
		<br />
		<xsl:apply-templates select="ti:Itinerary" />
		<br />
		<xsl:apply-templates select="ti:WhatsIncluded" />
		<br />
		<xsl:apply-templates select="ti:Highlights" />
		<br />
		<xsl:apply-templates select="ti:AirportTransfers" />
		<br />
		<xsl:apply-templates select="ti:Notes" />
		
		
		</div>
	</xsl:template>	
	
	
	<xsl:template match="ti:Metadata/ti:TourCategories">
	<div id="TourCategories">
		<h2>TourCategories:</h2>	

		<table class="data" width="100%" cellspacing="0" cellpadding="0" >
		<tr>
		<th align="left" width="20%">Name</th>
		<th align="left">CategoryValue</th>
		</tr>
		<xsl:for-each select="ti:TourCategory">
			<tr>
			<td><xsl:value-of select="@Name" /></td>
			<td><xsl:apply-templates select="ti:CategoryValue" /></td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
	</xsl:template>
	
	
	<xsl:template match="ti:CategoryValue">
	<div id="CategoryValue">
		<table class="data" cellspacing="0" cellpadding="0">
		<xsl:for-each select=".">
			<tr>
			<td><xsl:value-of select="@Name" /></td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
	</xsl:template>
	
	
	<xsl:template match="ti:Metadata/ti:SellingCompanies">
		<div id="SellingCompanies">
		<h2>SellingCompanies:</h2>
		
		<table class="data" width="100%" cellspacing="0" cellpadding="0" >
		<tr>
		<th align="left" width="20%">SellingCompanyCode</th>
		<th align="left">Currency</th>
		<th align="left">Brochure</th>
		</tr>

		<xsl:for-each select="ti:SellingCompany">
			<tr>
			<td><xsl:value-of select="@Code" /></td>
			<td><xsl:value-of select="@Currency" /></td>
			<td><xsl:apply-templates select="ti:Brochure" /></td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
	</xsl:template>
	
	
	<xsl:template match="ti:Brochure">
	<div id="Brochure">
	<table class="data" cellspacing="0" cellpadding="0">
	<tr>
		<th align="left">Code</th>
		<th align="left">Name</th>
	</tr>
	<xsl:for-each select=".">
		<tr>
		<td><xsl:value-of select="@Code" /></td>
		<td><xsl:value-of select="@Name" /></td>
		</tr>
	</xsl:for-each>
	</table>
	</div>
	</xsl:template>	


	<xsl:template match="ti:ContinentsVisited">
		<div id="ContinentsVisited">
		<h2>ContinentsVisited:</h2>
		<table class="data" cellspacing="0" cellpadding="0" width="70%">
		<tr>
		<th align="left">Code</th>
		<th align="left">Name</th>
		</tr>

		<xsl:for-each select="ti:Continent">
			<tr>
			<td><xsl:value-of select="@Code" /></td>
			<td><xsl:value-of select="@Name" /></td>			
			</tr>
		</xsl:for-each>
		</table>
		</div>
	</xsl:template>


	<xsl:template match="ti:CountriesVisited">
		<div id="CountriesVisited">
		<h2>CountriesVisited:</h2>
		<table class="data" cellspacing="0" cellpadding="0" width="70%">
		<tr>
		<th align="left">Code</th>
		<th align="left">Name</th>
		<th align="left">Continent</th>
		</tr>

		<xsl:for-each select="ti:Country">
			<tr>
			<td><xsl:value-of select="@Code" /></td>
			<td><xsl:value-of select="@Name" /></td>
			<td><xsl:value-of select="@Continent" /></td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
	</xsl:template>
	<xsl:template match="ti:LocationsVisited">
	    <div id="LocationsVisited">
		<h2>LocationsVisited:</h2>
		<table class="data" cellspacing="0" cellpadding="0" width="70%">
		<tr>
		<th align="left">Country</th>
		<th align="left">Name</th>
		</tr>

		<xsl:for-each select="ti:Location">
			<tr>
			<td><xsl:value-of select="@Country" /></td>
			<td><xsl:value-of select="@Name" /></td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
	</xsl:template>


	<xsl:template match="ti:Assets">
		
		<div id="Assets">
		<h2>Assets: Images:</h2>
		<table class="data" width="100%" cellspacing="0" cellpadding="0">
		<xsl:for-each select="ti:Image">
		<tr valign="top">
		<td colspan="2">
		<img>
		<xsl:attribute name="src"><xsl:value-of select="@Url" /></xsl:attribute>  
		<xsl:attribute name="width"><xsl:value-of select="@Width" /></xsl:attribute>
		<xsl:attribute name="height"><xsl:value-of select="@Height" /></xsl:attribute>
		<xsl:attribute name="alt"><xsl:value-of select="@Url" /></xsl:attribute>
		<xsl:attribute name="title"><xsl:value-of select="@Url" /></xsl:attribute>
		</img>  
 		</td>
  		</tr>
		<tr align="left"><th>Name:</th><td><xsl:value-of select="@Name" /></td></tr>
		<tr align="left"><th>Caption:</th><td><xsl:value-of select="@Caption" /></td></tr>
		<tr align="left"><th>Type:</th><td><xsl:value-of select="@Type" /></td></tr>
		<tr align="left"><th>Width:</th><td><xsl:value-of select="@Width" /></td></tr>
		<tr align="left"><th>Height:</th><td><xsl:value-of select="@Height" /></td></tr>
		<tr align="left"><th>Url:</th><td>
		<a>
		<xsl:attribute name="target">_blank</xsl:attribute>  
		<xsl:attribute name="href"><xsl:value-of select="@Url" /></xsl:attribute>  
		<xsl:value-of select="@Url" />
		</a>
		</td></tr>
		<tr align="left" height="10"> <th></th><td> </td></tr>
		</xsl:for-each>
		
		</table>
		</div>
		
		
		
	</xsl:template>


	<xsl:template match="ti:Itinerary">
		<div id="Itinerary">
		<h2>ItinerarySegments:</h2>
		<table class="data" width="100%" cellspacing="0" cellpadding="0">

		<xsl:for-each select="ti:ItinerarySegment">
			<tr align="left">
			<th width="5%">StartDay:</th>
			<td width="5%"><xsl:value-of select="@StartDay" /></td>
			<th width="5%">Duration:</th>
			<td width="5%"><xsl:value-of select="@Duration" /></td>
			<th>Title:</th>
			<td><xsl:value-of select="ti:Title" /></td>
			</tr>
			
			<tr align="left">
			<th></th>
			<td></td>
			<th></th>
			<td></td>
			<th>Text:</th>
			<td><xsl:copy-of select="ti:Text" /></td>
			</tr>
			
			<tr align="left">
			<th></th>
			<td></td>
			<th></th>
			<td></td>
			<th>Accommodation:</th>
			<td><xsl:value-of select="ti:Accommodation" /> <br/><br/></td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
	</xsl:template>
	
	

	<xsl:template match="ti:WhatsIncluded">
		<div id="WhatsIncluded">
		<h2>WhatsIncluded:</h2>
		<xsl:apply-templates select="ti:Section" />
		</div>
	</xsl:template>
	
	<xsl:template match="ti:Section">
		<div id="Section">
		<table class="data" width="100%" cellspacing="0" cellpadding="0">
		<xsl:for-each select=".">
			<tr align="left" valign="top">
			<th width="5%">Title:</th>
			<td><xsl:value-of select="ti:Title" /></td>
			</tr>
			
			<tr align="left" valign="top">
			<th width="5%">Text:</th>
			<td>
			<xsl:apply-templates select="ti:Text" />
			</td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
	</xsl:template>
	
	
	<xsl:template match="ti:Text">
	<div id="Text">
		<table class="data" width="100%" cellspacing="0" cellpadding="0">
		<xsl:for-each select=".">
			<tr>
			<td><xsl:copy-of select="." /></td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
	</xsl:template>
	
	


	<xsl:template match="ti:Highlights">
		<div id="Highlights">
		<h2>Highlights:</h2>
		<xsl:apply-templates select="ti:Section" />
		</div>
	</xsl:template>


	<xsl:template match="ti:AirportTransfers">
		<div id="AirportTransfers">
		<h2>AirportTransfers:</h2>
		<xsl:apply-templates select="ti:Section" />
		</div>
	</xsl:template>


	<xsl:template match="ti:Notes">
		<div id="Notes">
		<h2>Notes:</h2>
		<xsl:apply-templates select="ti:Section" />
		</div>
	</xsl:template>

	<xsl:template match="di:MarketVariationDepartureInfo">
		<div id="MarketVariationDepartureInfo">
		<h2 class="version2">MarketVariationDepartureInfo:</h2>
		<table class="reference" cellspacing="0" cellpadding="0" border="1">
		<tr>
		
		<th align="left">BrochureCode</th>
		<th align="left">MarketVariationCode</th>
		<th align="left">MarketVariationId</th>
		<th align="left">OperatingProductCode</th>
		<th align="left">OperatingProductId</th>
		<th align="left">BookableOnline</th>
		<th align="left">TourPackage</th>
		
		</tr>
		
		<tr>
		<td><xsl:value-of select="@BrochureCode" /></td>
		<td><xsl:value-of select="@MarketVariationCode" /></td>
		<td><xsl:value-of select="@MarketVariationId" /></td>
		<td><xsl:value-of select="@OperatingProductCode" /></td>
		<td><xsl:value-of select="@OperatingProductId" /></td>
		<td><xsl:value-of select="@BookableOnline" /></td>
		<td><xsl:value-of select="@TourPackage" /></td>
		</tr>
		</table>
		
		<br />
		<xsl:apply-templates select="di:MarketVariationPricings" />
		<br />
		<xsl:apply-templates select="di:Departures" />
		</div>
	</xsl:template>


	<xsl:template match="di:MarketVariationPricings">
		
		<div id="MarketVariationPricings">
		<h2 class="version2">MarketVariationPricings:</h2>
		<table class="reference" cellspacing="0" cellpadding="0" border="1">
		<tr>
		<th align="left">Currency</th>
		<th align="left">PriceIsIndicative</th>
		<th align="left">FoodFundPrice</th>
		<th align="left">ChildPortTax</th>
		<th align="left">AirPriceIncluded</th>
		<th align="left">AdultPortTax</th>
		<th align="left">SellingCompanyCode</th>
		</tr>

		<xsl:for-each select="di:MarketVariationPricing">
			<tr>
			<td><xsl:value-of select="@Currency" /></td>
			<td><xsl:value-of select="@PriceIsIndicative" /></td>
			<td><xsl:value-of select="@FoodFundPrice" /></td>
			<td><xsl:value-of select="@ChildPortTax" /></td>
			<td><xsl:value-of select="@AirPriceIncluded" /></td>
			<td><xsl:value-of select="@AdultPortTax" /></td>
			<td><xsl:value-of select="@SellingCompanyCode" /></td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
		
		
	</xsl:template>


	<xsl:template match="di:Departures">
		
		<div id="Departures">
		<h2 class="version2">Departures:</h2>
		
		
		<xsl:for-each select="di:Departure">

			<h3 class="version2"> Departure: <xsl:value-of select="position()"/> </h3>
			
			<table class="reference" cellspacing="0" cellpadding="0" border="1">
			<tr>
			<th align="left">StartDate</th>
			<th align="left">EndDate</th>
			<th align="left">DepartureCode</th>
			<th align="left">DefiniteDeparture</th>
			<th align="left">AvailabilityStatus</th>
			</tr>
			<tr>
			<td><xsl:value-of select="@StartDate" /></td>
			<td><xsl:value-of select="@EndDate" /></td>
			<td><xsl:value-of select="@DepartureCode" /></td>
			<td><xsl:value-of select="@DefiniteDeparture" /></td>
			<td><xsl:value-of select="@AvailabilityStatus" /></td>
			</tr>
			</table>
			<br />
			<xsl:apply-templates select="di:DeparturePricings" />
			
		</xsl:for-each>
		
		
		</div>
		

	</xsl:template>



	<xsl:template match="di:DeparturePricings">
		<div id="DeparturePricings">
		<table class="reference" cellspacing="0" cellpadding="0" border="1" align="left">
		<tr>
		<th align="left">Currency</th>
		<th align="left">TeenagerDiscount</th>
		<th align="left">LandOnlyReduction</th>
		<th align="left">ChildPrice</th>
		<th align="left">AdultSingleRoomPriceCombined</th>
		<th align="left">AdultSingleRoomPrice</th>
		<th align="left">AdultTwinRoomPriceCombined</th>
		<th align="left">AdultTwinRoomPrice</th>
		<th align="left">AdultTripleRoomPriceCombined</th>
		<th align="left">AdultTripleRoomPrice</th>
		<th align="left">AdultQuadRoomPriceCombined</th>
		<th align="left">AdultQuadRoomPrice</th>
		<th align="left">SellingCompanyCode</th>
		<th align="left">MandatoryProducts</th>
		<th align="left">Surcharges</th>
		</tr>

		<xsl:for-each select="di:DeparturePricing">
			<tr>
			<td><xsl:value-of select="@Currency" /></td>
			<td><xsl:value-of select="@TeenagerDiscount" /></td>
			<td><xsl:value-of select="@LandOnlyReduction" /></td>
			<td><xsl:value-of select="@ChildPrice" /></td>
			<td><xsl:value-of select="@AdultSingleRoomPriceCombined" /></td>
			<td><xsl:value-of select="@AdultSingleRoomPrice" /></td>
			<td><xsl:value-of select="@AdultTwinRoomPriceCombined" /></td>
			<td><xsl:value-of select="@AdultTwinRoomPrice" /></td>
			<td><xsl:value-of select="@AdultTripleRoomPriceCombined" /></td>
			<td><xsl:value-of select="@AdultTripleRoomPrice" /></td>
			<td><xsl:value-of select="@AdultQuadRoomPriceCombined" /></td>
			<td><xsl:value-of select="@AdultQuadRoomPrice" /></td>
			<td><xsl:value-of select="@SellingCompanyCode" /></td>
			<td><xsl:apply-templates select="di:MandatoryProducts" /></td>
			<td><xsl:apply-templates select="di:Surcharges" /></td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
	</xsl:template>
	
	<xsl:template match="di:MandatoryProducts">
		<xsl:if test="./*">
		<div id="MandatoryProducts">
		<table class="reference" cellspacing="0" cellpadding="0" border="1">
		<tr>
		<th align="left">AdultPrice</th>
		<th align="left">ChildPrice</th>
		<th align="left">Description</th>
		<th align="left">ProductId</th>
		<th align="left">ProductType</th>
		</tr>

		<xsl:for-each select="di:MandatoryProduct">
			<tr>
			<td><xsl:value-of select="@AdultPrice" /></td>
			<td><xsl:value-of select="@ChildPrice" /></td>
			<td><xsl:value-of select="@Description" /></td>
			<td><xsl:value-of select="@ProductId" /></td>
			<td><xsl:value-of select="@ProductType" /></td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
		</xsl:if>
	</xsl:template>
	
	<xsl:template match="di:Surcharges">
		<xsl:if test="./*">
		<div id="Surcharges">
		<table class="reference" cellspacing="0" cellpadding="0" border="1">
		<tr>
		<th align="left">SurchargeType</th>
		<th align="left">SurchargeDescription</th>
		<th align="left">SurchargeAmount</th>
		<th align="left">AmountIsAbsolute</th>
		</tr>

		<xsl:for-each select="di:Surcharge">
			<tr>
			<td><xsl:value-of select="@SurchargeType" /></td>
			<td><xsl:value-of select="@SurchargeDescription" /></td>
			<td><xsl:value-of select="@SurchargeAmount" /></td>
			<td><xsl:value-of select="@AmountIsAbsolute" /></td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
		</xsl:if>
</xsl:template>

</xsl:stylesheet>

