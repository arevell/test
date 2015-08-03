<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" 
	xmlns:xs="http://www.w3.org/2001/XMLSchema"
	xmlns:ti="http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0"
	xmlns="http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0"
	exclude-result-prefixes="xs ti"
	version="2.0">

	<xsl:template match="/">
		<xsl:variable name="tourCode" select="ti:TourInfo/@TourCode" />
		<xsl:for-each select="ti:TourInfo/ti:SellingCompanies/ti:SellingCompany/@Code">
				<CustomTag xmlns="">
					<xsl:value-of select="." />
				</CustomTag>
				<xsl:apply-templates select="/ti:TourInfo">
					<xsl:with-param name="sellingCompanyCode" select="." />
				</xsl:apply-templates>
		</xsl:for-each>
	</xsl:template>

	<xsl:template match="@*|node()">
		<xsl:param name="sellingCompanyCode" />
		<xsl:copy>
			<xsl:apply-templates select="@*|node()">
				<xsl:with-param name="sellingCompanyCode" select="$sellingCompanyCode" />
			</xsl:apply-templates>
		</xsl:copy>
	</xsl:template>

	<xsl:template match="ti:*">
		<xsl:param name="sellingCompanyCode" />
		<xsl:element name="{local-name()}">
			<xsl:apply-templates select="@*|node()">
				<xsl:with-param name="sellingCompanyCode" select="$sellingCompanyCode" />
			</xsl:apply-templates>
		</xsl:element>
	</xsl:template>

	<xsl:template match="ti:TourInfo">
		<xsl:param name="sellingCompanyCode" />
		<TourInfo>
			<xsl:apply-templates select="@*|*">
				<xsl:with-param name="sellingCompanyCode" select="$sellingCompanyCode" />
			</xsl:apply-templates>
		</TourInfo>
	</xsl:template>

	<xsl:template match="ti:SellingCompanies">
		<xsl:param name="sellingCompanyCode" />
		<SellingCompanies>
			<xsl:apply-templates select="ti:SellingCompany[@Code=$sellingCompanyCode]" />
		</SellingCompanies>
	</xsl:template>

</xsl:stylesheet>