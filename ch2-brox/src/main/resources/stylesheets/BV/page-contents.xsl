<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" 
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4"
	version="2.0">


	<xsl:template name="page-contents">
		<fo:page-sequence master-reference="twocolumns">
			<xsl:call-template name="borders" />

			<fo:flow flow-name="xsl-region-body">

				<fo:block id="contents" span="all" xsl:use-attribute-sets="font-sans"
					font-size="24pt" text-align="center" margin-top="0mm" padding-top="0mm"
					border-top-style="solid" border-top-width="7mm" border-color="red"
					line-height="0mm">

					<fo:external-graphic margin-top="0mm"
						margin-bottom="0mm" padding-top="3.9mm" padding-bottom="0mm"
						width="180.2mm" height="100mm" content-width="scale-to-fit"
						content-height="scale-to-fit" scaling="non-uniform">
						<xsl:attribute name="src">
							<xsl:value-of select="normalize-space(concat('url(', $apos, frontispiece/contents/image, $apos, ')'))" />
						</xsl:attribute>
					</fo:external-graphic>
				</fo:block>

				<fo:block span="all" xsl:use-attribute-sets="font-serif"
					color="red" font-size="36pt" text-align="left" margin-top="0mm"
					padding-top="5mm" padding-bottom="0mm" margin-bottom="0mm"
					margin-left="5mm">
					CONTENTS PAGE
				</fo:block>

				<fo:block span="all" xsl:use-attribute-sets="font-serif"
					color="black" font-size="24pt" text-align="left" margin-top="0mm"
					padding-top="0mm" margin-left="5mm" padding-bottom="5mm">
					DESIGNED FOR YOU
				</fo:block>

				<fo:block margin-left="5mm" margin-right="5mm">
					<fo:table table-layout="fixed" width="80mm"
						border-top-style="solid" border-top-width="0.1mm" border-color="#CCCCCC"
						font-size="10pt">
						<fo:table-column column-width='70mm' />
						<fo:table-column column-width='10mm' />

						<fo:table-body>
							
							

							<fo:table-row>
								<fo:table-cell xsl:use-attribute-sets="contents-row">
									<fo:block padding-top="1mm" padding-bottom="1mm">
										<fo:basic-link internal-destination="intro2">
											<xsl:value-of select="translate(frontispiece/intro2/title, $smallcase, $uppercase)"/>										</fo:basic-link>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell xsl:use-attribute-sets="contents-row">
									<fo:block text-align="right" padding-top="1mm"
										padding-bottom="1mm">
										<fo:page-number-citation ref-id="intro2" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							<fo:table-row>
								<fo:table-cell xsl:use-attribute-sets="contents-row">
									<fo:block padding-top="1mm" padding-bottom="1mm">
										<fo:basic-link internal-destination="offers">
											PROMOTIONS
										</fo:basic-link>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell xsl:use-attribute-sets="contents-row">
									<fo:block text-align="right" padding-top="1mm"
										padding-bottom="1mm">
										<fo:page-number-citation ref-id="offers" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							
							<fo:table-row>
								<fo:table-cell xsl:use-attribute-sets="contents-row">
									<fo:block padding-top="1mm" padding-bottom="1mm">
										<fo:basic-link internal-destination="terms">
											TERMS &amp; CONDITIONS
										</fo:basic-link>
									</fo:block>
								</fo:table-cell>
								<fo:table-cell  xsl:use-attribute-sets="contents-row">
									<fo:block text-align="right" padding-top="1mm" padding-bottom="1mm">
										<fo:page-number-citation ref-id="terms" />
									</fo:block>
								</fo:table-cell>
							</fo:table-row>
							
							
						</fo:table-body>

					</fo:table>







					<fo:block xsl:use-attribute-sets="font-serif"
						background-color="red" color="white" font-size="12pt" margin-top="4mm"
						margin-right="0mm" margin-bottom="1.5mm" padding-top="1.5mm" padding-bottom="1mm"
						padding-left="0mm">
						<fo:block margin-left="5mm">YOUR TOURS</fo:block>
					</fo:block>


					<fo:table table-layout="fixed" width="80mm" font-size="10pt">
						<fo:table-column column-width='70mm' />
						<fo:table-column column-width='10mm' />

						<fo:table-body>
							<xsl:for-each select="ti:TourInfo">
								<fo:table-row>
									<fo:table-cell xsl:use-attribute-sets="contents-row">
										<fo:block padding-top="1mm" padding-bottom="1mm">
											<fo:basic-link internal-destination="{@MVCode}">
												<xsl:value-of select="translate(ti:TourName, $smallcase, $uppercase)" />
											</fo:basic-link>
										</fo:block>
									</fo:table-cell>
									<fo:table-cell xsl:use-attribute-sets="contents-row">
										<fo:block text-align="right" padding-top="1mm"
											padding-bottom="1mm">
											<fo:page-number-citation ref-id="{@MVCode}" />
										</fo:block>
									</fo:table-cell>
								</fo:table-row>

							</xsl:for-each>


							
						</fo:table-body>

					</fo:table>
				</fo:block>
				
					

					

					


			</fo:flow>
		</fo:page-sequence>

	</xsl:template>

</xsl:stylesheet>