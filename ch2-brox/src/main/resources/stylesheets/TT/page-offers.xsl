<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0"
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4">

	<xsl:template match="offers">

		<fo:page-sequence master-reference="standard-TT">
			<fo:static-content flow-name="footer-TT">
				<xsl:call-template name="footer-TT"/>
			</fo:static-content>

			<fo:flow flow-name="xsl-region-body">
				<fo:block id="offers-TT" font-family="CenturyStd-Book" font-size="34pt"
					text-align="left" margin-left="9mm" margin-top="3mm" color="red"
					margin-right="68mm">
					<xsl:value-of select="title" />
				</fo:block>

				<fo:block font-family="AvenirLTStd-Light" font-size="10pt"
					text-align="left" margin-left="9mm" margin-right="10mm" margin-top="7mm" color="black"
					margin-bottom="5mm">
					<xsl:apply-templates select="text"/>
				</fo:block>
				
				<fo:block-container>
				<!-- all positions are now relative to the block container I think -->
				<!--  row 1  -->
				<xsl:call-template name="offer">
					<xsl:with-param name="offer" select="offer[position()=1]" />
					<xsl:with-param name="left" select="'9mm'" />
					<xsl:with-param name="top" select="'0mm'" />
				</xsl:call-template>

				<xsl:call-template name="offer">
					<xsl:with-param name="offer" select="offer[position()=2]" />
					<xsl:with-param name="left" select="'94mm'" />
					<xsl:with-param name="top" select="'0mm'" />
				</xsl:call-template>
				
				<!-- row 2 -->
				<xsl:call-template name="offer">
					<xsl:with-param name="offer" select="offer[position()=3]" />
					<xsl:with-param name="left" select="'9mm'" />
					<xsl:with-param name="top" select="'50mm'" />
				</xsl:call-template>

				<xsl:call-template name="offer">
					<xsl:with-param name="offer" select="offer[position()=4]" />
					<xsl:with-param name="left" select="'94mm'" />
					<xsl:with-param name="top" select="'50mm'" />
				</xsl:call-template>

				<!-- row 3 -->
				<xsl:call-template name="offer">
					<xsl:with-param name="offer" select="offer[position()=5]" />
					<xsl:with-param name="left" select="'9mm'" />
					<xsl:with-param name="top" select="'100mm'" />
				</xsl:call-template>

				<xsl:call-template name="offer">
					<xsl:with-param name="offer" select="offer[position()=6]" />
					<xsl:with-param name="left" select="'94mm'" />
					<xsl:with-param name="top" select="'100mm'" />
				</xsl:call-template>

				<!-- row 4 -->
				<xsl:call-template name="offer">
					<xsl:with-param name="offer" select="offer[position()=7]" />
					<xsl:with-param name="left" select="'9mm'" />
					<xsl:with-param name="top" select="'150mm'" />
				</xsl:call-template>

				<xsl:call-template name="offerImage">
					<xsl:with-param name="offer" select="offer[position()=8]" />
					<xsl:with-param name="left" select="'94mm'" />
					<xsl:with-param name="top" select="'150mm'" />
				</xsl:call-template>

				</fo:block-container>


			</fo:flow>
		</fo:page-sequence>
	</xsl:template>

	<xsl:template name="offer">
		<xsl:param name="offer" />
		<xsl:param name="left" />
		<xsl:param name="top" />

		<fo:block-container position="absolute" height="44mm"
			width="79mm" overflow="hidden" background-color="#FFF1DA">


			<xsl:attribute name="left">
				<xsl:value-of select="$left" />
			</xsl:attribute>
			<xsl:attribute name="top">
				<xsl:value-of select="$top" />
			</xsl:attribute>

			<fo:block font-family="CenturyStd-Book" font-size="10pt" color="white" font-weight="bold"
				margin-left="0mm" margin-right="0mm" background-color="red"
				padding-top="3mm" padding-left="2mm" padding-bottom="2mm">
				<fo:inline font-size="11pt">
					<xsl:value-of select="translate($offer/title, $smallcase, $uppercase)" />
				</fo:inline>
			</fo:block>
			
			<fo:block-container position="absolute" left="0mm" top="10mm" width="49mm">
			<fo:block font-family="AvenirLTStd-Light" font-size="8pt" margin-left="3mm" 
				margin-right="0mm" padding-top="2mm">
					<xsl:value-of select="$offer/text" />
				</fo:block>
			</fo:block-container>
			
			<fo:block-container position="absolute" left="52mm" top="12mm">
			<fo:block margin-left="0mm" margin-right="0mm">
				<fo:external-graphic  height="25mm"
					width="25mm" content-width="scale-to-fit" content-height="scale-to-fit"
					scaling="non-uniform">
					<xsl:attribute name="src">
						<xsl:value-of select="normalize-space(concat('url(', $apos, $offer/image, $apos, ')'))" />
					</xsl:attribute>
				</fo:external-graphic>
			</fo:block>

			</fo:block-container>
				
			
		</fo:block-container>



	</xsl:template>

	<xsl:template name="offerImage">
		<xsl:param name="offer" />
		<xsl:param name="left" />
		<xsl:param name="top" />

		<fo:block-container position="absolute" height="44mm" width="79mm" overflow="hidden">
			<xsl:attribute name="left">
				<xsl:value-of select="$left" />
			</xsl:attribute>
			<xsl:attribute name="top">
				<xsl:value-of select="$top" />
			</xsl:attribute>

			<fo:block>
				<fo:external-graphic content-height="scale-to-fit" content-width="scale-to-fit"
					width="100%" height="100%">
					<xsl:attribute name="src">
						<xsl:value-of select="normalize-space(concat('url(', $apos, $offer/background, $apos, ')'))" />
					</xsl:attribute>
				</fo:external-graphic>
			</fo:block>
		</fo:block-container>

	</xsl:template>
</xsl:stylesheet>