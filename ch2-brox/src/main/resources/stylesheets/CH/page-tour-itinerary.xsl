<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.1"
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4" xmlns:fn="http://www.w3.org/2005/xpath-functions">
	<!-- xmlns:ti="http://www.ttsl.com/TourInfo/2014/01/3.0" -->

	<xsl:template match="ti:TourInfo/ti:Itinerary">
		<fo:page-sequence master-reference="tour-pages-CH">

			<fo:static-content flow-name="footer-CH">
				<xsl:call-template name="footer-CH" />
			</fo:static-content>

			<!-- first tour page -->
			<fo:static-content flow-name="header-first-CH">
				<fo:block-container height="19mm" overflow="hidden"
					display-align="center" margin-bottom="0mm" border-bottom-color="white">

					<fo:block font-family="FuturaStd-ExtraBold" font-size="34pt"
						font-weight="bold" border-bottom-style="solid" margin-bottom="7mm"
						span="all">
						ITINERARY
					</fo:block>

				</fo:block-container>
			</fo:static-content>

			<!-- continuation tour pages -->
			<fo:static-content flow-name="header-rest-CH">
				<fo:block-container height="19mm" overflow="hidden"
					display-align="center" margin-bottom="0mm" border-bottom-color="white">

					<fo:block font-family="FuturaStd-ExtraBold" font-size="34pt"
						font-weight="bold" border-bottom-style="solid" margin-bottom="7mm"
						span="all">
						ITINERARY
						<fo:inline font-family="FuturaStd-Bold" font-size="20">(continued)
						</fo:inline>
					</fo:block>

				</fo:block-container>
			</fo:static-content>

			<fo:flow flow-name="xsl-region-body">

				<xsl:call-template name="Itinerary-CH" />

			</fo:flow>
		</fo:page-sequence>
	</xsl:template>


	<xsl:template name="Itinerary-CH">

		<!-- <fo:block keep-together.within-page="always"> -->

		<xsl:for-each select="ti:ItinerarySegment[position() &lt; 5]">
			<xsl:call-template name="ItinerarySegment-CH" />
		</xsl:for-each>
		<!-- </fo:block> -->

		<xsl:for-each select="ti:ItinerarySegment[position() &gt;= 5]">

			<xsl:call-template name="ItinerarySegment-CH" />
		</xsl:for-each>

	</xsl:template>

	<xsl:template name="ItinerarySegment-CH">
		<!-- <xsl:template match="ti:ItinerarySegment"> -->
		<fo:block margin-left="0mm" margin-right="1mm" margin-bottom="5mm"
			font-size="9pt" padding-left="0mm" padding-right="2mm"
			xsl:use-attribute-sets="font-sans">

			<fo:block margin-bottom="1.5mm" keep-with-next="always">

				<fo:inline font-family="FuturaStd-Bold" font-size="11">
					<xsl:choose>
						<xsl:when test="@Duration = 1">
							DAY
							<xsl:value-of select="@StartDay" />
						</xsl:when>
						<xsl:otherwise>
							DAYS
							<xsl:value-of select="@StartDay" />
							-
							<xsl:value-of select="@StartDay + @Duration - 1" />
						</xsl:otherwise>
					</xsl:choose>
				</fo:inline>

				<xsl:variable name="title" select="ti:Title" />

				<fo:inline font-family="FuturaStd-Light" font-size="11">
					&#xa0;
					<xsl:value-of select="translate($title, $smallcase, $uppercase)" />
				</fo:inline> <!-- &#xa0; same as HTML &nbsp -->

			</fo:block>

			<fo:block font-size="9pt" font-family="FSAlbert-Light">
				<xsl:apply-templates select="ti:Text" />
			</fo:block>

			<fo:block>
				<xsl:if test="ti:Accommodation">
					<fo:inline font-family="FuturaStd-Bold" font-size="9"
						margin-top="1mm">
						Accommodation:
					</fo:inline>
					<fo:inline font-size="9pt" font-family="FSAlbert-Light">
						<xsl:value-of select="ti:Accommodation" />
					</fo:inline>
				</xsl:if>
			</fo:block>
			<fo:block>
				<xsl:if test="ti:Meals">
					<fo:inline font-family="FuturaStd-Bold" font-size="9"
						margin-top="1mm">
						Meals:
					</fo:inline>
					<fo:inline font-size="9pt" font-family="FSAlbert-Light">
						<xsl:value-of select="ti:Meals" />
					</fo:inline>
				</xsl:if>
			</fo:block>

		</fo:block>

	</xsl:template>

	<xsl:template match="ti:Text/ti:strong">
		<fo:inline font-family="FSAlbert-Bold">
			<xsl:value-of select="." />
		</fo:inline>
	</xsl:template>



</xsl:stylesheet>