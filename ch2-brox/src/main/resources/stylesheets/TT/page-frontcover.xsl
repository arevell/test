<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0"
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4">

	<xsl:template name="page-frontcover">
		<fo:page-sequence master-reference="cover-TT">
			<fo:flow flow-name="xsl-region-body">
				<fo:block-container position="absolute" top="16mm"
					left="16mm">
					<fo:block id="logo-TT">
						<fo:external-graphic content-width="74mm"
							content-height="scale-to-fit" scaling="uniform">
							<xsl:attribute name="src">
								<xsl:value-of select="normalize-space(concat('url(', $apos, frontispiece/logo, $apos, ')'))" />
							</xsl:attribute>
						</fo:external-graphic>
					</fo:block>
				</fo:block-container>

				<fo:block-container position="absolute" top="128mm"
					left="18mm" right="18mm" z-index="-1">
					<fo:block text-align="left" id="cover-image-TT">
						<fo:external-graphic content-width="scale-to-fit"
							width="100%">
							<xsl:attribute name="src">
								<xsl:value-of select="normalize-space(concat('url(', $apos, frontispiece/image, $apos, ')'))" />
							</xsl:attribute>
						</fo:external-graphic>
					</fo:block>
					<fo:block margin-top="9mm" text-align="center" font-size="14pt"
						color="black" font-family="AvenirLTStd-Heavy" z-index="1"
						font-weight="bold">
						<fo:basic-link>
							<xsl:attribute name="external-destination">
				              <xsl:value-of select="frontispiece/web" />
				            </xsl:attribute>
							<xsl:value-of select="frontispiece/web" />
						</fo:basic-link>
					</fo:block>
				</fo:block-container>


				<fo:block-container position="absolute" top="46mm"
					left="18mm" z-index="1" right="19mm">
					<fo:block font-size="42pt" color="red" font-family="CenturyStd-Book">
						<xsl:value-of select="title" />
					</fo:block>
				</fo:block-container>

				<!-- The texts have got more margin to the right side of the square to assure the line breaks.
					I coud also use the <br/> tag within the text. 
				-->
				<xsl:call-template name="frontText">
					<xsl:with-param name="text" select="frontispiece/front/text[position()=1]" />
					<xsl:with-param name="left" select="'157mm'" />
					<xsl:with-param name="top" select="'131mm'" />
					<xsl:with-param name="right" select="'26mm'" />
				</xsl:call-template>

				<xsl:call-template name="frontText">
					<xsl:with-param name="text" select="frontispiece/front/text[position()=2]" />
					<xsl:with-param name="left" select="'20mm'" />
					<xsl:with-param name="top" select="'173mm'" />
					<xsl:with-param name="right" select="'163mm'" />
				</xsl:call-template>

				<xsl:call-template name="frontText">
					<xsl:with-param name="text" select="frontispiece/front/text[position()=3]" />
					<xsl:with-param name="left" select="'62mm'" />
					<xsl:with-param name="top" select="'215mm'" />
					<xsl:with-param name="right" select="'121mm'" />
				</xsl:call-template>


			</fo:flow>
		</fo:page-sequence>

	</xsl:template>


	<xsl:template name="frontText">
		<xsl:param name="text" />
		<xsl:param name="left" />
		<xsl:param name="top" />
		<xsl:param name="right" />

		<fo:block-container position="absolute">
			<xsl:attribute name="left">
				<xsl:value-of select="$left" />
			</xsl:attribute>
			<xsl:attribute name="top">
				<xsl:value-of select="$top" />
			</xsl:attribute>
			<xsl:attribute name="right">
				<xsl:value-of select="$right" />
			</xsl:attribute>
			<fo:block>
				<fo:block font-family="AvenirLTStd-Heavy" margin-left="0mm" font-size="12pt"
					font-weight="bold" margin-right="0mm" text-align="left" color="white">
					<xsl:apply-templates select="$text"/>
				</fo:block>
			</fo:block>
		</fo:block-container>
	</xsl:template>
</xsl:stylesheet>