<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

	<xsl:template match="questions">
		<fo:page-sequence master-reference="fourcolumn-pages-CH">
			
			<fo:static-content flow-name="footer-4col-CH">
				<xsl:call-template name="footer-4col-CH"/>
			</fo:static-content>
			
			<!-- first questions page -->
			<fo:static-content flow-name="fourcolumn-header-CH">
		        <fo:block-container height="19mm" overflow="hidden"
		             display-align="center" margin-bottom="0mm" border-bottom-color="white">
		          
		          	<fo:block font-family="FuturaStd-ExtraBold" font-size="34pt" font-weight="bold" 
		                border-bottom-style="solid" margin-bottom="7mm" span="all" text-transform="uppercase">
						<xsl:value-of select="title"/>
		          	</fo:block>
		        </fo:block-container>
			</fo:static-content>
			
			<fo:flow flow-name="xsl-region-body">

				<fo:block id="questions-CH" font-family="FSAlbert-Bold" font-size="10pt" span="all"
				          margin-left="4mm" margin-bottom="4mm">
				    Answers to frequently asked questions &amp; things you need to know:
				</fo:block>
  
				<fo:block font-size="8pt" margin-left="2mm" margin-right="2mm">
					<xsl:apply-templates select="question" />
				</fo:block>

			</fo:flow>
		</fo:page-sequence>
	</xsl:template>

	<xsl:template match="question">
		<fo:block font-family="FuturaStd-Bold" font-size="9pt" margin-left="2mm" margin-top="1.5mm" margin-bottom="1mm" >
			<xsl:value-of select="title" />
		</fo:block>
		
		<fo:block font-family="FSAlbert-Light" font-size="7pt" margin-left="2mm" margin-right="2mm"> 
			<xsl:apply-templates select="paragraph"/>
		</fo:block>
	</xsl:template>
	
</xsl:stylesheet>