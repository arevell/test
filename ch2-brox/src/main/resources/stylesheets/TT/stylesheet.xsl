<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="2.0" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4"
	xmlns:di="http://www.ttls.com/MarketVariationDepartureInfo/2009/01/alpha">

	<!-- dateFormat is a global variable with the format of Dates -->	
	<xsl:variable name="dateFormat" select="/brochure/frontispiece/@dateFormat"/>

	<xsl:include href="stylesheets/TT/page-frontcover.xsl" />
	<xsl:include href="stylesheets/TT/page-contents.xsl" />
	<xsl:include href="stylesheets/TT/page-intro.xsl" />
	<xsl:include href="stylesheets/TT/page-offers.xsl" />
	<xsl:include href="stylesheets/TT/page-tour.xsl" />
	<xsl:include href="stylesheets/TT/page-terms.xsl" />
	<xsl:include href="stylesheets/TT/text.xsl" />
	<xsl:include href="stylesheets/TT/attribute-sets.xsl" />
	<xsl:include href="stylesheets/TT/layout.xsl" />
	<xsl:include href="stylesheets/TT/format.xsl" />

	<xsl:strip-space elements="*" />

	<xsl:param name="currentDate" />
	<xsl:variable name="smallcase" select="'abcdefghijklmnopqrstuvwxyz'" />
	<xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'" />
	<xsl:variable name="apos">'</xsl:variable>

	<xsl:template match="/brochure">

		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">
			<!-- define the page layouts to be used -->
			<xsl:call-template name="layout">
				<xsl:with-param name="papersize" select="terms/@papersize" />
			</xsl:call-template>
			<!-- generate pages -->
			<xsl:call-template name="page-frontcover" />
			<xsl:call-template name="page-contents" />
			<xsl:apply-templates select="frontispiece/intro" />
			<xsl:apply-templates select="frontispiece/offers" />
			<xsl:apply-templates select="ti:TourInfo"/>
			<xsl:apply-templates select="terms" />
		</fo:root>

	</xsl:template>

</xsl:stylesheet>