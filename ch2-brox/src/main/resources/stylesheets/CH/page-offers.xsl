<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0"
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4">

	<xsl:template match="offers">
		<!-- Contiki -->

		<fo:page-sequence master-reference="standard-CH">

			

			<fo:flow flow-name="xsl-region-body">

				<fo:block-container id="offers-CH" position="absolute"
					top="-16mm" left="0mm">
					<fo:block text-align="center">
						<fo:external-graphic margin-top="0mm"
							margin-bottom="0mm" content-height="300mm" content-width="scale-to-fit"
							scaling="uniform">
							<xsl:attribute name="src">
								<xsl:value-of select="normalize-space(concat('url(', $apos, image, $apos, ')'))" />
							</xsl:attribute>
						</fo:external-graphic>
					</fo:block>
				</fo:block-container>
				
				<fo:block-container  position="absolute" top="265mm" height="19mm"  border-top-width="0.2mm"
					  z-index="1">
					<fo:block/>
					<fo:table table-layout="fixed" width="180mm" >
						<fo:table-column column-width='50mm' />
						<fo:table-column column-width='128mm' />
						
						<fo:table-body color="#939598">
							<fo:table-row>
								<fo:table-cell>
									 <fo:block padding-top="1mm" text-align="left"
										font-style="italic" font-size="8pt" xsl:use-attribute-sets="font-sans" linefeed-treatment="preserve">
										<!-- <xsl:value-of select="/brochure/agent/text"/> --> 
									</fo:block>
							</fo:table-cell>
								<fo:table-cell>
									<fo:block padding-top="1mm" text-align="right"
										font-style="italic" font-size="8pt" xsl:use-attribute-sets="font-sans">
										<xsl:value-of select="/brochure/title" />
										<xsl:text> / </xsl:text>
										<xsl:value-of select="$currentDate" />
										<!-- <xsl:value-of select="format-date($currentDate,'[D01] [MNn,3-9] [Y0001]')" /> -->
										<xsl:text> / </xsl:text>
										<fo:page-number />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							
						</fo:table-body>
					</fo:table>
			</fo:block-container>

			</fo:flow>
		</fo:page-sequence>
	</xsl:template>


</xsl:stylesheet>