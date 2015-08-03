<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">
	
	<xsl:template match="intro2">
		<fo:page-sequence master-reference="threecolumns-intro2-CH">
			
			<fo:static-content flow-name="footer-CH">
				<fo:block text-align="center">
					<fo:external-graphic content-height="135mm" content-width="180mm" 
						padding-bottom="0mm" padding-top="0mm" margin-left="20" margin-right="30">
						<xsl:attribute name="src">
							<xsl:value-of select="normalize-space(concat('url(', $apos, image, $apos, ')'))" />
						</xsl:attribute>
					</fo:external-graphic>
				</fo:block>
				<fo:block>
				    <xsl:call-template name="footer2-CH"/>
				</fo:block>
			</fo:static-content>
			
			<fo:flow flow-name="xsl-region-body">
				<fo:block id="intro2-CH" font-family="FuturaStd-ExtraBold" font-size="48pt" text-align="center" span="all"
					      margin-top="7mm" margin-bottom="7mm" margin-left="10mm" margin-right="10mm"  
					      border-bottom-style="solid" text-transform="uppercase">
					<xsl:value-of select="title"/>
				</fo:block>
				
				<fo:block font-family="FuturaStd-Light" font-size="10pt" margin-left="10mm" margin-right="10mm" margin-bottom="5mm" margin-top="-2mm"
				          linefeed-treatment="preserve">
					<xsl:apply-templates select="block-intro" />
				</fo:block>
				
				<fo:block font-family="FSAlbert-Light" font-size="9pt" margin-left="10mm" margin-right="10mm"
				          linefeed-treatment="preserve">
					<xsl:apply-templates select="block" />
				</fo:block>
				
			</fo:flow>
		</fo:page-sequence>
	</xsl:template>
	
<!--  	
	<xsl:template match="block">
		<fo:block font-size="10pt" xsl:use-attribute-sets="font-sans" font-weight="bold" margin-top="1mm">
			<xsl:value-of select="title"/>
		</fo:block>
		
		<fo:block font-size="8pt" margin-top="2mm" margin-bottom="2mm" xsl:use-attribute-sets="font-sans">
			<xsl:value-of select="text"/>
		</fo:block>
	</xsl:template>
-->		
</xsl:stylesheet>

