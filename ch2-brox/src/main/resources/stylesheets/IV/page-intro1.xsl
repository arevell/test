<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

	<xsl:template match="intro1">
		<fo:page-sequence master-reference="standard">
			<fo:static-content flow-name="footer">
				<xsl:call-template name="footer" />
			</fo:static-content>

			<fo:flow flow-name="xsl-region-body">

				<fo:block xsl:use-attribute-sets="font-serif" color="#002664" font-size="24pt"
					text-align="center" margin-top="0mm" padding-top="5mm"
					border-top-style="solid" border-top-width="7mm" border-color="#002664">
					<xsl:value-of select="translate(title, $smallcase, $uppercase)" />
				</fo:block>

				<!--  row 1  -->
				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=1]" />
					<xsl:with-param name="left" select="'7.5mm'" />
					<xsl:with-param name="top" select="'40mm'" />
				</xsl:call-template>

				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=2]" />
					<xsl:with-param name="left" select="'95.5mm'" />
					<xsl:with-param name="top" select="'40mm'" />
				</xsl:call-template>

				<!-- row 2 -->
				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=3]" />
					<xsl:with-param name="left" select="'7.5mm'" />
					<xsl:with-param name="top" select="'90mm'" />
				</xsl:call-template>

				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=4]" />
					<xsl:with-param name="left" select="'95.5mm'" />
					<xsl:with-param name="top" select="'90mm'" />
				</xsl:call-template>

				<!-- row 3 -->
				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=5]" />
					<xsl:with-param name="left" select="'7.5mm'" />
					<xsl:with-param name="top" select="'140mm'" />
				</xsl:call-template>

				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=6]" />
					<xsl:with-param name="left" select="'95.5mm'" />
					<xsl:with-param name="top" select="'140mm'" />
				</xsl:call-template>

				<!-- row 4 -->
				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=7]" />
					<xsl:with-param name="left" select="'7.5mm'" />
					<xsl:with-param name="top" select="'190mm'" />
				</xsl:call-template>

				<xsl:call-template name="block">
					<xsl:with-param name="block" select="block[position()=8]" />
					<xsl:with-param name="left" select="'95.5mm'" />
					<xsl:with-param name="top" select="'190mm'" />
				</xsl:call-template>



				<fo:block-container position="absolute" left="0mm"
					width="100%" top="235mm" z-index="1">
					<fo:block span="all" xsl:use-attribute-sets="font-sans"
						font-size="16pt" text-align="center" margin-top="5mm">
						<xsl:value-of select="footer" />
					</fo:block>
				</fo:block-container>
			</fo:flow>


		</fo:page-sequence>

	</xsl:template>

	<xsl:template name="block">
		<xsl:param name="block" />
		<xsl:param name="left" />
		<xsl:param name="top" />

		<fo:block-container position="absolute" height="41mm"
			width="78mm" border-style="solid" border-color="black" border-width="0.1mm"
			padding-left="3mm" padding-top="2mm" padding-bottom="3mm" padding-right="3mm" overflow="hidden">
			<xsl:attribute name="left">
				<xsl:value-of select="$left" />
			</xsl:attribute>
			<xsl:attribute name="top">
				<xsl:value-of select="$top" />
			</xsl:attribute>

			<fo:block margin-left="0mm" margin-right="0mm">
				<fo:external-graphic margin-top="0mm"
					margin-bottom="0mm" padding-top="0mm" padding-bottom="0mm" height="19.25mm"
					width="77mm" content-width="scale-to-fit" content-height="scale-to-fit"
					scaling="non-uniform">
					<xsl:attribute name="src">
						<xsl:value-of select="normalize-space(concat('url(', $apos, $block/image, $apos, ')'))" />
					</xsl:attribute>
				</fo:external-graphic>

				<fo:block font-size="10pt" color="#002664" font-weight="bold"
					margin-left="0mm" margin-right="0mm" margin-top="1mm" margin-bottom="1mm">
					<xsl:value-of select="translate($block/title, $smallcase, $uppercase)" />
				</fo:block>
				<fo:block font-size="8pt" margin-left="0mm" margin-right="0mm">
					<xsl:value-of select="$block/text" />
				</fo:block>
			</fo:block>
		</fo:block-container>


	</xsl:template>


</xsl:stylesheet>