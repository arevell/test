<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0"
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4"
>
	
	<xsl:template name="page-frontcover">
		<fo:page-sequence master-reference="cover">
			<fo:flow flow-name="xsl-region-body">

				<fo:block margin-top="5mm" text-align="center">
					<fo:external-graphic margin-top="0mm"
						margin-bottom="0mm" padding-top="0mm" padding-bottom="0mm" height="50mm"
						width="100mm" content-width="scale-to-fit" content-height="scale-to-fit">
						<xsl:attribute name="src">
							<xsl:value-of select="normalize-space(concat('url(', $apos, frontispiece/logo, $apos, ')'))" />
						</xsl:attribute>
					</fo:external-graphic>

				</fo:block>
				 
				<fo:block-container position="absolute" top="30mm"
					left="1mm">
					
					<fo:block margin-left="10mm" margin-right="10mm"
						text-align="center" font-size="48pt" color="#CCCCCC"
						xsl:use-attribute-sets="font-serif">
						<xsl:value-of select="translate(title, $smallcase, $uppercase)" />
					</fo:block>

					

				</fo:block-container>
				
				<fo:block-container position="absolute" top="29mm"
					left="0mm" z-index="1">
					<fo:block margin-left="10mm" margin-right="10mm"
						text-align="center" font-size="48pt" color="#002664"
						xsl:use-attribute-sets="font-serif">
						<xsl:value-of select="translate(title, $smallcase, $uppercase)" />
					</fo:block>

					
				</fo:block-container>

				

				<fo:block-container position="absolute" top="115mm"
					left="0mm">
					<!-- absolute positioning for this -->
					
					
					<fo:block text-align="center" >
						<fo:external-graphic margin-top="0mm"
							margin-bottom="0mm" padding-top="0mm" padding-bottom="0mm" width="166mm" height="110.6mm"
							content-width="scale-to-fit" content-height="scale-to-fit" scaling="non-uniform">
							<xsl:attribute name="src">
								<xsl:value-of select="normalize-space(concat('url(', $apos, frontispiece/image, $apos, ')'))" />
							</xsl:attribute>
						</fo:external-graphic>
					</fo:block>
					
					<fo:block text-align="center" color="#002664">
						<xsl:value-of select="frontispiece/tag" />
					</fo:block>
					
					<fo:block margin-top="8mm" text-align="center" color="#002664">
						<fo:basic-link>
							<xsl:attribute name="external-destination">
								<xsl:value-of select="normalize-space(concat('url(', $apos, 'http://', frontispiece/web, $apos, ')'))" />
							</xsl:attribute>
							<xsl:value-of select="frontispiece/web" />
						</fo:basic-link>
					</fo:block>
					
				</fo:block-container>


			</fo:flow>
		</fo:page-sequence>

	</xsl:template>
</xsl:stylesheet>