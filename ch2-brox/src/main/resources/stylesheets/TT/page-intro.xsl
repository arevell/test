<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

	<xsl:template match="intro">
		<fo:page-sequence master-reference="standard-TT">
			<fo:static-content flow-name="footer-TT">
				<xsl:call-template name="footer-TT"/>
			</fo:static-content>

			<fo:flow flow-name="xsl-region-body">
				<fo:block-container id="intro-TT" position="absolute"
					z-index="-1" margin-left="4mm" margin-right="3mm"
					 margin-top="20mm" margin-bottom="20mm">
					<fo:block text-align="left">
						<fo:external-graphic
							content-height="262mm" content-width="172mm">
							<xsl:attribute name="src">
								<xsl:value-of select="normalize-space(concat('url(', $apos, image, $apos, ')'))" />
							</xsl:attribute>
						</fo:external-graphic>
					</fo:block>
				</fo:block-container>

				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=1]" />
					<xsl:with-param name="fontTitle" select="'AvenirLTStd-Heavy'" />
					<xsl:with-param name="sizeTitle" select="'10pt'" />
					<xsl:with-param name="colourTitle" select="'black'" />
					<xsl:with-param name="sizeText" select="'10pt'" />
					<xsl:with-param name="left" select="'4mm'" />
					<xsl:with-param name="top" select="'0mm'" />
					<xsl:with-param name="right" select="'105mm'" />
				</xsl:call-template>

				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=2]" />
					<xsl:with-param name="fontTitle" select="'CenturyStd-Book'" />
					<xsl:with-param name="sizeTitle" select="'11pt'" />
					<xsl:with-param name="colourTitle" select="'red'" />
					<xsl:with-param name="sizeText" select="'9pt'" />
					<xsl:with-param name="left" select="'4mm'" />
					<xsl:with-param name="top" select="'47mm'" />
					<xsl:with-param name="right" select="'105mm'" />
				</xsl:call-template>

				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=3]" />
					<xsl:with-param name="fontTitle" select="'CenturyStd-Book'" />
					<xsl:with-param name="sizeTitle" select="'11pt'" />
					<xsl:with-param name="colourTitle" select="'red'" />
					<xsl:with-param name="sizeText" select="'9pt'" />
					<xsl:with-param name="left" select="'117mm'" />
					<xsl:with-param name="top" select="'65mm'" />
					<xsl:with-param name="right" select="'4mm'" />
				</xsl:call-template>

				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=4]" />
					<xsl:with-param name="fontTitle" select="'CenturyStd-Book'" />
					<xsl:with-param name="sizeTitle" select="'11pt'" />
					<xsl:with-param name="colourTitle" select="'red'" />
					<xsl:with-param name="sizeText" select="'9pt'" />
					<xsl:with-param name="left" select="'117mm'" />
					<xsl:with-param name="top" select="'102mm'" />
					<xsl:with-param name="right" select="'4mm'" />
				</xsl:call-template>

				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=5]" />
					<xsl:with-param name="fontTitle" select="'CenturyStd-Book'" />
					<xsl:with-param name="sizeTitle" select="'11pt'" />
					<xsl:with-param name="colourTitle" select="'red'" />
					<xsl:with-param name="sizeText" select="'9pt'" />
					<xsl:with-param name="left" select="'82mm'" />
					<xsl:with-param name="top" select="'143mm'" />
					<xsl:with-param name="right" select="'4mm'" />
				</xsl:call-template>

				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=6]" />
					<xsl:with-param name="fontTitle" select="'CenturyStd-Book'" />
					<xsl:with-param name="sizeTitle" select="'11pt'" />
					<xsl:with-param name="colourTitle" select="'red'" />
					<xsl:with-param name="sizeText" select="'9pt'" />
					<xsl:with-param name="left" select="'82mm'" />
					<xsl:with-param name="top" select="'175mm'" />
					<xsl:with-param name="right" select="'4mm'" />
				</xsl:call-template>

				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=7]" />
					<xsl:with-param name="fontTitle" select="'CenturyStd-Book'" />
					<xsl:with-param name="sizeTitle" select="'11pt'" />
					<xsl:with-param name="colourTitle" select="'red'" />
					<xsl:with-param name="sizeText" select="'9pt'" />
					<xsl:with-param name="left" select="'82mm'" />
					<xsl:with-param name="top" select="'207mm'" />
					<xsl:with-param name="right" select="'4mm'" />
				</xsl:call-template>

				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=8]" />
					<xsl:with-param name="fontTitle" select="'CenturyStd-Book'" />
					<xsl:with-param name="sizeTitle" select="'11pt'" />
					<xsl:with-param name="colourTitle" select="'red'" />
					<xsl:with-param name="sizeText" select="'9pt'" />
					<xsl:with-param name="left" select="'82mm'" />
					<xsl:with-param name="top" select="'235mm'" />
					<xsl:with-param name="right" select="'4mm'" />
				</xsl:call-template>

			</fo:flow>
		</fo:page-sequence>
	</xsl:template>

	<xsl:template name="block">
		<xsl:param name="fontTitle" />
		<xsl:param name="sizeTitle" />
		<xsl:param name="colourTitle" />
		<xsl:param name="sizeText" />
		<xsl:param name="block" />
		<xsl:param name="left" />
		<xsl:param name="top" />
		<xsl:param name="right" />

		<fo:block-container position="absolute">
			<xsl:attribute name="left">
				<xsl:value-of select="$left" />
			</xsl:attribute>
			<xsl:attribute name="top">
				<xsl:value-of select="$top" />
			</xsl:attribute>
			<xsl:attribute name="right">
				<xsl:value-of select="$right" />
			</xsl:attribute>

			<fo:block>
				<fo:block font-weight="bold" margin-bottom="1mm" text-align="left">
					<xsl:attribute name="font-family">
						<xsl:value-of select="$fontTitle" />
					</xsl:attribute>
					<xsl:attribute name="color">
						<xsl:value-of select="$colourTitle" />
					</xsl:attribute>
					<xsl:attribute name="font-size">
						<xsl:value-of select="$sizeTitle" />
					</xsl:attribute>
					<xsl:value-of select="$block/title" />
				</fo:block>
				<fo:block font-family="AvenirLTStd-Light" margin-left="0mm" margin-right="0mm" text-align="left" line-height="1.3">
					<xsl:attribute name="font-size">
						<xsl:value-of select="$sizeText" />
					</xsl:attribute>
					<xsl:apply-templates select="$block/text"/>
				</fo:block>
			</fo:block>
		</fo:block-container>
	</xsl:template>
</xsl:stylesheet>