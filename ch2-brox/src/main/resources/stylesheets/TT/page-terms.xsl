<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

	<xsl:template match="terms">
		<fo:page-sequence master-reference="threecolumns-TT">
			
			<fo:static-content flow-name="footer-TT">
				<xsl:call-template name="footer-TT"/>
			</fo:static-content>

			<fo:flow flow-name="xsl-region-body">
				<fo:block id="terms-TT" span="all" font-family="CenturyStd-Book" font-size="24pt" text-align="center" 
					margin-top="2mm" color="red" font-stretch="expanded">
					<xsl:value-of select="translate(Title, $smallcase, $uppercase)"/>
				</fo:block>


					<xsl:apply-templates select="Section1" />

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
										<fo:block padding="3mm" text-align="left" margin-left="0mm" font-family="AvenirLTStd-Light"
											font-size="8pt" linefeed-treatment="preserve">
											
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
		<fo:block font-family="CenturyStd-Book" span="all" font-size="14pt" text-align="center">
			<xsl:value-of select="translate(Title, $smallcase, $uppercase)" />
		</fo:block>
		<xsl:apply-templates select="Text|Section2" />
		<xsl:if test="Image">
			<fo:block text-align="center">
				<fo:external-graphic content-width="scale-to-fit"
					width="100%">
					<xsl:attribute name="src">
						<xsl:value-of select="normalize-space(concat('url(', $apos, Image, $apos, ')'))" />
					</xsl:attribute>
				</fo:external-graphic>
			</fo:block>
		</xsl:if>
	</xsl:template>
	
	<xsl:template match="Section2">
		<fo:block font-family="MyriadPro-Cond" font-size="4pt" font-weight="bold" text-align="center" margin-top="0.2mm">
			<xsl:value-of select="Title" />
		</fo:block>
		<fo:block text-align="justify" font-family="MyriadPro-Cond" font-size="4pt" margin-left="1mm"
			margin-right="1mm" font-weight="normal">
			<xsl:apply-templates select="Text"/>
		</fo:block>
	</xsl:template>

	
</xsl:stylesheet>