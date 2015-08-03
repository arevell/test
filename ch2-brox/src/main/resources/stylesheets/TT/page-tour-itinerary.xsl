<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.1"
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4" xmlns:di="http://www.ttsl.com/MarketVariationDepartureInfo/2010/09/1.1">


	<xsl:template name="Itinerary-TT">


		<fo:block font-family="CenturyStd-Book" font-size="12pt"
			margin-left="4mm" margin-right="4mm" margin-top="4mm" 
			color="red">
			<xsl:text>Itinerary</xsl:text>
		</fo:block>

		<xsl:for-each select="ti:Itinerary/ti:ItinerarySegment[position() &lt; 5]">
			<xsl:call-template name="ItinerarySegment-TT" />
		</xsl:for-each>
		<!-- </fo:block> -->

		<xsl:for-each select="ti:Itinerary/ti:ItinerarySegment[position() &gt;= 5]">
			<xsl:call-template name="ItinerarySegment-TT" />
		</xsl:for-each>

	</xsl:template>

	<xsl:template name="ItinerarySegment-TT">
		<!-- <xsl:template match="ti:ItinerarySegment"> -->
		<fo:block
			margin-left="4mm" margin-right="4mm" margin-top="4mm">

			<fo:block font-family="AvenirLTStd-Heavy" font-size="9pt" font-weight="bold"
				keep-with-next="always">

				<fo:inline>
					<xsl:choose>
						<xsl:when test="@Duration = 1">
							Day
							<xsl:value-of select="@StartDay" />
						</xsl:when>
						<xsl:otherwise>
							Days
							<xsl:value-of select="@StartDay" />
							-
							<xsl:value-of select="@StartDay + @Duration - 1" />
						</xsl:otherwise>
					</xsl:choose>
				</fo:inline>
				<fo:inline>
					&#xa0;
					<xsl:value-of select="ti:Title" />
				</fo:inline> <!-- &#xa0; same as HTML &nbsp -->

			</fo:block>

			<fo:block font-family="AvenirLTStd-Light" font-size="9pt">
				<xsl:apply-templates select="ti:Text" />
			</fo:block>

		</fo:block>
	</xsl:template>


</xsl:stylesheet>