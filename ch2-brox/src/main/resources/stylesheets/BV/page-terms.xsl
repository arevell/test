<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

	<xsl:template match="terms">
		<fo:page-sequence master-reference="threecolumns">
			
			<xsl:call-template name="borders" />

			<fo:flow flow-name="xsl-region-body">
				<fo:block id="terms" span="all" xsl:use-attribute-sets="font-serif"
					font-size="28pt" text-align="center" margin-top="5mm"
					margin-bottom="0mm">
					<xsl:value-of select="Title"/>
				</fo:block>
				<!--  
				<fo:block span="all" xsl:use-attribute-sets="font-serif"
					font-size="22pt" text-align="center" color="red" margin-top="2mm"
					margin-bottom="5mm">
					AND OTHER IMPORTANT INFORMATION
				</fo:block>
				-->

				<fo:block text-align="justify" font-size="8pt" margin-left="2mm"
					margin-right="2mm">


					<xsl:apply-templates select="Section1" />

				</fo:block>

				<fo:block span="all">
					<xsl:if test="/brochure/agent">
						<fo:block margin-left="20mm" margin-right="20mm"
							text-align-last="justify">
							<fo:leader leader-pattern="rule" />
						</fo:block>
						
						<fo:block margin-left="20mm" margin-top="10mm">
						<fo:table table-layout="fixed" width="140mm"
							border-color="black" border-width="0.2mm" border-style="solid">
							<fo:table-column column-width='70mm' />
							<fo:table-column column-width='70mm' />

							<fo:table-body>
								<fo:table-row>
									<fo:table-cell margin-left="0mm" border-color="black" border-width="0.2mm" border-style="solid">
										<fo:block padding="3mm" text-align="left" margin-left="0mm" xsl:use-attribute-sets="font-sans"
											font-size="10pt" linefeed-treatment="preserve">
											
											<xsl:value-of select="/brochure/agent/text"/>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell margin-left="0mm" border-color="black" border-width="0.2mm" border-style="solid">
										<fo:block padding="3mm" font-size="10pt" margin-left="0mm">
											<fo:external-graphic margin-top="0mm"
											margin-bottom="0mm" margin-left="0mm" padding-top="0mm" padding-bottom="0mm"
											width="60mm" content-width="scale-to-fit"
											content-height="scale-to-fit" scaling="uniform">
											<xsl:attribute name="src">
												<xsl:value-of select="normalize-space(concat('url(', $apos, /brochure/agent/image, $apos, ')'))" />
											</xsl:attribute>
										</fo:external-graphic>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

							</fo:table-body>
						</fo:table>
						</fo:block>
					</xsl:if>
				</fo:block>

				<fo:block id="last-page" />


			</fo:flow>
		</fo:page-sequence>
	</xsl:template>

	<xsl:template match="Section1">
		<fo:block font-size="10pt" font-weight="bold" text-align="center" margin-top="1mm">
			<xsl:value-of select="Title" />
		</fo:block>
		<xsl:apply-templates select="Text|Section2" />
	</xsl:template>
	
	<xsl:template match="Section2">
		<fo:block font-weight="bold" text-align="center" margin-top="1mm">
			<xsl:value-of select="Title" />
		</fo:block>
		<xsl:apply-templates select="Text"/>
	</xsl:template>

	
</xsl:stylesheet>