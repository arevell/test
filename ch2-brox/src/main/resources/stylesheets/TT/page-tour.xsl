<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.1"
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4" xmlns:di="http://www.ttsl.com/MarketVariationDepartureInfo/2010/09/1.1">

	<!-- these sections broken out for ease of maintenance -->
	<xsl:include href="stylesheets/TT/page-tour-itinerary.xsl" />
	<xsl:include href="stylesheets/TT/page-tour-departures.xsl" />

	<xsl:template match="ti:TourInfo">
		<xsl:variable name="map"
			select="ti:Assets/ti:Image[@Type='map' and number(@Width) &gt; 500]" />
		<xsl:variable name="photo"
			select="ti:Assets/ti:Image[@Type='photo' and number(@Width) &gt; 500]" />


		<!-- first tour page -->
		<fo:page-sequence master-reference="tour-pages-TT">

			<fo:static-content flow-name="footer-TT">
				<xsl:call-template name="footer-TT"/>
			</fo:static-content>



			<fo:flow flow-name="xsl-region-body">

				<fo:block id="{@MVCode}" padding-before="0mm"
					padding-after="0mm">
					<fo:block font-family="CenturyStd-Book" font-size="28pt"
						color="red">
						<xsl:value-of select="ti:TourName" />
					</fo:block>
				</fo:block>

				<fo:block margin-top="5mm">
					<fo:table table-layout="fixed" width="80mm" font-size="10pt">
						<fo:table-column column-width="110mm" />
						<fo:table-column column-width="80mm" />

						<fo:table-body>
							<fo:table-row>

								<fo:table-cell>  <!-- TOUR DESCRIPTION AND MAP -->
									<fo:block margin-left="3mm" font-family="AvenirLTStd-Heavy"
										font-size="12pt" font-weight="bold" margin-right="5mm">
										<xsl:value-of select="@Duration" />
										days
									</fo:block>
									<fo:block margin-left="3mm" font-family="AvenirLTStd-Light"
										font-size="10pt" margin-right="10mm">
										<xsl:apply-templates select="ti:Description" />
									</fo:block>

									<fo:block margin-top="3mm" margin-bottom="3mm"
										padding-top="0mm" padding-bottom="0mm" font-size="0"
										line-height="0">
										<fo:external-graphic margin-top="0mm"
											margin-bottom="0mm" padding-top="0mm" padding-bottom="0mm"
											height="70mm" width="105mm" content-width="scale-to-fit"
											content-height="scale-to-fit" scaling="uniform">
											<xsl:attribute name="src">
												<xsl:value-of select="normalize-space(concat('url(', $apos, $map[1]/@Url, $apos, ')'))" />
											</xsl:attribute>
										</fo:external-graphic>
									</fo:block>
								</fo:table-cell>

								<fo:table-cell>  <!-- TOUR PHOTO, PLACES VISITED -->
									<fo:block padding-bottom="1mm">
										<fo:external-graphic margin-top="0mm"
											margin-bottom="0mm" padding-top="0mm" padding-bottom="0mm"
											height="35mm" width="70mm" content-width="scale-to-fit"
											content-height="scale-to-fit" scaling="uniform">
											<xsl:attribute name="src">
												<xsl:value-of select="normalize-space(concat('url(', $apos, $photo[1]/@Url, $apos, ')'))" />
											</xsl:attribute>
										</fo:external-graphic>
									</fo:block>
									<fo:block margin-top="3mm" font-family="CenturyStd-Book"
										font-size="12pt" color="red">
										<xsl:text>You will visit</xsl:text>
									</fo:block>

									<fo:block margin-top="2mm" font-family="AvenirLTStd-Light"
										font-size="10pt" font-weight="normal">
										<xsl:for-each select="ti:CountriesVisited/ti:Country">
											<xsl:value-of select="@Name" />
											<xsl:if test="position() != last()">
												,
											</xsl:if>
										</xsl:for-each>
									</fo:block>
								</fo:table-cell>

							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:block>

				<fo:block font-family="CenturyStd-Book" font-size="12pt"
					margin-left="4mm" margin-right="4mm" color="red">
					<xsl:text>Highlights</xsl:text>
				</fo:block>

				<fo:block 
					margin-left="0mm" margin-right="0mm" padding-left="4mm"
					padding-right="4mm">
					<xsl:apply-templates select="ti:WhatsIncluded" />
				</fo:block>

				<fo:block
					margin-left="0mm" margin-right="0mm" padding-left="4mm"
					padding-right="4mm">
					<xsl:apply-templates select="ti:Highlights" />
				</fo:block>


				<xsl:call-template name="Itinerary-TT" />

				<xsl:call-template name="DatesAndRates-TT" />


			</fo:flow>

		</fo:page-sequence>

	</xsl:template>

	<xsl:template match="ti:WhatsIncluded|ti:Highlights">
		<xsl:apply-templates select="ti:Section" />
	</xsl:template>

	<xsl:template match="ti:WhatsIncluded/ti:Section|ti:Highlights/ti:Section">
		<xsl:apply-templates  select="ti:Title"/>
		<fo:block font-family="AvenirLTStd-Light" font-size="9pt">
			<xsl:apply-templates select="ti:Text" />
		</fo:block>
	</xsl:template>

	<xsl:template
		match="ti:WhatsIncluded/ti:Section/ti:Title|ti:Highlights/ti:Section/ti:Title">
		<fo:block font-family="AvenirLTStd-Heavy" font-size="9pt"
			font-weight="bold" keep-with-next="2"
			margin-top="4mm">
			<xsl:value-of select="." />
		</fo:block>

	</xsl:template>





</xsl:stylesheet>