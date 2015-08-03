<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	version="2.0" xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4"
	xmlns:di="http://www.ttls.com/MarketVariationDepartureInfo/2009/01/alpha">

	<xsl:include href="stylesheets/CH/page-frontcover.xsl" />
	<xsl:include href="stylesheets/CH/page-contents.xsl" />
	<xsl:include href="stylesheets/CH/page-intro1.xsl" />
	<xsl:include href="stylesheets/CH/page-intro2.xsl" />
	<xsl:include href="stylesheets/CH/page-questions.xsl" />
	<xsl:include href="stylesheets/CH/page-tour.xsl" />
	<xsl:include href="stylesheets/CH/page-tour-itinerary.xsl"/>
	<xsl:include href="stylesheets/CH/page-tour-departures.xsl"/>
	<xsl:include href="stylesheets/CH/page-terms.xsl" />
	<xsl:include href="stylesheets/CH/text.xsl" />
	<xsl:include href="stylesheets/CH/attribute-sets.xsl" />
	<xsl:include href="stylesheets/CH/layout.xsl" />
	<xsl:include href="stylesheets/CH/format.xsl" />

	<!-- <xsl:strip-space elements="*" /> -->
	<xsl:preserve-space elements="*"/>

	<xsl:param name="currentDate" />
	<xsl:param name="currentDateNumerical" />
	<xsl:variable name="smallcase" select="'abcdefghijklmnopqrstuvwxyz'" />
	<xsl:variable name="uppercase" select="'ABCDEFGHIJKLMNOPQRSTUVWXYZ'" />
	<xsl:variable name="apos">'</xsl:variable>

	<xsl:template match="/brochure">

		<fo:root xmlns:fo="http://www.w3.org/1999/XSL/Format">


			<!-- define the page layouts to be used -->
			<xsl:call-template name="layout">
				<xsl:with-param name="papersize" select="terms/@papersize" />
			</xsl:call-template>

			<!--  generate pages  -->

			<xsl:call-template name="bookmarks" />

			<xsl:call-template name="page-frontcover" />
			<xsl:call-template name="page-contents" />
			
			<xsl:apply-templates select="frontispiece/intro1" />
			<xsl:apply-templates select="frontispiece/intro2" />
			<xsl:apply-templates select="ti:TourInfo/ti:TourName" />
			<xsl:apply-templates select="frontispiece/questions" />
			
			<xsl:apply-templates select="terms" />
			
		</fo:root>

	</xsl:template>

	<!--  this template generates the bookmarks -->
	<xsl:template name="bookmarks">
		<fo:bookmark-tree>
			<fo:bookmark internal-destination="contents-CH">
				<fo:bookmark-title>Contents</fo:bookmark-title>
			</fo:bookmark>

			<xsl:choose>
				<xsl:when test="ti:TourInfo/@BrandCode = 'CH'">
					<fo:bookmark internal-destination="intro2-CH">
						<fo:bookmark-title>
							<xsl:value-of select="/brochure/frontispiece/intro2/title" />
						</fo:bookmark-title>
					</fo:bookmark>
					
					<xsl:for-each select="/brochure/ti:TourInfo">
						<fo:bookmark internal-destination="{@MVCode}">
							<fo:bookmark-title>
								<xsl:value-of select="ti:TourName" />
							</fo:bookmark-title>
						</fo:bookmark>
					</xsl:for-each>
					
					
					
					<fo:bookmark internal-destination="questions-CH">
						<fo:bookmark-title>Got a Question?</fo:bookmark-title>
					</fo:bookmark>
					
					<fo:bookmark internal-destination="terms">
						<fo:bookmark-title>Booking Conditions</fo:bookmark-title>
					</fo:bookmark>
				</xsl:when>
				
				<xsl:otherwise>
					<fo:bookmark internal-destination="intro2">
						<fo:bookmark-title>
							<xsl:value-of select="/brochure/frontispiece/intro2/title" />
						</fo:bookmark-title>
					</fo:bookmark>
					
					<fo:bookmark internal-destination="offers">
						<fo:bookmark-title>Promotions</fo:bookmark-title>
					</fo:bookmark>
					
					
					<xsl:for-each select="/brochure/ti:TourInfo">
						<fo:bookmark internal-destination="{@MVCode}">
							<fo:bookmark-title>
								<xsl:value-of select="ti:TourName" />
							</fo:bookmark-title>
						</fo:bookmark>
					</xsl:for-each>
					
					<fo:bookmark internal-destination="datesandrates-CH">
						<fo:bookmark-title>Dates &amp; Rates</fo:bookmark-title>
					</fo:bookmark>
					
					<fo:bookmark internal-destination="terms">
						<fo:bookmark-title>Terms &amp; Conditions</fo:bookmark-title>
					</fo:bookmark>
				</xsl:otherwise>
			</xsl:choose>
			
		</fo:bookmark-tree>

	</xsl:template>
</xsl:stylesheet>