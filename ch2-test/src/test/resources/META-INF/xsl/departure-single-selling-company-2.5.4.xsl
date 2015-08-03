<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:td="http://www.ttc.com/ch2/api/ccapi/v3/TourDepartures/2013/09/2.5.4"
	xmlns="http://www.ttc.com/ch2/api/ccapi/v3/MarketLocalisedTourDepartures/2013/09/2.5.4"
	exclude-result-prefixes="xs #default td"
	version="2.0">

	<xsl:template match="/">
		<xsl:variable name="tour_code" select="td:TourDepartures/@TourCode" />
		<xsl:for-each select="//td:SellingCompany/@Code">
				<CustomTag xmlns="">
					<xsl:value-of select="." />
				</CustomTag>
				<xsl:apply-templates select="/td:TourDepartures">
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

	<xsl:template match="td:*">
		<xsl:param name="sellingCompanyCode" />
		<!-- <xsl:element name="{local-name()}" namespace="http://www.ttc.com/ch2/api/ccapi/v3/XX"> -->
		<xsl:element name="{local-name()}">
			<xsl:apply-templates select="@*|node()">
				<xsl:with-param name="sellingCompanyCode" select="$sellingCompanyCode" />
			</xsl:apply-templates>
		</xsl:element>
	</xsl:template>

	<!-- x -->
	<xsl:template match="td:TourDepartures">
		<xsl:param name="sellingCompanyCode" />
		<MarketLocalisedTourDepartures>
			<xsl:apply-templates select="@*|*">
				<xsl:with-param name="sellingCompanyCode" select="$sellingCompanyCode" />
			</xsl:apply-templates>
		</MarketLocalisedTourDepartures>
	</xsl:template>

	<!-- only render the node for current selected selling-company -->
	<xsl:template match="td:SellingCompanies">
		<xsl:param name="sellingCompanyCode" />
		<xsl:apply-templates select="td:SellingCompany[@Code=$sellingCompanyCode]" />
	</xsl:template>

</xsl:stylesheet>