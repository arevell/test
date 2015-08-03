<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:mvdi="http://www.ttsl.com/MarketVariationDepartureInfo/2010/09/1.1"
	xmlns="http://www.ttsl.com/MarketLocalisedTourDepartures/2010/09/1.1"
	exclude-result-prefixes="xs #default mvdi"
	version="2.0">

	<xsl:template match="/">
		<xsl:variable name="mv_code" select="mvdi:MarketVariationDepartureInfo/@MarketVariationCode" />
		<xsl:for-each select="//mvdi:MarketVariationPricing/@SellingCompanyCode">
				<CustomTag xmlns="">
					<xsl:value-of select="." />
				</CustomTag>
				<xsl:apply-templates select="/mvdi:MarketVariationDepartureInfo">
					<xsl:with-param name="sellingCompanyCode" select="." />
				</xsl:apply-templates>
		</xsl:for-each>
	</xsl:template>

	<!-- match everyting, unless more specific match defined elsewhere. copy all elements and attributes -->
	<xsl:template match="@*|node()">
		<xsl:param name="sellingCompanyCode" />
		<xsl:copy>
			<xsl:apply-templates select="@*|node()">
				<xsl:with-param name="sellingCompanyCode" select="$sellingCompanyCode" />
			</xsl:apply-templates>
		</xsl:copy>
	</xsl:template>

	<xsl:template match="mvdi:*">
		<xsl:param name="sellingCompanyCode" />
		<!-- <xsl:element name="{local-name()}" namespace="http://www.ttsl.com/XX"> -->
		<xsl:element name="{local-name()}">
			<xsl:apply-templates select="@*|node()">
				<xsl:with-param name="sellingCompanyCode" select="$sellingCompanyCode" />
			</xsl:apply-templates>
		</xsl:element>
	</xsl:template>

	<!-- x -->
	<xsl:template match="mvdi:MarketVariationDepartureInfo">
		<xsl:param name="sellingCompanyCode" />
		<MarketLocalisedTourDepartures>
			<xsl:attribute name="SellingCompany"><xsl:value-of select="$sellingCompanyCode"></xsl:value-of></xsl:attribute>
			<xsl:apply-templates select="@*|*">
				<xsl:with-param name="sellingCompanyCode" select="$sellingCompanyCode" />
			</xsl:apply-templates>
		</MarketLocalisedTourDepartures>
	</xsl:template>

	<!-- exclude these attributes -->
	<xsl:template match="mvdi:MarketVariationPricing/@SellingCompanyCode" />
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