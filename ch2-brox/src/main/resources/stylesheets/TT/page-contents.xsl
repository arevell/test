<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4"
	version="2.0">


	<xsl:template name="page-contents">
		<fo:page-sequence master-reference="standard-TT">

			<fo:static-content flow-name="footer-TT">
				<xsl:call-template name="footer-TT"/>
			</fo:static-content>


			<fo:flow flow-name="xsl-region-body">

				<!-- TABLE OF CONTENTS -->
				<fo:block id="contents-TT" margin-top="10mm" margin-left="0mm"
					margin-right="0mm">

					<fo:table table-layout="fixed" width="205mm" font-size="10pt">
						<fo:table-column column-width="70mm" />
						<fo:table-column column-width="140mm" />

						<fo:table-body>

							<fo:table-row>
								<fo:table-cell>
									<fo:block padding-top="1mm" />
								</fo:table-cell>
								<fo:table-cell>
									<fo:block font-family="CenturyStd-Book" font-size="34pt"
										color="red" padding-top="1mm">
										<xsl:text>Contents</xsl:text>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>

							<fo:table-row> 
								<fo:table-cell>
									<fo:block font-family="AvenirLTStd-Light" font-size="12pt"
										text-align="right">
										<fo:page-number-citation ref-id="intro-TT" />.
									</fo:block>
								</fo:table-cell>

								<fo:table-cell margin-left="3mm" wrap-option="wrap"
									margin-right="25mm">
									<fo:block font-family="AvenirLTStd-Light" font-size="12pt"
										padding-bottom="1mm" padding-left="0mm">
										<fo:basic-link internal-destination="intro-TT">
											<xsl:value-of select="frontispiece/intro/title" />
										</fo:basic-link>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>

							<fo:table-row> 
								<fo:table-cell>
									<fo:block font-family="AvenirLTStd-Light" font-size="12pt"
										text-align="right" padding-top="1mm" padding-bottom="0mm">
										<fo:page-number-citation ref-id="offers-TT" />.
									</fo:block>
								</fo:table-cell>
								<fo:table-cell margin-left="3mm">
									<fo:block font-family="AvenirLTStd-Light" font-size="12pt"
										padding-top="1mm" padding-bottom="1mm" padding-left="0mm">
										<fo:basic-link internal-destination="offers-TT">
											<xsl:text>Promotions</xsl:text>
										</fo:basic-link>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>

							<xsl:for-each select="ti:TourInfo">
								<fo:table-row>
									<fo:table-cell>
										<fo:block font-family="AvenirLTStd-Heavy" font-size="12pt" font-weight="bold"
											text-align="right" padding-top="1mm" padding-bottom="0mm">
											<fo:page-number-citation ref-id="{@MVCode}" />.
										</fo:block>
									</fo:table-cell>
									<fo:table-cell margin-left="3mm">
										<fo:block font-family="AvenirLTStd-Heavy" font-size="12pt"
											font-weight="bold" padding-top="1mm" padding-bottom="0mm"
											wrap-option="wrap" margin-right="25mm">
											<fo:basic-link internal-destination="{@MVCode}">
												<xsl:value-of select="ti:TourName" />
											</fo:basic-link>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

							</xsl:for-each>

							<fo:table-row> <!-- Terms and Conditions -->
								<fo:table-cell>
									<fo:block font-family="AvenirLTStd-Light" font-size="12pt"
										text-align="right" padding-top="2mm" padding-bottom="0mm">
										<fo:page-number-citation ref-id="terms-TT" />.
									</fo:block>
								</fo:table-cell>
								<fo:table-cell margin-left="3mm">
									<fo:block font-family="AvenirLTStd-Light" font-size="12pt"
										padding-top="2mm" padding-bottom="0mm" padding-left="0mm">
										<fo:basic-link internal-destination="terms-TT">
											<xsl:text>Terms &amp; Conditions </xsl:text>
										</fo:basic-link>
									</fo:block>
								</fo:table-cell>
							</fo:table-row>

						</fo:table-body>

					</fo:table>
				</fo:block>

				<!-- FIXED IMAGE -->

				<fo:block-container margin-top="50mm">
					<fo:block>
						<fo:external-graphic margin-bottom="0mm"
							content-width="178mm" content-height="scale-to-fit" scaling="uniform">
							<xsl:attribute name="src">
								<xsl:value-of select="normalize-space(concat('url(', $apos, frontispiece/contents/image, $apos, ')'))" />
							</xsl:attribute>
						</fo:external-graphic>
					</fo:block>
				</fo:block-container>
			</fo:flow>
		</fo:page-sequence>
	</xsl:template>
</xsl:stylesheet>
