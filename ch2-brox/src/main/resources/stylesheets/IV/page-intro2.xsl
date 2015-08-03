<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

	<xsl:template match="intro2">
		<fo:page-sequence master-reference="standard">
			<xsl:call-template name="borders" />

			<fo:flow flow-name="xsl-region-body">

				<fo:block id="intro2" xsl:use-attribute-sets="font-sans" font-size="24pt"
					text-align="center" margin-top="0mm" padding-top="0mm"
					border-top-style="solid" border-top-width="7mm" border-color="#002664"
					line-height="0mm">

					<fo:external-graphic margin-top="0mm"
						margin-bottom="0mm" padding-top="3.9mm" padding-bottom="0mm"
						width="180.2mm" height="100mm" content-width="scale-to-fit" content-height="scale-to-fit"
						scaling="non-uniform">
						<xsl:attribute name="src">
							<xsl:value-of select="normalize-space(concat('url(', $apos, image, $apos, ')'))" />
						</xsl:attribute>
					</fo:external-graphic>
				</fo:block>

				<fo:block xsl:use-attribute-sets="font-serif" color="#002664"
					font-size="22pt" text-align="left" margin-top="0mm" padding-top="5mm"
					margin-left="5mm">

					<xsl:value-of select="translate(title, $smallcase, $uppercase)" />

				</fo:block>

				<!--  row 1  -->
				<xsl:call-template name="block2">
					<xsl:with-param name="block" select="block[position()=1]" />
					<xsl:with-param name="left" select="'7.5mm'" />
					<xsl:with-param name="top" select="'126mm'" />
				</xsl:call-template>

				<xsl:call-template name="block2">
					<xsl:with-param name="block" select="block[position()=2]" />
					<xsl:with-param name="left" select="'95.5mm'" />
					<xsl:with-param name="top" select="'126mm'" />
				</xsl:call-template>

				<!-- row 2 -->
				<xsl:call-template name="block2">
					<xsl:with-param name="block" select="block[position()=3]" />
					<xsl:with-param name="left" select="'7.5mm'" />
					<xsl:with-param name="top" select="'152mm'" />
				</xsl:call-template>

				<xsl:call-template name="block2">
					<xsl:with-param name="block" select="block[position()=4]" />
					<xsl:with-param name="left" select="'95.5mm'" />
					<xsl:with-param name="top" select="'152mm'" />
				</xsl:call-template>

				<!-- row 3 -->
				<xsl:call-template name="block2">
					<xsl:with-param name="block" select="block[position()=5]" />
					<xsl:with-param name="left" select="'7.5mm'" />
					<xsl:with-param name="top" select="'180mm'" />
				</xsl:call-template>

				<xsl:call-template name="block2">
					<xsl:with-param name="block" select="block[position()=6]" />
					<xsl:with-param name="left" select="'95.5mm'" />
					<xsl:with-param name="top" select="'180mm'" />
				</xsl:call-template>

				<!-- row 4 -->
				<xsl:call-template name="block2">
					<xsl:with-param name="block" select="block[position()=7]" />
					<xsl:with-param name="left" select="'7.5mm'" />
					<xsl:with-param name="top" select="'208mm'" />
				</xsl:call-template>

				<xsl:call-template name="block2">
					<xsl:with-param name="block" select="block[position()=8]" />
					<xsl:with-param name="left" select="'95.5mm'" />
					<xsl:with-param name="top" select="'208mm'" />
				</xsl:call-template>



				<fo:block-container position="absolute" left="0mm"
					width="100%" top="245mm" z-index="1">
					<fo:block span="all" xsl:use-attribute-sets="font-sans"
						font-size="16pt" font-style="italic" text-align="right"
						margin-top="5mm" margin-right="5mm">
						<xsl:value-of select="footer" />
					</fo:block>
				</fo:block-container>
			</fo:flow>


		</fo:page-sequence>

	</xsl:template>

	<xsl:template name="block2">
		<xsl:param name="block" />
		<xsl:param name="left" />
		<xsl:param name="top" />

		<fo:block-container position="absolute" height="25mm"
			width="78mm" padding-left="3mm" padding-top="2mm" padding-bottom="0mm"
			padding-right="3mm" overflow="hidden">
			<xsl:attribute name="left">
				<xsl:value-of select="$left" />
			</xsl:attribute>
			<xsl:attribute name="top">
				<xsl:value-of select="$top" />
			</xsl:attribute>

			<fo:block margin-left="0mm" margin-right="0mm">
				<fo:list-block provisional-label-separation="2mm"
					provisional-distance-between-starts="5mm">
					<fo:list-item>
						<fo:list-item-label end-indent="label-end()">
							<fo:block>
								<fo:external-graphic src="url('red_square.jpg')" />
							</fo:block>
						</fo:list-item-label>
						<fo:list-item-body start-indent="body-start()">
							<fo:block font-size="10pt" color="#002664" font-weight="bold">
								<xsl:value-of select="translate($block/title, $smallcase, $uppercase)" />
							</fo:block>
						</fo:list-item-body>
					</fo:list-item>
					<fo:list-item>
						<fo:list-item-label end-indent="label-end()">
							<fo:block />
						</fo:list-item-label>
						<fo:list-item-body start-indent="body-start()">
							<fo:block font-size="8pt" margin-left="0mm"
								margin-right="0mm">
								<xsl:value-of select="$block/text" />
							</fo:block>
						</fo:list-item-body>
					</fo:list-item>
				</fo:list-block>



			</fo:block>
		</fo:block-container>


	</xsl:template>


</xsl:stylesheet>