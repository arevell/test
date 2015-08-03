<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.1"
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4"
	xmlns:di="http://www.ttsl.com/MarketVariationDepartureInfo/2010/09/1.1"
>


	<xsl:template match="ti:TourInfo/ti:TourName">
	
		<xsl:variable name="map"
			select="ancestor::ti:TourInfo/ti:Assets/ti:Image[@Type='map' and number(@Width) &gt; 500]"/>
		<xsl:variable name="photo"
			select="ancestor::ti:TourInfo/ti:Assets/ti:Image[@Type='photo' and number(@Width) &gt; 500]"/>
		<xsl:variable name="mvcode"
			select="ancestor::ti:TourInfo[1]/@MVCode" />


		<!-- first tour page -->
		<fo:page-sequence master-reference="standard-CH">
			
			<fo:static-content flow-name="footer-CH">
				<xsl:call-template name="footer-CH"/>
			</fo:static-content>
			
						
			<fo:flow flow-name="xsl-region-body">

				<fo:block id="{$mvcode}" padding-before="0mm" padding-after="7mm">
					<fo:block xsl:use-attribute-sets="font-sans" font-size="23pt" font-family="FuturaStd-ExtraBold"
						padding-before="3mm" padding-after="3mm"  text-transform="uppercase"
						border-bottom-style="solid" border-top-style="solid">
						<xsl:value-of select="self::ti:TourName"/>
					</fo:block>
				</fo:block>
				
				<fo:block>
					<fo:table table-layout="fixed" font-size="10pt">
						<fo:table-column column-width="110mm"/>
						<fo:table-column column-width="70mm"/>
						
						<fo:table-body>
							<fo:table-row>
								
								<fo:table-cell >  <!--  TOUR DESCRIPTION, HIGHLIGHTS  -->
									<fo:block margin-bottom="2mm" xsl:use-attribute-sets="font-sans" 
										      font-family="FuturaStd-ExtraBold" font-size="14pt">
										<xsl:value-of select="ancestor::ti:TourInfo[1]/@Duration" /> DAYS 
									</fo:block>

									<fo:block border-before-style="solid" margin-right="7mm" margin-bottom="5mm"> </fo:block>

									<fo:block padding-top="0mm" padding-bottom="0mm" margin-right="6mm"
										font-family="FSAlbert-Light" font-size="10pt"> 
										<xsl:apply-templates select="ancestor::ti:TourInfo/ti:Description"/>
									</fo:block>

									<fo:block margin-top="7mm" font-size="12pt" font-family="FuturaStd-Bold">
										YOU WILL VISIT
									</fo:block>
									<fo:block margin-top="2mm" font-family="FSAlbert-Light" 
										font-size="12pt" font-weight="normal">
										<xsl:for-each select="ancestor::ti:TourInfo/ti:CountriesVisited/ti:Country">
											<xsl:value-of select="@Name"/>
											<xsl:if test="position() != last()">, </xsl:if>
										</xsl:for-each>
									</fo:block>

									<fo:block margin-top="7mm" margin-bottom="3mm" font-size="12pt" font-family="FuturaStd-Bold">
										HIGHLIGHTS
									</fo:block>
									
									<fo:block font-family="FSAlbert-Light" font-size="8.5pt" margin-right="7mm">
										<xsl:apply-templates select="ancestor::ti:TourInfo/ti:Highlights"/>
									</fo:block>
									
								</fo:table-cell>
								
								<fo:table-cell >  <!--  TOUR PHOTO, MAP  -->
									<fo:block>
										<fo:external-graphic margin-top="0mm" margin-bottom="0mm" padding-top="0mm"
											padding-bottom="0mm"  width="70mm" content-width="scale-to-fit"
											content-height="scale-to-fit" scaling="uniform">
											<xsl:attribute name="src">
												<xsl:value-of select="normalize-space(concat('url(', $apos, $photo[1]/@Url, $apos, ')'))" />
											</xsl:attribute>
										</fo:external-graphic>
									</fo:block>

									<fo:block margin-top="0mm" margin-bottom="0mm"
										padding-top="0mm" padding-bottom="0mm" font-size="0" line-height="0">
										<fo:external-graphic margin-top="0mm" margin-bottom="0mm" padding-top="0mm"
											padding-bottom="0mm"  width="70mm" content-width="scale-to-fit"
											content-height="scale-to-fit" scaling="uniform">
											<xsl:attribute name="src">
												<xsl:value-of select="normalize-space(concat('url(', $apos, $map[1]/@Url, $apos, ')'))" />
											</xsl:attribute>
										</fo:external-graphic>
									</fo:block>

									<fo:block margin-top="0mm" margin-bottom="0mm"
										padding-top="0mm" padding-bottom="0mm">
										<xsl:apply-templates select="ancestor::ti:TourInfo/ti:WhatsIncluded"/>
									</fo:block>

								</fo:table-cell>
								
							</fo:table-row>
						</fo:table-body>
					</fo:table>
				</fo:block>
				
			</fo:flow>
			
		</fo:page-sequence>
		
		<xsl:apply-templates select="ancestor::ti:TourInfo/ti:Itinerary" />
		<xsl:call-template name="tour-departures-CH" />

	</xsl:template>


	<xsl:template match="ti:TourInfo/ti:WhatsIncluded">
		<fo:block-container height="85mm" background-color="#F8F3F3"
		    keep-together.within-page="always">
		    <fo:block margin-left="3mm" margin-top="5mm" margin-bottom="2mm" 
		              font-family="FuturaStd-Bold" font-size="12pt">
		    WHAT'S INCLUDED
		    </fo:block>
			<xsl:apply-templates/>
		</fo:block-container>
	</xsl:template>

	<!-- What's Included bullet items -->
	<xsl:template match="ti:WhatsIncluded/ti:Section/ti:Text/ti:ul/ti:li">
		<fo:list-block provisional-label-separation="2mm" provisional-distance-between-starts="4mm" margin-left="3mm" margin-right="5mm">
			<fo:list-item>
				<fo:list-item-label end-indent="label-end()"><fo:block>&#8226;</fo:block></fo:list-item-label>  
				<fo:list-item-body start-indent="body-start()">
					<fo:block margin-bottom="2mm" font-family="FSAlbert-Light" font-size="8.5pt">
						<xsl:value-of select="text()"/>
					</fo:block>
				</fo:list-item-body>
			</fo:list-item>
		</fo:list-block>
	</xsl:template>
	
	
	<xsl:template match="ti:Highlights">
		<xsl:apply-templates select="ti:Section"/>
	</xsl:template>

	<xsl:template match="ti:Highlights/ti:Section">
		<xsl:apply-templates/>
	</xsl:template>

	<xsl:template match="ti:Highlights/ti:Section/ti:Title">
		<xsl:choose>
			<xsl:when test="/brochure/ti:TourInfo/@BrandCode = 'CH'"> 
				<fo:inline font-size="9pt" padding-top="0mm" padding-bottom="0mm">
					<xsl:value-of select="."/>
					<fo:inline color="#939598"> /  </fo:inline>
				</fo:inline>
			</xsl:when>
			
			<xsl:otherwise>
				<fo:block font-weight="bold" margin-top="3mm" margin-bottom="2mm" keep-with-next="2">
					<xsl:apply-templates/>
				</fo:block>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<xsl:template match="ti:Highlights/ti:Section/Text/ti:ul">
		<fo:inline font-size="8.5pt">
			<xsl:for-each select="ti:li">
				<xsl:value-of select="."/><xsl:value-of select="' '"/>
				<xsl:if test="position() != last()"> <fo:inline color="#939598" font-size="9pt">/ </fo:inline> </xsl:if>
				<xsl:if test="position() = last()"><xsl:text>&#xa0; </xsl:text></xsl:if> <!-- nbsp equivalent -->
			</xsl:for-each>
		</fo:inline>
	</xsl:template>
	
</xsl:stylesheet>
