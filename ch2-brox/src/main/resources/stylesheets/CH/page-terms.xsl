<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0"
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4">

	<xsl:template match="terms">
		<fo:page-sequence master-reference="fourcolumn-pages-CH">

			<fo:static-content flow-name="footer-4col-CH">
				<xsl:call-template name="footer-4col-CH"/>
			</fo:static-content>

			<fo:static-content flow-name="fourcolumn-header-CH">
		        <fo:block-container height="28mm" overflow="hidden"
		             display-align="center" margin-bottom="10mm" border-bottom-color="white">
		          
		          	<fo:block font-family="FuturaStd-ExtraBold" font-size="34pt" font-weight="bold" 
		                border-bottom-style="solid" margin-bottom="7mm" span="all" text-transform="uppercase">
						<xsl:value-of select="Section1/Title"/>
		          	</fo:block>
		        </fo:block-container>
			</fo:static-content>

			<fo:flow flow-name="xsl-region-body">
				<fo:block id="terms" font-family="TMixLight" font-size="5pt" margin-left="2mm" margin-right="2mm">
					<xsl:apply-templates select="Section1"/>
				</fo:block>

				<fo:block span="all">
					<xsl:if test="/brochure/agent">
						<fo:block margin-left="15mm">
							<fo:block padding="3mm" font-size="10pt"
								margin-left="0mm">
								<fo:external-graphic margin-top="0mm" 
									margin-bottom="0mm" margin-left="0mm"
									padding-top="0mm" padding-bottom="0mm"
									width="60mm" content-width="scale-to-fit"
									content-height="scale-to-fit" scaling="uniform">
									<xsl:attribute name="src">
										<xsl:value-of select="normalize-space(concat('url(', $apos, /brochure/agent/image, $apos, ')'))" />
									</xsl:attribute>
								</fo:external-graphic>
							</fo:block>
						</fo:block>
						<fo:block >
							<fo:block-container width="120mm" margin-left="5mm" margin-top="10mm">
								<fo:block padding-top="1mm" text-align="Left" 
								font-style="normal" font-size="8pt" font-family="TMixLight">
								<xsl:value-of select="/brochure/agent/text"/>
								</fo:block>
							</fo:block-container>
						</fo:block>
						
					</xsl:if>
				</fo:block>

				<fo:block id="last-page"/>
				
			</fo:flow>
		</fo:page-sequence>
	</xsl:template>

	<xsl:template match="Section1">

		<fo:block font-size="6pt" margin-bottom="2mm">
			<xsl:value-of select="Text"/>
		</fo:block>
		
		<xsl:apply-templates select="Section2"/>
	</xsl:template>

	<xsl:template match="Section2">
		<fo:block font-family="FuturaStd-Bold" margin-top="1mm" font-size="7pt" text-transform="uppercase">
			<xsl:value-of select="Title"/>
		</fo:block>

		<fo:block font-family="FSAlbert-Light" font-size="5.5pt">
		<xsl:apply-templates select="Text"/>
		</fo:block>
	</xsl:template>

	<xsl:template match="bold">
		<fo:block font-family="FSAlbert-Bold" margin-top="1mm" font-size="5.5pt">
			<xsl:value-of select="."/>
		</fo:block>
	</xsl:template>


</xsl:stylesheet>
