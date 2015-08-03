<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:mvdi="http://www.ttsl.com/MarketVariationDepartureInfo/2010/09/1.1"
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4"
	xmlns:ts="http://www.ttc.com/ch2/api/ccapi/v1"
	xmlns="http://www.ttsl.com/MarketLocalisedTourData/2010/11/1.0"
	exclude-result-prefixes="xs #default mvdi ti ts"
	version="2.0">

	<xsl:template match="/ts:TransformationSource">
		<MarketLocalisedTourData>
			<xsl:attribute name="BrandCode"><xsl:value-of select="@BrandCode" /></xsl:attribute>
			<xsl:attribute name="CatalogueCode"><xsl:value-of select="./ti:TourInfo/@CatalogueCode" /></xsl:attribute>
			<xsl:attribute name="TropicsBrochureCode"><xsl:value-of select="mvdi:MarketVariationDepartureInfo/@BrochureCode" /></xsl:attribute>
			<xsl:attribute name="MarketVariationCode"><xsl:value-of select="mvdi:MarketVariationDepartureInfo/@MarketVariationCode" /></xsl:attribute>
			<xsl:attribute name="OperatingProductCode"><xsl:value-of select="mvdi:MarketVariationDepartureInfo/@OperatingProductCode" /></xsl:attribute>
			<xsl:attribute name="SellingCompanyCode"><xsl:value-of select="@SellingCompanyCode" /></xsl:attribute>
			<xsl:attribute name="Duration"><xsl:value-of select="ti:TourInfo/@Duration" /></xsl:attribute>
			<xsl:attribute name="BookableOnline"><xsl:value-of select="mvdi:MarketVariationDepartureInfo/@BookableOnline" /></xsl:attribute>
			<xsl:if test="mvdi:MarketVariationDepartureInfo/@TourPackage='true'">
				<xsl:attribute name="TourPackage">true</xsl:attribute>
			</xsl:if>
			<xsl:variable name="sellingCompanyCode" select="@SellingCompanyCode" />
			<xsl:attribute name="Currency"><xsl:value-of select="mvdi:MarketVariationDepartureInfo/mvdi:MarketVariationPricings/mvdi:MarketVariationPricing[@SellingCompanyCode=$sellingCompanyCode]/@Currency" /></xsl:attribute>
			<xsl:apply-templates select="@*|node()">
				<xsl:with-param name="sellingCompanyCode" select="@SellingCompanyCode" />
			</xsl:apply-templates>
		</MarketLocalisedTourData>
	</xsl:template>

	<!-- match everything, unless more specific match defined elsewhere. copy all elements and attributes -->
	<xsl:template match="@*|node()">
		<xsl:param name="sellingCompanyCode" />
		<xsl:copy>
			<xsl:apply-templates select="@*|node()">
				<xsl:with-param name="sellingCompanyCode" select="$sellingCompanyCode" />
			</xsl:apply-templates>
		</xsl:copy>
	</xsl:template>

	<!-- match all elements in defined namespaces, but under new default namespace. -->
	<xsl:template match="mvdi:*|ti:*">
		<xsl:param name="sellingCompanyCode" />
		<xsl:element name="{local-name()}">
			<xsl:apply-templates select="@*|node()">
				<xsl:with-param name="sellingCompanyCode" select="$sellingCompanyCode" />
			</xsl:apply-templates>
		</xsl:element>
	</xsl:template>

	<!-- match all elements in defined namespaces, but under new default namespace. -->
	<xsl:template match="ti:SellingCompanies">
		<xsl:param name="sellingCompanyCode" />
		<Brochures>
			<xsl:apply-templates select="ti:SellingCompany[@Code=$sellingCompanyCode]/ti:Brochure">
				<xsl:with-param name="sellingCompanyCode" select="$sellingCompanyCode" />
			</xsl:apply-templates>
		</Brochures>
	</xsl:template>

	<!-- rename this element and lose the attributes -->
	<xsl:template match="mvdi:MarketVariationDepartureInfo">
		<xsl:param name="sellingCompanyCode" />
		<DepartureInfo>
			<xsl:apply-templates select="node()">
				<xsl:with-param name="sellingCompanyCode" select="$sellingCompanyCode" />
			</xsl:apply-templates>
		</DepartureInfo>
	</xsl:template>

	<!-- rename this element and lose the attributes -->
	<xsl:template match="mvdi:MarketVariationPricing">
		<xsl:param name="sellingCompanyCode" />
		<TourSeriesPricing>
			<xsl:apply-templates select="@*|node()">
				<xsl:with-param name="sellingCompanyCode" select="$sellingCompanyCode" />
			</xsl:apply-templates>
		</TourSeriesPricing>
	</xsl:template>

	<!-- exclude these attributes -->
	<xsl:template match="ti:TourInfo/@*" />
	<xsl:template match="mvdi:MarketVariationPricing/@Currency" />
	<xsl:template match="mvdi:MarketVariationPricing/@SellingCompanyCode|@Currency" />
	<xsl:template match="mvdi:DeparturePricing/@SellingCompanyCode" />

	<!-- only render the node for current selected selling-company -->
	<xsl:template match="mvdi:MarketVariationPricings">
		<xsl:param name="sellingCompanyCode" />
		<xsl:apply-templates select="mvdi:MarketVariationPricing[@SellingCompanyCode=$sellingCompanyCode]" />
	</xsl:template>

	<!-- only render the node for current selected selling-company -->
	<xsl:template match="mvdi:DeparturePricings">
		<xsl:param name="sellingCompanyCode" />
		<xsl:apply-templates select="mvdi:DeparturePricing[@SellingCompanyCode=$sellingCompanyCode]" />
	</xsl:template>

</xsl:stylesheet>