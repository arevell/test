<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0"
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4">

	<xsl:template name="page-frontcover">
		
		<fo:page-sequence master-reference="cover-CH">
			<fo:flow flow-name="xsl-region-body">

				<fo:block-container id="cover-CH" position="absolute" top="11mm" left="0mm">
					<fo:block text-align="center">
						<fo:external-graphic margin-top="0mm" margin-bottom="0mm" 
							content-height="160mm" content-width="scale-to-fit" scaling="uniform">
							<xsl:attribute name="src">
								<xsl:value-of select="normalize-space(concat('url(', $apos, frontispiece/image, $apos, ')'))" />
							</xsl:attribute>
						</fo:external-graphic>
					</fo:block>
				</fo:block-container>

				<fo:block-container position="absolute" top="190mm" 
					left="13.5mm" right="21mm" z-index="1">
					<fo:block margin-left="2mm" margin-right="2mm" font-size="50pt" color="black" font-family="FuturaStd-ExtraBold">
						<xsl:value-of select="translate(title, $smallcase, $uppercase)"/>&#x00B4;S  <!-- &#x00B4; = acute accent char -->
					</fo:block>
					
					<fo:block margin-left="2mm" font-size="38pt" color="black" font-family="FSAlbert-Light">
						Brochure
					</fo:block>
				</fo:block-container>
				
				<fo:block-container position="absolute" top="265mm" left="13.5mm"  z-index="1">
					<fo:block margin-top="10mm" margin-left="2mm" width="40mm" font-size="30pt" color="black" font-family="Veneer Clean">
						#NOREGRETS
					</fo:block>
				</fo:block-container>
				
				<fo:block-container position="absolute" top="269mm" left="161mm" right="21mm" z-index="1">
					<fo:block text-align="center">
						<fo:external-graphic margin-top="0mm" margin-bottom="0mm" 
							content-height="16mm" content-width="scale-to-fit" scaling="uniform">
							<xsl:attribute name="src">
								<xsl:value-of select="normalize-space(concat('url(', $apos, frontispiece/logo, $apos, ')'))" />
							</xsl:attribute>
						</fo:external-graphic>
					</fo:block>
				</fo:block-container>
				
				

			</fo:flow>
		</fo:page-sequence>

	</xsl:template>
</xsl:stylesheet>