<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.1"
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4"
	xmlns:di="http://www.ttsl.com/MarketVariationDepartureInfo/2010/09/1.1">

	<!-- these sections broken out for ease of maintenance -->
	<xsl:include href="stylesheets/BV/page-tour-itinerary.xsl" />
	<xsl:include href="stylesheets/BV/page-tour-departures.xsl" />

	<xsl:template match="ti:TourInfo">
		<xsl:variable name="map"
			select="ti:Assets/ti:Image[@Type='map' and number(@Width) &gt; 500]" />
		<xsl:variable name="photo"
			select="ti:Assets/ti:Image[@Type='photo' and number(@Width) &gt; 500]" />


		<!-- first tour page -->
		<fo:page-sequence master-reference="tour-pages">

			<xsl:call-template name="borders" />

			<fo:static-content flow-name="header-rest">
				<!--
					the header on subsequent pages- the first page header is included
					in the flow
				-->
				<fo:block-container height="19mm" overflow="hidden"
					display-align="center" text-align="center" xsl:use-attribute-sets="at-tourhead"
					margin-bottom="0mm" border-top-width="0.1mm" border-top-style="solid"
					border-top-color="black" border-bottom-width="1.9mm"
					border-bottom-style="solid" border-bottom-color="white">

					<fo:block xsl:use-attribute-sets="font-serif" font-size="18pt">
						<xsl:value-of select="ti:TourName" />
					</fo:block>

				</fo:block-container>
			</fo:static-content>

			<fo:flow flow-name="xsl-region-body">

				<fo:block id="{@MVCode}" text-align="center"
					padding-before="3mm" padding-after="3mm" xsl:use-attribute-sets="at-tourhead"
					margin-bottom="0.2mm" border-top-width="0.1mm" border-top-style="solid"
					border-color="black">

					<fo:block xsl:use-attribute-sets="font-sans" font-size="10pt">
						<xsl:value-of select="@Duration" />
						DAYS
					</fo:block>
					<fo:block xsl:use-attribute-sets="font-serif" font-size="18pt">
						<xsl:value-of select="ti:TourName" />
					</fo:block>
					<fo:block xsl:use-attribute-sets="font-curly" font-size="12pt">
						<xsl:value-of select="ti:Metadata/ti:TourStyles/ti:TourStyle" />
					</fo:block>
				</fo:block>

				<!--  pictures here -->
				<fo:block-container height="90mm"
					overflow="hidden" margin-top="0mm" margin-bottom="0mm" padding-top="0mm"
					padding-bottom="0mm">


					<fo:block xsl:use-attribute-sets="at-plain"
						margin-top="0mm" margin-bottom="0mm" padding-top="0mm"
						padding-bottom="0mm" font-size="0" line-height="0">


						<fo:external-graphic margin-top="0mm"
							margin-bottom="0mm" padding-top="0mm" padding-bottom="0mm"
							height="90mm" width="90mm" content-width="scale-to-fit"
							content-height="scale-to-fit" scaling="non-uniform">
							<xsl:attribute name="src">
								<xsl:value-of select="normalize-space(concat('url(', $apos, $photo[1]/@Url, $apos, ')'))" />
							</xsl:attribute>
						</fo:external-graphic>

						<fo:external-graphic margin-top="0mm"
							margin-bottom="0mm" padding-top="0mm" padding-bottom="0mm"
							height="90mm" width="90mm" content-width="scale-to-fit"
							content-height="scale-to-fit" scaling="non-uniform">
							<xsl:attribute name="src">
								<xsl:value-of select="normalize-space(concat('url(', $apos, $map[1]/@Url, $apos, ')'))" />
							</xsl:attribute>
						</fo:external-graphic>

					</fo:block>
				</fo:block-container>

				<fo:block xsl:use-attribute-sets="at-tourtag"
					padding-before="4mm" padding-after="4mm" margin-top="0mm">
					<fo:block font-size="12pt" font-weight="bold"
						xsl:use-attribute-sets="font-sans" margin-left="4mm" margin-right="4mm">
						Tour Summary
					</fo:block>
				</fo:block>



				<fo:block margin-left="0mm" margin-right="0mm"
					padding-before="1mm" padding-after="5mm" padding-left="4mm"
					padding-right="4mm" font-size="10pt" xsl:use-attribute-sets="font-sans">
					<xsl:apply-templates select="Description" />
					<fo:block font-weight="bold" margin-top="3mm"
						margin-bottom="2mm">
						Locations Visited
					</fo:block>
					<xsl:for-each select="ti:LocationsVisited/ti:Location">
						<xsl:value-of select="@Name" />
						<xsl:if test="position() != last()">
							-
						</xsl:if>
					</xsl:for-each>

					<xsl:apply-templates select="ti:WhatsIncluded" />
					<xsl:apply-templates select="ti:Highlights" />
				</fo:block>


				<xsl:call-template name="Itinerary" />

				<xsl:call-template name="DatesAndRates">
					<xsl:with-param name="photo" select="$photo[2]"/>
				</xsl:call-template>

			</fo:flow>
		</fo:page-sequence>
	</xsl:template>






	<xsl:template match="ti:WhatsIncluded">
		<xsl:apply-templates select="ti:Section[ti:Title!='Airport Transfers']" />
	</xsl:template>

	<xsl:template match="ti:WhatsIncluded/ti:Section">
		<xsl:apply-templates />
	</xsl:template>

	<xsl:template match="ti:WhatsIncluded/ti:Section/ti:Title">
		<fo:block font-weight="bold" margin-top="3mm" margin-bottom="2mm"
			keep-with-next="2">
			<xsl:apply-templates />
		</fo:block>
	</xsl:template>


	<xsl:template match="ti:Highlights">
		<xsl:apply-templates select="ti:Section" />
	</xsl:template>
	
	<xsl:template match="ti:Highlights/ti:Section">
		<xsl:apply-templates />
	</xsl:template>
	
	<xsl:template match="ti:Highlights/ti:Section/ti:Title">
		<fo:block font-weight="bold" margin-top="3mm" margin-bottom="2mm"
			keep-with-next="2">
			<xsl:apply-templates />
		</fo:block>
	</xsl:template>
</xsl:stylesheet>