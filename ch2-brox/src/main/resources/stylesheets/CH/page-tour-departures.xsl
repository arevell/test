<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.1"
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4"
	xmlns:di="http://www.ttsl.com/MarketVariationDepartureInfo/2010/09/1.1">

	<xsl:template name="tour-departures-CH">
		<fo:page-sequence master-reference="standard-CH">

			<fo:static-content flow-name="footer-CH">
				<xsl:call-template name="footer-CH"/>
			</fo:static-content>
			
			<fo:flow flow-name="xsl-region-body">
				
				<fo:block font-family="FuturaStd-ExtraBold" font-size="34pt" 
					border-bottom-style="solid" margin-bottom="7mm">
					DATES &amp; RATES
				</fo:block>
				
				<xsl:call-template name="datesandrates-CH"/>
				
			</fo:flow>
		</fo:page-sequence>
	</xsl:template>

	

	<!-- dates and rates table -->
	<xsl:template name="datesandrates-CH">
		<xsl:variable name="mvcode" select="ancestor::ti:TourInfo[1]/@MVCode" />

		<fo:block>
			<xsl:variable name="code" select="ancestor::ti:TourInfo[1]/@MVCode" />
			<xsl:choose>
				<xsl:when
					test="/brochure/di:MarketVariationDepartureInfo[@MarketVariationCode=$code]/di:Departures/di:Departure[@AvailabilityStatus!='Cancelled']">
					<xsl:call-template name="departurestable-CH" />
				</xsl:when>
				
				<xsl:otherwise>
					<fo:block margin-top="1mm" font-size='8pt'
						margin-left="4mm" xsl:use-attribute-sets="font-sans">
						Please enquire for availability.
					</fo:block>
				</xsl:otherwise>
			</xsl:choose>
		</fo:block>

	</xsl:template>

	<xsl:template name="departurestable-CH">
		<fo:block-container>
			<fo:table table-layout="fixed" width="130mm" margin-top="1mm"
				margin-left="1mm">
				<fo:table-column column-width='30mm' />
				<fo:table-column column-width='30mm' />
				<fo:table-column column-width='35mm' />
				<fo:table-column column-width='35mm' />

				<fo:table-header font-family="FSAlbert-Bold" font-size='10pt'>

					<fo:table-row>
						<fo:table-cell>
							<fo:block padding-after="2mm">Departs</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block>Returns</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block>Twin Land Only</fo:block>
						</fo:table-cell>
						<fo:table-cell>
							<fo:block>Single Land Only</fo:block>
						</fo:table-cell>
					</fo:table-row>
				</fo:table-header>
				<fo:table-body font-size='10pt' font-family="FSAlbert-Light">

					<xsl:variable name="code" select="ancestor::ti:TourInfo[1]/@MVCode" />

					<!-- these are the distinct available dates for the marketing company -->
					<xsl:variable name="dates" select="/brochure/di:MarketVariationDepartureInfo[@MarketVariationCode=$code]/di:Departures/di:Departure[@AvailabilityStatus!='Cancelled'][not(@StartDate = preceding-sibling::*/@StartDate)]/@StartDate" />
						

					<!-- this is a collection of all the departures, so we can refer to it in the loop below -->
					<xsl:variable name="departures"
						select="/brochure/di:MarketVariationDepartureInfo[@MarketVariationCode=$code]/di:Departures" />

					<xsl:variable name="brochure" select="/brochure" />
					<xsl:variable name="company" select="/brochure/@SellingCompany" />

					<xsl:for-each select="$dates">

						<fo:table-row>
							<!-- get the cheapest departure for the date in this position-->

							<xsl:variable name="date1" select="." />
							<xsl:variable name="rates1"
								select="$departures/di:Departure[@StartDate=$date1 and @AvailabilityStatus!='Cancelled']" />

							<!-- go through departures sorted by the price for the selling co-->
							<xsl:for-each select="$rates1">
								<xsl:sort
									select="di:DeparturePricings/di:DeparturePricing[@SellingCompanyCode=$company]/@AdultTwinRoomPriceCombined"
									data-type="number" />
								<xsl:if test="position() = 1">
									<xsl:call-template name="rate-cells-CH">
										<xsl:with-param name="rate" select="." />
										<xsl:with-param name="brochure" select="$brochure" />
									</xsl:call-template>
								</xsl:if>
							</xsl:for-each>
						</fo:table-row>
					</xsl:for-each>
				</fo:table-body>
			</fo:table>
		</fo:block-container>

	</xsl:template>

	<!-- this does the three cells for a DatesAndRates. -->
	<xsl:template name="rate-cells-CH">
		<xsl:param name="brochure" />
		<xsl:param name="rate" />
		
		<fo:table-cell>
			<fo:block margin-top="0.5mm">
				<xsl:call-template name="format-date">
					<xsl:with-param name="date" select="$rate/@StartDate" />
				</xsl:call-template>
			</fo:block>
		</fo:table-cell>
		<fo:table-cell>
			<fo:block margin-top="0.5mm">
				<xsl:call-template name="format-date">
					<xsl:with-param name="date" select="$rate/@EndDate" />
				</xsl:call-template>
			</fo:block>
		</fo:table-cell>
		
		<xsl:variable name="prices" select="$rate/di:DeparturePricings/di:DeparturePricing[@SellingCompanyCode=$brochure/@SellingCompany]"/>
		
		<fo:table-cell>
			<fo:block margin-top="0.5mm">
				<xsl:call-template name="format-price">
					<xsl:with-param name="amount"
						select="$prices/@AdultTwinRoomPriceCombined" />
					<xsl:with-param name="symbol"
						select="$brochure/terms/@currency_symbol" />
				</xsl:call-template>
			</fo:block>
		</fo:table-cell>
		
		<fo:table-cell>
			<fo:block margin-top="0.5mm">
				<xsl:call-template name="format-price">
					<xsl:with-param name="amount"
						select="$prices/@AdultSingleRoomPriceCombined" />
					<xsl:with-param name="symbol"
						select="$brochure/terms/@currency_symbol" />
				</xsl:call-template>

			</fo:block>
		</fo:table-cell>

	</xsl:template>

</xsl:stylesheet>