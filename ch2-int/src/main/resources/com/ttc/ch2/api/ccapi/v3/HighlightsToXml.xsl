<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:hl="http://www.ttc.com/ch2/api/ccapi/v3/TourInfo/2014/01/3.0"
	exclude-result-prefixes="#default hl"
	version="2.0">

	<xsl:output method="html" version="4.0" encoding="utf-8" indent="no" omit-xml-declaration="yes" />

	<xsl:template match="hl:Highlights">
		<div class="highlights">
			<xsl:apply-templates></xsl:apply-templates>
		</div>
	</xsl:template>

	<xsl:template match="hl:WhatsIncluded">
		<div class="whats_included">
			<xsl:apply-templates></xsl:apply-templates>
		</div>
	</xsl:template>

	<!-- currently no namespace on Highlights element coming out of SOLR -->
	<xsl:template match="Highlights">
		<div class="highlights">
			<xsl:apply-templates></xsl:apply-templates>
		</div>
	</xsl:template>

	<xsl:template match="hl:Section">
		<div class="section">
			<xsl:apply-templates></xsl:apply-templates>
		</div>
	</xsl:template>

	<xsl:template match="hl:Title">
		<span class="title">
			<xsl:apply-templates></xsl:apply-templates>
		</span>
	</xsl:template>

	<xsl:template match="hl:Text">
		<p>
			<xsl:apply-templates></xsl:apply-templates>
		</p>
	</xsl:template>

	<!-- match all elements in namespace hl -->
	<xsl:template match="hl:*">
		<xsl:element name="{local-name()}">
			<xsl:apply-templates select="@*|node()" />
		</xsl:element>
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

	<!-- standard copy template <xsl:template match="@*|node()"> <xsl:copy> <xsl:apply-templates select="@*"/> <xsl:apply-templates/> </xsl:copy> </xsl:template> -->
</xsl:stylesheet>