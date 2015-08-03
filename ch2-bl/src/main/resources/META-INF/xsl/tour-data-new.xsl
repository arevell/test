<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:ti="http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0"
	xmlns:di="http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4"
	version="1.0"
	> 	
	<xsl:param name="appName" />	
	<xsl:param name="tourInfoDataSource" />
	<xsl:template match="/">
		<html>			
			<head>
				<title>Tour Data</title>
				<meta name="expires" content="0" />
				<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE" />
				<meta http-equiv="Cache-control" content="no-cache" />
				<!-- <link rel="stylesheet" href="/ch2-ui/css/tourDataView.css" /> -->
				<link rel="stylesheet" href="{concat($appName, '/css/tourDataView.css')}" />				
			</head>
			<body>
				<xsl:apply-templates select="TourData/ti:TourInfo" />
				<br/>
				<br/>				
				 <xsl:apply-templates select="TourData/di:TourDepartures" />				
				<br/>
				<br/>
				<br/>
			</body>
		</html>
	</xsl:template>


	<xsl:template match="ti:TourInfo">
		<div id="TourInfo">				
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
			<th align="left">Duration:</th>
			<td align="left"><xsl:value-of select="@Duration" /></td>
			</tr>
			<tr>
			<th align="left">TourCode:</th>
			<td align="left"><xsl:value-of select="@TourCode" /></td>
			</tr>
			<tr>
			<th align="left">TourName:</th>
			<td align="left"><xsl:value-of select="ti:TourName" /></td>
			</tr>
			<tr>
				<th align="left">CataloguedCode:</th>
				<td align="left"><xsl:value-of select="ti:CataloguedTour/ti:Code" /></td>
			</tr>
			<tr>
				<th align="left">CataloguedName:</th>
				<td align="left"><xsl:value-of select="ti:CataloguedTour/ti:Name" /></td>
			</tr>
			<tr>
				<th align="left">Data source:</th>
				<td align="left"><xsl:value-of select="$tourInfoDataSource" /></td>
			</tr>
			</table> 
			<br />
			<xsl:apply-templates select="ti:SellingCompanies" /><br />
			
			<xsl:apply-templates select="ti:TourVariationDefiners" /><br />
			
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
			<xsl:value-of select="ti:Description" /><br /><br />			
			<xsl:apply-templates select="ti:Assets" /><br />			
			<xsl:apply-templates select="ti:Itinerary" /><br />			
			<xsl:apply-templates select="ti:WhatsIncluded" /><br />
			<xsl:apply-templates select="ti:Highlights" /><br />			
			<xsl:apply-templates select="ti:AirportTransfers" /><br />
			<xsl:apply-templates select="ti:Notes" />			
		</div>
	</xsl:template>	
	
	<!--  Selling company start -->	
	<xsl:template match="ti:SellingCompanies">
		<div id="SellingCompanies">
			<h2>SellingCompanies:</h2>		
			<xsl:for-each select="ti:SellingCompany">				
				<table class="data" width="100%" cellspacing="0" cellpadding="0" >
					<tr>
						<th align="left" width="20%">SellingCompanyCode:</th>
						<td><xsl:value-of select="@Code" /></td>
					</tr>
				</table>
				<table class="data_border" width="100%" cellspacing="0" cellpadding="0" >
					<tr>			
						<th align="left" width="5%" >Currency Code</th>
						<th align="left" width="20%" >Brochure</th>			
						<th align="left" width="20%" >TermsAndConditionsLinkURL</th>
						<th align="left" width="20%" >TourCategories</th>
						<th align="left" width="35%" >MarketingFlags</th>				
					</tr>
					<tr>
						<td ><xsl:value-of select="@CurrencyCode" /></td>
						<td ><xsl:apply-templates select="ti:Brochure" /></td> 				
						<td ><xsl:value-of select="ti:TermsAndConditionsLinkURL" /></td>
						<td ><xsl:apply-templates select="ti:TourCategories" /></td>
						<td ><xsl:apply-templates select="ti:MarketingFlags" /></td>
					</tr>		
				</table>
				<br/>
			</xsl:for-each>
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
						<td><xsl:value-of select="ti:Code" /></td>
						<td><xsl:value-of select="ti:Name" /></td>
					</tr>
				</xsl:for-each>
			</table>
		</div>
	</xsl:template>	
	
	<xsl:template match="ti:TourCategories">
		<div id="TourCategories">			
			<table class="data" width="100%" cellspacing="0" cellpadding="0" >
				<tr>
					<th align="left">CategoryName</th>
					<th align="left">CategoryValue</th>
				</tr>
				<xsl:for-each select="ti:TourCategory">
					<tr>
						<td><xsl:value-of select="@Name" /></td>
						<td>
							<xsl:for-each select="ti:CategoryValue">
								<xsl:value-of select="." /><br/>								
							</xsl:for-each>							
						</td>
					</tr>
				</xsl:for-each>
			</table>
		</div>
	</xsl:template>
	
	<xsl:template match="ti:MarketingFlags">
		<div id="MarketingFlags">			
			<table class="data" width="100%" cellspacing="0" cellpadding="0" >
				<tr>
					<th align="left" width="20%">Marketing Priority:</th>
					<td align="left"><xsl:value-of select="ti:MarketingPriority" /></td>
				</tr>	
				<tr>
					<th align="left" width="20%">MostPopular:</th>
					<td align="left"><xsl:value-of select="ti:MostPopular" /></td>
				</tr>
				<tr>
					<th align="left" colspan="2">KeywordsPhrasesText:</th>					
				</tr>
				<xsl:for-each select="ti:KeywordsPhrases">
					<tr>
						<td><xsl:apply-templates select="ti:Text" /></td>
					</tr>
				</xsl:for-each>
			</table> 			
		</div>
	</xsl:template>		
