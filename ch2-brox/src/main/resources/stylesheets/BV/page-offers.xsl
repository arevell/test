<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

	<xsl:template match="offers">
		<fo:page-sequence master-reference="standard">
			
			<xsl:call-template name="borders" />
			
			<fo:flow flow-name="xsl-region-body">
				<fo:block id="offers" xsl:use-attribute-sets="font-serif" font-size="24pt"
					text-align="center" margin-top="0mm" background-color="red" color="white"
					margin-bottom="2mm" padding-top="3mm" padding-bottom="3mm">
					<xsl:value-of select="translate(title, $smallcase, $uppercase)" />
				</fo:block>

				<fo:block xsl:use-attribute-sets="font-sans" font-size="10pt"
					text-align="left" margin-left="5mm" margin-right="5mm" margin-top="2mm" padding-top="2mm" background-color="white" color="black"
					margin-bottom="5mm">
					<xsl:value-of select="text" />
				</fo:block>
				
				<fo:block-container>
				<!-- all positions are now relative to the block container I think -->
				<!--  row 1  -->
				<xsl:call-template name="offer">
					<xsl:with-param name="offer" select="offer[position()=1]" />
					<xsl:with-param name="num" select="'1'"/>
					<xsl:with-param name="left" select="'7.5mm'" />
					<xsl:with-param name="top" select="'0mm'" />
				</xsl:call-template>

				<xsl:call-template name="offer">
					<xsl:with-param name="offer" select="offer[position()=2]" />
					<xsl:with-param name="num" select="'2'"/>
					<xsl:with-param name="left" select="'95.5mm'" />
					<xsl:with-param name="top" select="'0mm'" />
				</xsl:call-template>
				
				<!-- row 2 -->
				<xsl:call-template name="offer">
					<xsl:with-param name="offer" select="offer[position()=3]" />
					<xsl:with-param name="num" select="'3'"/>
					<xsl:with-param name="left" select="'7.5mm'" />
					<xsl:with-param name="top" select="'50mm'" />
				</xsl:call-template>

				<xsl:call-template name="offer">
					<xsl:with-param name="offer" select="offer[position()=4]" />
					<xsl:with-param name="num" select="'4'"/>
					<xsl:with-param name="left" select="'95.5mm'" />
					<xsl:with-param name="top" select="'50mm'" />
				</xsl:call-template>

				<!-- row 3 -->
				<xsl:call-template name="offer">
					<xsl:with-param name="offer" select="offer[position()=5]" />
					<xsl:with-param name="num" select="'5'"/>
					<xsl:with-param name="left" select="'7.5mm'" />
					<xsl:with-param name="top" select="'100mm'" />
				</xsl:call-template>

				<xsl:call-template name="offer">
					<xsl:with-param name="offer" select="offer[position()=6]" />
					<xsl:with-param name="num" select="'6'"/>
					<xsl:with-param name="left" select="'95.5mm'" />
					<xsl:with-param name="top" select="'100mm'" />
				</xsl:call-template>

				<!-- row 4 -->
				<xsl:call-template name="offer">
					<xsl:with-param name="offer" select="offer[position()=7]" />
					<xsl:with-param name="num" select="'7'"/>
					<xsl:with-param name="left" select="'7.5mm'" />
					<xsl:with-param name="top" select="'150mm'" />
				</xsl:call-template>

				<xsl:call-template name="offer">
					<xsl:with-param name="offer" select="offer[position()=8]" />
					<xsl:with-param name="num" select="'8'"/>
					<xsl:with-param name="left" select="'95.5mm'" />
					<xsl:with-param name="top" select="'150mm'" />
				</xsl:call-template>
				
				</fo:block-container>


			</fo:flow>
		</fo:page-sequence>
	</xsl:template>

	<xsl:template name="offer">
		<xsl:param name="offer" />
		<xsl:param name="num"/>
		<xsl:param name="left" />
		<xsl:param name="top" />

		<fo:block-container position="absolute" height="41mm"
			width="78mm" padding-bottom="3mm" overflow="hidden" background-color="#FFF1DA"
			border-style="dashed" border-width="0.2mm" border-color="#FDB813">


			<xsl:attribute name="left">
				<xsl:value-of select="$left" />
			</xsl:attribute>
			<xsl:attribute name="top">
				<xsl:value-of select="$top" />
			</xsl:attribute>

			<fo:block font-size="14pt" color="white" font-weight="bold"
				margin-left="0mm" margin-right="0mm" background-color="#FDB813"
				padding-top="1.5mm" padding-left="1mm" padding-bottom="1mm" 
				background-image="url('offers2.svg')" background-repeat="no-repeat">
				<xsl:value-of select="$num" /> 
				<xsl:text> </xsl:text>
				<fo:inline font-size="10pt" padding-left="3mm"><xsl:value-of select="translate($offer/title, $smallcase, $uppercase)" /></fo:inline>
			</fo:block>
			
			<fo:block-container position="absolute" left="0mm" top="10mm" width="45mm">
			<fo:block font-size="8pt" margin-left="3mm" margin-right="0mm">
					<xsl:value-of select="$offer/text" />
				</fo:block>
			</fo:block-container>
			
			<fo:block-container position="absolute" left="50mm" top="10mm">
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

</xsl:stylesheet>