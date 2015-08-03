<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
	
	<xsl:template match="intro1">
		<fo:page-sequence master-reference="twocolumns-CH">
			
			<fo:static-content flow-name="footer-CH">
				<xsl:call-template name="footerWithBorder-CH"/>
			</fo:static-content>
			
			<fo:flow flow-name="xsl-region-body">
				<fo:block id="intro1-CH" font-family="FuturaStd-ExtraBold" font-size="36pt" span="all" text-transform="uppercase">
					<xsl:value-of select="title"/>

					<fo:block text-align="center">
						<fo:external-graphic content-height="80mm" content-width="scale-to-fit" scaling="uniform"
							padding-bottom="10mm" border-bottom-style="solid">
							<xsl:attribute name="src">
								<xsl:value-of select="normalize-space(concat('url(', $apos, image, $apos, ')'))" />
							</xsl:attribute>
						</fo:external-graphic>
					</fo:block>
					<fo:block margin-top="3mm"> </fo:block>
				</fo:block>
				
				<fo:block font-family="FuturaStd-Bold" font-size="9pt" margin-left="2mm" margin-right="2mm">
					<xsl:apply-templates select="block" />
				</fo:block>
				
			</fo:flow>
		</fo:page-sequence>
	</xsl:template>
	<!--  margin top from 3 to 0 -->
	<xsl:template match="block">
		<fo:block font-size="8.5pt" font-family="FuturaStd-Bold" margin-top="0mm">
			<xsl:value-of select="title"/>
		</fo:block>
		
		<fo:block font-size="8pt" margin-top="1mm" margin-bottom="2mm" font-family="FSAlbert-Light">
			<xsl:value-of select="text"/>
		</fo:block>
	</xsl:template>
		
</xsl:stylesheet>