<!--  Selling company end -->	
	<!-- TourVariationDefiners start-->
	<xsl:template match="ti:TourVariationDefiners">
		<div id="TourVariationDefiners">
			<h2>Tour Variation Definers:</h2>
			<table class="data_border" width="100%" cellspacing="0" cellpadding="0" >
				<tr>
					<th align="left"  colspan="3">OperatingProduct:</th>
				</tr>
				<tr>
					<th align="left" width="20%"></th>
					<th align="left" width="20%" >Code:</th>
					<td align="left"><xsl:value-of select="ti:OperatingProduct/ti:Code" /></td>
				</tr>
				<tr>
					<th align="left" width="20%"></th>
					<th align="left" width="20%" >ContractingSeason:</th>
					<td align="left"><xsl:value-of select="ti:OperatingProduct/ti:ContractingSeason" /></td>
				</tr>
				<tr>
					<th align="left" width="20%"></th>
					<th align="left" width="20%" >Category:</th>
					<td align="left"><xsl:value-of select="ti:OperatingProduct/ti:Category" /></td>
				</tr>
				<tr>
					<th align="left" width="20%"></th>
					<th align="left" width="20%" >Classification:</th>
					<td align="left"><xsl:value-of select="ti:OperatingProduct/ti:Classification" /></td>
				</tr>
				<tr>
					<th align="left" width="20%"></th>
					<th align="left" width="20%" >StandardName:</th>
					<td align="left"><xsl:value-of select="ti:OperatingProduct/ti:StandardName" /></td>
				</tr>
				<tr>
					<th align="left"  colspan="3">IncludedSubProducts:</th>
				</tr>
				<tr>
					<th align="left" width="20%"></th>
					<th align="left"  colspan="2">IncludedSubProduct:</th>
				</tr>
				<tr>
					<th align="left" width="20%"></th>
					<th align="left" width="20%" ></th>
					<td align="left">
						<table class="data" width="100%" cellspacing="0" cellpadding="0" >
							<tr>
								<th align="left">Code</th>
								<th align="left">Name</th>
								<th align="left">Category</th>
								<th align="left">Service Type</th>
							</tr>
							<xsl:for-each select="ti:IncludedSubProducts/ti:IncludedSubProduct">
							<tr>
								<td><xsl:value-of  select="ti:Code" /></td>
								<td><xsl:value-of  select="ti:Name" /></td>
								<td><xsl:value-of  select="ti:Category" /></td>
								<td><xsl:value-of  select="ti:ServiceType" /></td>
							</tr>
						</xsl:for-each>
						</table>
					</td>
				</tr>
				<tr>
					<th align="left"  colspan="3">Room Types:</th>
				</tr>
				<tr>
					<th align="left" width="20%"></th>
					<th align="left" colspan="2">Room Type:</th>
				</tr>
				<tr>
					<th align="left" width="20%"></th>
					<th align="left" width="20%" ></th>
					<td align="left">
						<table class="data" width="100%" cellspacing="0" cellpadding="0" >
							<tr>
								<th align="left">Type</th>
								<th align="left">Sellable</th>								
							</tr>
							<xsl:for-each select="ti:RoomTypes/ti:RoomType">
								<tr>
									<td><xsl:value-of  select="@Type" /></td>
									<td><xsl:value-of  select="@Sellable" /></td>									
								</tr>
							</xsl:for-each>
						</table>
					</td>
				</tr>
				<tr>
					<th align="left">StartCity</th>
					<th align="left">Name</th>
					<td align="left"><xsl:value-of  select="ti:StartCity/ti:Name" /></td>
				</tr>
				<tr>
					<th></th>
					<th align="left">Airports</th>
					<th align="left">Airport</th>
				</tr>
				<tr>
					<th></th>
					<th></th>
					<td>
						<table class="data" width="100%" cellspacing="0" cellpadding="0">
						<tr>
							<th align="left">IATACode</th>
							<th align="left">Name</th>
							<th align="left">City</th>		
							<th align="left">Region</th>		
							<th align="left">Country</th>
							<th align="left">DefaultForCity</th>							
						</tr>
						<xsl:for-each select="ti:StartCity/ti:Airports/ti:Airport">
							<tr>
								<td><xsl:value-of  select="ti:IATACode" /></td>
								<td><xsl:value-of  select="ti:Name" /></td>	
								<td><xsl:value-of  select="ti:City" /></td>	
								<td><xsl:value-of  select="ti:Region" /></td>	
								<td><xsl:value-of select="ti:Country" /></td>	
								<td><xsl:value-of  select="ti:DefaultForCity" /></td>									
							</tr>
						</xsl:for-each>
						</table>
					</td>
				</tr>
				<tr>
					<th align="left">EndCity</th>
					<th align="left">Name</th>
					<td align="left"><xsl:value-of  select="ti:EndCity/ti:Name" /></td>
				</tr>
				<tr>
					<th></th>
					<th align="left">Airports</th>
					<th align="left">Airport</th>
				</tr>
				<tr>
					<th></th>
					<th></th>
					<td>
						<table class="data" width="100%" cellspacing="0" cellpadding="0">
							<tr>
								<th align="left">IATACode</th>
								<th align="left">Name</th>
								<th align="left">City</th>		
								<th align="left">Region</th>		
								<th align="left">Country</th>
								<th align="left">DefaultForCity</th>							
							</tr>
							<xsl:for-each select="ti:EndCity/ti:Airports/ti:Airport">
								<tr>
									<td><xsl:value-of  select="ti:IATACode" /></td>
									<td><xsl:value-of  select="ti:Name" /></td>	
									<td><xsl:value-of  select="ti:City" /></td>	
									<td><xsl:value-of  select="ti:Region" /></td>	
									<td><xsl:value-of  select="ti:Country" /></td>	
									<td><xsl:value-of  select="ti:DefaultForCity" /></td>									
								</tr>
							</xsl:for-each>
						</table>
					</td>
				</tr>
				<tr>
					<th align="left" >IsTourPackage:</th>
					<td align="left" colspan="2" ><xsl:value-of  select="ti:IsTourPackage" /></td>
				</tr>
				<tr>
					<th align="left" >IncludedCruiseCabinType:</th>
					<td align="left" colspan="2" ><xsl:value-of  select="ti:IncludedCruiseCabinType" /></td>
				</tr>
				<tr>
					<th align="left" colspan="3" >AdditionalDefiners</th>					
				</tr>
				<tr>
					<th></th>
					<th>Section</th>
					<th align="left" colspan="2" ><xsl:apply-templates select="ti:AdditionalDefiners/ti:Section" /></th>					
				</tr>				
			</table>
		</div>
	</xsl:template>
	<!-- TourVariationDefiners end-->

	<xsl:template match="ti:ContinentsVisited">
		<div id="ContinentsVisited">
		<h2>ContinentsVisited:</h2>
			<table class="data_border" cellspacing="0" cellpadding="0" width="70%">
				<tr>
					<th align="left">Code</th>
					<th align="left">Name</th>
				</tr>
				<xsl:for-each select="ti:Continent">
					<tr>
					<td><xsl:value-of select="ti:Code" /></td>
					<td><xsl:value-of select="ti:Name" /></td>			
					</tr>
				</xsl:for-each>
			</table>
		</div>
	</xsl:template>

	<xsl:template match="ti:CountriesVisited">
		<div id="CountriesVisited">
		<h2>CountriesVisited:</h2>
		<table class="data_border" cellspacing="0" cellpadding="0" width="70%">
		<tr>
			<th align="left">Code</th>
			<th align="left">Name</th>
			<th align="left">Continent</th>
		</tr>
		<xsl:for-each select="ti:Country">
			<tr>
				<td><xsl:value-of select="ti:Code" /></td>
				<td><xsl:value-of select="ti:Name" /></td>
				<td><xsl:value-of select="ti:ContinentCode" /></td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
	</xsl:template>

	<xsl:template match="ti:LocationsVisited">
	    <div id="LocationsVisited">
		<h2>LocationsVisited:</h2>
	    	<table class="data_border" cellspacing="0" cellpadding="0" width="70%">
		<tr>
		<th align="left">Country</th>
		<th align="left">Name</th>
		</tr>
		<xsl:for-each select="ti:Location">
			<tr>
				<td><xsl:value-of select="@CountryCode" /></td>
				<td><xsl:value-of select="@Name" /></td>
			</tr>
		</xsl:for-each>
		</table>
		</div>
	</xsl:template>

	<xsl:template match="ti:Assets">		
		<div id="Assets">
		<h2>Assets:</h2>
		<h1>Images</h1>
		<table class="data" width="100%" cellspacing="0" cellpadding="0">
			<xsl:for-each select="ti:Images/ti:Image">
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
				<tr align="left"><th width="150px">Name:</th><td><xsl:value-of select="@Name" /></td></tr>
				<tr align="left"><th width="150px">Caption:</th><td><xsl:value-of select="@Caption" /></td></tr>
				<tr align="left"><th width="150px">Type:</th><td><xsl:value-of select="@Type" /></td></tr>
				<tr align="left"><th width="150px">Width:</th><td><xsl:value-of select="@Width" /></td></tr>
				<tr align="left"><th width="150px">Height:</th><td><xsl:value-of select="@Height" /></td></tr>
				<tr align="left"><th width="150px">Url:</th>
						<td>
									<a>
										<xsl:attribute name="target">_blank</xsl:attribute>  
										<xsl:attribute name="href"><xsl:value-of select="@Url" /></xsl:attribute>  
										<xsl:value-of select="@Url" />
									</a>
						</td>
					</tr>
					<tr align="left" height="10"> 
						<th></th><td> </td>
					</tr>
			</xsl:for-each>		
			</table>			
			<h1>Videos</h1>
			<table class="data" width="100%" cellspacing="0" cellpadding="0">
				<xsl:for-each select="ti:Videos/ti:Video">
					<tr align="left"><th width="150px">Name:</th><td><xsl:value-of select="@Name" /></td></tr>
					<tr align="left"><th width="150px">Caption:</th><td><xsl:value-of select="@Caption" /></td></tr>
					<tr align="left"><th width="150px">AspectRatio:</th><td><xsl:value-of select="@AspectRatio" /></td></tr>
					<tr align="left"><th width="150px">Type:</th><td><xsl:value-of select="@Type" /></td></tr>					
					<tr align="left"><th width="150px">Url:</th>
						<td>
							<a>
								<xsl:attribute name="target">_blank</xsl:attribute>  
								<xsl:attribute name="href"><xsl:value-of select="@Url" /></xsl:attribute>  
								<xsl:value-of select="@Url" />
							</a>
						</td>
					</tr>
					<tr align="left" height="10"> 
						<th></th><td> </td>
					</tr>
				</xsl:for-each>		
			</table>
		</div>
		
		
		
		
	</xsl:template>

	<xsl:template match="ti:Itinerary">
		<div id="Itinerary">
		<h2>ItinerarySegments:</h2>
		<table class="data_border" width="100%" cellspacing="0" cellpadding="0">
		<xsl:for-each select="ti:ItinerarySegment">
			<tr align="left">
				<th width="5%">StartDay:</th>
				<td width="5%"><xsl:value-of select="@StartDay" /></td>
				<th width="5%">Duration:</th>
				<td width="5%"><xsl:value-of select="@Duration" /></td>
				<th width="20px">Title:</th>
				<td><xsl:value-of select="ti:Title" /></td>
			</tr>			
			<tr align="left">
				<th colspan="4"></th>
				<th align="left" width="20px">Text:</th>				
				<td align="left"><xsl:apply-templates select="ti:Text" />
					<!--<xsl:copy-of select="ti:Text" />--></td>
			</tr>		
			<tr align="left">
				<th colspan="4"></th>
				<th align="left" width="20px">Accommodation:</th>
				<td align="left"><xsl:value-of select="ti:Accommodation" /> <br/><br/></td>
			</tr>
			<tr>
				<th colspan="4"></th>
				<th align="left" width="20px" colspan="2">Meals:</th>				
			</tr>
			<tr>
				<th colspan="5"></th>
				<td>
					<table>
					<tr>
						<th>Meal type</th>
						<th>Number</th>
					</tr>
					<xsl:for-each select="ti:Meals/ti:Meal">
						<tr>
							<td><xsl:value-of select="ti:Type" /></td>
							<td><xsl:value-of select="ti:Number" /></td>
						</tr>
					</xsl:for-each>
					</table>
				</td>
			</tr>					
			<tr>
				<th colspan="4"></th>
				<th align="left" width="20px" colspan="2">LocationsVisited:</th>				
			</tr>
			<tr>
				<th colspan="5"></th>
				<td>
					<table>
						<tr>
							<th>LocationCountryCode</th>
							<th>Name</th>
						</tr>
						<xsl:for-each select="ti:LocationsVisited/ti:Location">
							<tr>
								<td><xsl:value-of select="@CountryCode" /></td>
								<td><xsl:value-of select="@Name" /></td>
							</tr>
						</xsl:for-each>
					</table>
				</td>
			</tr>	
			<tr>
				<th colspan="4"></th>
				<th align="left" width="20px" colspan="2">OptionalExtras:</th>				
			</tr>
			<tr>
				<th colspan="5"></th>
				<td>
					<table>
						<tr>
							<th align="left">ExtraCode</th>
							<th colspan="4" align="left">Sections</th>
						</tr>
						<xsl:for-each select="ti:OptionalExtras/ti:Extra">
							<tr>
								<td><xsl:value-of select="ti:Code" /></td>
								<td colspan="4">
									<xsl:apply-templates select="ti:Section" />								
								</td>
							</tr>
						</xsl:for-each>
						<tr>
							<th colspan="5" align="left">Price</th>
						</tr>
						<tr>
							<th align="left">PassengerType</th>
							<th align="left">CurrencyCode</th>
							<th align="left">AmountFrom</th>
							<th align="left">AmountTo</th>
							<th align="left">Notes</th>
						</tr>
						<tr>							
							<xsl:for-each select="ti:OptionalExtras/ti:Extra">
								<xsl:for-each select="ti:Price">	
								<tr>
									<td><xsl:value-of select="ti:PassengerType" /></td>
									<td><xsl:value-of select="ti:CurrencyCode" /></td>
									<td><xsl:value-of select="ti:AmountFrom" /></td>
									<td><xsl:value-of select="ti:AmountTo" /></td>
									<td><xsl:apply-templates select="ti:Notes/ti:Section" /></td>
								</tr>			
								</xsl:for-each>
							</xsl:for-each>
						</tr>
					</table>					
				</td>
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
	<!-- tour info part end -->
	
	<!-- tour departure  part start -->
	
	<xsl:template match="di:TourDepartures">
		<div id="TourDeparturesData">
			<h2 class="version2">Tour Departures Data:</h2>
				<table class="reference" cellspacing="0" cellpadding="0" border="1">
				<tr>		
					<th align="left">TourCode:</th>
					<td align="left"><xsl:value-of select="@TourCode" /></td>
					<th align="left">OperatingProductCode:</th>
					<td align="left"><xsl:value-of select="di:OperatingProductCode" /></td>						
				</tr>		
				<tr>
					<th colspan="2"></th>
					<th align="left">OnlineBookable:</th>
					<td align="left"><xsl:value-of select="@OnlineBookable" /></td>											
				</tr>
				</table>		
			<br />
			<xsl:apply-templates select="di:SellingCompanies" />
			<!--<xsl:apply-templates select="di:MarketVariationPricings" /><br />
			<xsl:apply-templates select="di:Departures" />-->
		</div>
	</xsl:template>

	<xsl:template match="di:SellingCompanies">
		<div id="SellingCompany">			
			<xsl:for-each select="di:SellingCompany">
				<table class="reference" cellspacing="0" cellpadding="0" border="1">
					<tr>		
						<th align="left">SellingCompanyCode:</th>
						<td align="left"><xsl:value-of select="@Code" /></td>
						<th align="left">InventoryBrochureCode:</th>
						<td align="left"><xsl:value-of select="@InventoryBrochureCode" /></td>						
					</tr>
				</table>
				<xsl:apply-templates select="di:Departures" />
			</xsl:for-each>
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
				<table>
					<tr>
						<td><h3 class="version2"> Departure: <xsl:value-of select="position()"/> </h3></td>
					</tr>
					<tr>
						<td>
							<table class="reference" cellspacing="0" cellpadding="0" border="1">
								<tr>
									<th align="left">DepartureCode</th>
									<th align="left">StartDateTime</th>
									<th align="left">EndDateTime</th>
									<th align="left">AvailabilityStatus</th>
									<th align="left">DefiniteDeparture</th>
									<th align="left">DateRangeIncludesTravelTime</th>
									<th align="left">CanSearchForFlights</th>					
									<th align="left">IsEligibleForFrequentTravellerDiscount</th>
									<th align="left">Notes</th>
								</tr>
								<tr>
									<td><xsl:value-of select="di:DepartureCode" /></td>
									<td><xsl:value-of select="di:StartDateTime" /></td>
									<td><xsl:value-of select="di:EndDateTime" /></td>
									<td><xsl:value-of select="di:AvailabilityStatus" /></td>
									<td><xsl:value-of select="di:DefiniteDeparture" /></td>
									
									<td><xsl:value-of select="di:DateRangeIncludesTravelTime" /></td>
									<td><xsl:value-of select="di:TourRules/di:CanSearchForFlights" /></td>
									<td><xsl:value-of select="di:TourRules/di:IsEligibleForFrequentTravellerDiscount" /></td>
									<td><xsl:copy-of select="di:Notes" /></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr><td><h3 class="version2">Departure Pricings:</h3></td></tr>
					<tr>
						<td>
							<table class="reference" cellspacing="0" cellpadding="0" border="1" align="left">
								<tr>
									<th align="left">Currency</th>
									<th align="left">PriceIsIndicative</th>
								</tr>
								<tr>
									<td><xsl:value-of select="../../@CurrencyCode" /></td>
									<td><xsl:value-of select="di:TourRules/di:PriceIsIndicative" /></td>						
								</tr>
							</table>
						</td>						
					</tr>
					<tr>
						<td>
							<table class="reference" cellspacing="0" cellpadding="0" border="1" align="left">
								<tr>
									<th align="left" rowspan="2">SellableRoomType</th>
									<th align="left" rowspan="2">AdultBasePrice</th>
									<th align="left" rowspan="2">AdultCombinedPrice</th>
									<th align="left" rowspan="2">ChildBasePrice</th>
									<th align="left" rowspan="2">ChildCombinedPrice</th>
									<th align="left" rowspan="1" colspan="5">Room OccupancyRule</th>
								</tr>
								<tr>
									<th align="left" rowspan="1">MaximumAdults</th>
									<th align="left" rowspan="1">MaximumPassengers</th>									
									<th align="left" rowspan="1">MinimumAdults</th>									
									<th align="left" rowspan="1">MinimumPassengers</th>
									<th align="left" rowspan="1">MinimumPayingAdults</th>									
								</tr>
								<xsl:for-each select="di:TourRules/di:Rooms/di:Room">
								<tr>									
									<td><xsl:value-of select="di:Type" /></td>
									<td><xsl:value-of select="di:Price/di:Adult/di:Base" /></td>
									<td><xsl:value-of select="di:Price/di:Adult/di:Combined" /></td>	
									<td><xsl:value-of select="di:Price/di:Child/di:Base" /></td>
									<td><xsl:value-of select="di:Price/di:Child/di:Combined" /></td>
									
									<td><xsl:value-of select="di:OccupancyRule/di:MaximumAdults" /></td>
									<td><xsl:value-of select="di:OccupancyRule/di:MaximumPassengers" /></td>
									<td><xsl:value-of select="di:OccupancyRule/di:MinimumAdults" /></td>
									<td><xsl:value-of select="di:OccupancyRule/di:MinimumPassengers" /></td>
									<td><xsl:value-of select="di:OccupancyRule/di:MinimumPayingAdults" /></td>
								</tr>
								</xsl:for-each>
							</table>
							<table class="reference" cellspacing="0" cellpadding="0" border="1" align="left" style="margin-left:10px">
								<tr>
									<th  align="left">Type</th>
									<th  align="left">AgeFrom</th>
									<th  align="left">AgeTo</th>
								</tr>
								<xsl:for-each select="di:TourRules/di:Passengers/di:Passenger">
								<tr>
									<td><xsl:value-of select="di:Type" /></td>
									<td><xsl:value-of select="di:AgeFrom" /></td>
									<td><xsl:value-of select="di:AgeTo" /></td>
								</tr>
								</xsl:for-each>
							</table>
						</td>						
					</tr>
					<tr><td><h3 class="version2">CombinedIncludedCharges</h3></td></tr>
					<tr>
						<td>
							<table class="reference" cellspacing="0" cellpadding="0" border="1" align="left">
								<tr>
									<th align="left" colspan="5">Mandatory Miscellaneous Products</th>									
								</tr>
								<tr>
									<th  align="left" >Mandatory Miscellaneous ProductCode</th>
									<th  align="left" >Name</th>
									<th  align="left" >Category</th>
									<th  align="left" >AdultPrice</th>
									<th  align="left" >ChildPrice</th>
								</tr>
								<xsl:for-each select="di:TourRules/di:CombinedIncludedCharges/di:MandatoryMiscellaneousProducts/di:MiscellaneousProduct">
								<tr>
									<td><xsl:value-of select="di:Code" /></td>
									<td><xsl:value-of select="di:Name" /></td>
									<td><xsl:value-of select="di:Category" /></td>
									<td><xsl:value-of select="di:Price/di:Adult" /></td>
									<td><xsl:value-of select="di:Price/di:Child" /></td>
								</tr>
								</xsl:for-each>
							</table>
							<table style="margin-left:10px" class="reference" cellspacing="0" cellpadding="0" border="1" align="left">
								<tr>
									<th align="left" rowspan="1">Surcharge / PortTax / FoodFund	</th>
									<th align="left" rowspan="1">AdultPrice</th>
									<th align="left" rowspan="1">ChildPrice</th>
								</tr>
								<xsl:for-each select="di:TourRules/di:CombinedIncludedCharges/di:Surcharges/di:Surcharge">
								<tr>
									<td>Surcharge</td>
									<td><xsl:value-of select="di:Price/di:Adult" /></td>
									<td><xsl:value-of select="di:Price/di:Child" /></td>
								</tr>
								</xsl:for-each>
								<tr>
									<td>PortTax</td>
									<td><xsl:value-of select="di:TourRules/di:CombinedIncludedCharges/di:PortTax/di:Price/di:Adult" /></td>
									<td><xsl:value-of select="di:TourRules/di:CombinedIncludedCharges/di:PortTax/di:Price/di:Child" /></td>
								</tr>
								<tr>
									<td>FoodFund</td>
									<td><xsl:value-of select="di:TourRules/di:CombinedIncludedCharges/di:FoodFund/di:Price/di:Adult" /></td>
									<td><xsl:value-of select="di:TourRules/di:CombinedIncludedCharges/di:FoodFund/di:Price/di:Child" /></td>
								</tr>								
							</table>
						</td>						
					</tr>
					<tr><td><h3 class="version2">Discounts</h3></td></tr>
					<tr>
						<td>
							<table class="reference" cellspacing="0" cellpadding="0" border="1" align="left">
								<tr>
									<th align="left" rowspan="1">Discount Code</th>
									<th align="left" rowspan="1">Type</th>
									<th align="left" rowspan="1">Basis</th>
									<th align="left" rowspan="1">Amount</th>
									<th align="left" rowspan="1">IsPercentage</th>
									<th align="left" rowspan="1">PaymentDueDate</th>
								</tr>
								<xsl:for-each select="di:TourRules/di:Discounts/di:Discount">
								<tr>
									<td><xsl:value-of select="di:Code" /></td>
									<td><xsl:value-of select="di:Type" /></td>
									<td><xsl:value-of select="di:Basis" /></td>
									<td><xsl:value-of select="di:Amount" /></td>
									<td><xsl:value-of select="di:IsPercentage" /></td>
									<td><xsl:value-of select="di:PaymentDueDate" /></td>
								</tr>
								</xsl:for-each>
							</table>							
						</td>
					</tr>
					<tr><td><h3 class="version2">Associated Products</h3></td></tr>
					<tr>
						<td>
							<table class="reference" cellspacing="0" cellpadding="0" border="1" align="left" width="100%">
								<tr>
									<th colspan="12" align="left">Associated Accommodation Products</th>
								</tr>
								<tr>
									<th colspan="12" align="left">PreAccommodations</th>
								</tr>
								<tr>
									<th align="left" rowspan="1">Code</th>
									<th align="left" rowspan="1">Name</th>
									<th align="left" rowspan="1">Rooms</th>
									<th align="left" rowspan="1">Address Line1</th>
									<th align="left" rowspan="1">AddressLine2</th>
									<th align="left" rowspan="1">Address Line 3</th>
									<th align="left" rowspan="1">City</th>
									<th align="left" rowspan="1">Region</th>
									<th align="left" rowspan="1">PostCode</th>
									<th align="left" rowspan="1">Country</th>
								</tr>								
								<xsl:for-each select="di:AssociatedProducts/di:AccommodationProducts/di:AccommodationProduct[@Type='PreAccommodation']">
									<tr>
										<td><xsl:value-of select="di:Code" /></td>
										<td><xsl:value-of select="di:Name" /></td>
										<td>
										<table>
											<tr align="left">
												<th>Room Type</th>
												<th>AdultPrice</th>
												<th>ChildPrice</th>
											</tr>
											<xsl:for-each select="di:Rooms/di:Room">
											<tr>
												<td><xsl:value-of select="di:Type" /></td>
												<td><xsl:value-of select="di:Price/di:Adult" /></td>
												<td><xsl:value-of select="di:Price/di:Child" /></td>
											</tr>
											</xsl:for-each>
										</table>										
										</td>
										<td><xsl:value-of select="di:Address/di:Line1" /></td>
										<td><xsl:value-of select="di:Address/di:Line2" /></td>
										<td><xsl:value-of select="di:Address/di:Line3" /></td>
										<td><xsl:value-of select="di:Address/di:City" /></td>
										<td><xsl:value-of select="di:Address/di:Region" /></td>
										<td><xsl:value-of select="di:Address/di:Postcode" /></td>
										<td><xsl:value-of select="di:Address/di:Country" /></td>										
									</tr>
								</xsl:for-each>
								<tr>
									<th colspan="12" align="left">PostAccommodations</th>
								</tr>
								<tr>
									<th align="left" rowspan="1">Code</th>
									<th align="left" rowspan="1">Name</th>
									<th align="left" rowspan="1">Rooms</th>
									<th align="left" rowspan="1">Address Line1</th>
									<th align="left" rowspan="1">AddressLine2</th>
									<th align="left" rowspan="1">Address Line 3</th>
									<th align="left" rowspan="1">City</th>
									<th align="left" rowspan="1">Region</th>
									<th align="left" rowspan="1">PostCode</th>
									<th align="left" rowspan="1">Country</th>
								</tr>								
								<xsl:for-each select="di:AssociatedProducts/di:AccommodationProducts/di:AccommodationProduct[@Type='PostAccommodation']">
									<tr>
										<td><xsl:value-of select="di:Code" /></td>
										<td><xsl:value-of select="di:Name" /></td>
										<td>
											<table>
												<tr align="left">
													<th>Room Type</th>
													<th>AdultPrice</th>
													<th>ChildPrice</th>
												</tr>
												<xsl:for-each select="di:Rooms/di:Room">
													<tr>
														<td><xsl:value-of select="di:Type" /></td>
														<td><xsl:value-of select="di:Price/di:Adult" /></td>
														<td><xsl:value-of select="di:Price/di:Child" /></td>
													</tr>
												</xsl:for-each>
											</table>										
										</td>
										<td><xsl:value-of select="di:Address/di:Line1" /></td>
										<td><xsl:value-of select="di:Address/di:Line2" /></td>
										<td><xsl:value-of select="di:Address/di:Line3" /></td>
										<td><xsl:value-of select="di:Address/di:City" /></td>
										<td><xsl:value-of select="di:Address/di:Region" /></td>
										<td><xsl:value-of select="di:Address/di:Postcode" /></td>
										<td><xsl:value-of select="di:Address/di:Country" /></td>										
									</tr>
								</xsl:for-each>
							</table>															
							<table class="reference" cellspacing="0" cellpadding="0" border="1" align="left" style="display:inline-block;margin-top:10px">
								<tr>
									<th colspan="12" align="left">Associated Miscellaneous Products</th>
								</tr>
								<tr>
									<th align="left" rowspan="1">Miscellaneous Product Code</th>
									<th align="left" rowspan="1">Name</th>
									<th align="left" rowspan="1">Category</th>
									<th align="left" rowspan="1">AdultPrice</th>
									<th align="left" rowspan="1">ChildPrice</th>									
								</tr>								
								<xsl:for-each select="di:AssociatedProducts/di:MiscellaneousProducts/di:MiscellaneousProduct">
									<tr>
										<td><xsl:value-of select="di:Code" /></td>
										<td><xsl:value-of select="di:Name" /></td>
										<td><xsl:value-of select="di:Category" /></td>
										<td><xsl:value-of select="di:Price/di:Adult" /></td>
										<td><xsl:value-of select="di:Price/di:Child" /></td>
									</tr>
								</xsl:for-each>
							</table>				
						</td>
					</tr>					
				</table>		
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
				<td><xsl:value-of select="../@Currency" /></td>
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

