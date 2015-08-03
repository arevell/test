<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">


	<xsl:template name="layout">
		<xsl:param name="papersize" select="'A4'" />

		<fo:layout-master-set>
			<xsl:choose>

				<xsl:when test="$papersize='A4'">

					<!-- COVER PAGE -->
					<fo:simple-page-master master-name="cover-TT"
						page-height="29.7cm" page-width="21cm">
						<xsl:call-template name="layout-regions-cover-TT" />
					</fo:simple-page-master>


					<!-- STANDARD PAGE (Intro, Offers) -->
					<fo:simple-page-master master-name="standard-TT"
						page-height="29.7cm" page-width="21cm" margin-top="1.5cm"
						margin-bottom="1.5cm" margin-left="1.45cm" margin-right="1.45cm">
						<xsl:call-template name="layout-regions-TT" />
					</fo:simple-page-master>

					<!-- TOUR PAGES -->
					<fo:page-sequence-master master-name="tour-pages-TT">
						<fo:single-page-master-reference
							master-reference="tour-first-TT" />
						<fo:repeatable-page-master-reference
							master-reference="tour-rest-TT" />
					</fo:page-sequence-master>

					<fo:simple-page-master master-name="tour-first-TT"
						page-height="29.7cm" page-width="21cm" margin-top="1.4cm"
						margin-bottom="1.4cm" margin-left="1.45cm" margin-right="1.45cm">
						<xsl:call-template name="layout-regions-tour-TT" />
					</fo:simple-page-master>

					<fo:simple-page-master master-name="tour-rest-TT"
						page-height="29.7cm" page-width="21cm" margin-top="1.5cm"
						margin-bottom="1.5cm" margin-left="1.45cm" margin-right="1.45cm">
						<xsl:call-template name="layout-regions-tour2-TT" />
					</fo:simple-page-master>



					<!-- TWO COLUMNS (Contents) -->
					<fo:simple-page-master master-name="twocolumns-TT"
						page-height="29.7cm" page-width="21cm" margin-top="1.5cm"
						margin-bottom="1.5cm" margin-left="1.45cm" margin-right="1.45cm">
						<xsl:call-template name="layout-regions-TT">
							<xsl:with-param name="cols" select="'2'" />
						</xsl:call-template>
					</fo:simple-page-master>

					<!-- THREE COLUMNS (Terms) -->
					<fo:simple-page-master master-name="threecolumns-TT"
						page-height="29.7cm" page-width="21cm" margin-top="1.5cm"
						margin-bottom="1.5cm" margin-left="1.45cm" margin-right="1.45cm">
						<xsl:call-template name="layout-regions-TT">
							<xsl:with-param name="cols" select="'3'" />
						</xsl:call-template>
					</fo:simple-page-master>

				</xsl:when>

				<xsl:otherwise>  <!-- US LETTER SIZE PAGES -->

					<!-- US LETTER SIZE COVER PAGE -->
					<fo:simple-page-master master-name="cover-TT"
						page-height="27.94cm" page-width="21.59cm">
						<xsl:call-template name="layout-regions-cover-TT" />
					</fo:simple-page-master>

					<!-- US LETTER SIZE STANDARD PAGE (Intro, Offers) -->
					<fo:simple-page-master master-name="standard-TT"
						page-height="27.94cm" page-width="21.59cm" margin-top="1.5cm"
						margin-bottom="1cm" margin-left="1.745cm" margin-right="1.745cm">
						<xsl:call-template name="layout-regions-TT" />
					</fo:simple-page-master>

					<!-- US LETTER SIZE TOUR PAGES -->
					<fo:page-sequence-master master-name="tour-pages-TT">
						<fo:single-page-master-reference
							master-reference="tour-first-TT" />
						<fo:repeatable-page-master-reference
							master-reference="tour-rest-TT" />
					</fo:page-sequence-master>

					<fo:simple-page-master master-name="tour-first-TT"
						page-height="27.94cm" page-width="21.59cm" margin-top="1.5cm"
						margin-bottom="1cm" margin-left="1.745cm" margin-right="1.745cm">
						<xsl:call-template name="layout-regions-tour-TT" />
					</fo:simple-page-master>

					<fo:simple-page-master master-name="tour-rest-TT"
						page-height="27.94cm" page-width="21.59cm" margin-top="1.5cm"
						margin-bottom="1cm" margin-left="1.745cm" margin-right="1.745cm">
						<xsl:call-template name="layout-regions-tour2-TT" />
					</fo:simple-page-master>

					<!-- US TWO COLUMNS (Contents) -->
					<fo:simple-page-master master-name="twocolumns-TT"
						page-height="27.94cm" page-width="21.59cm" margin-top="1.5cm"
						margin-bottom="1cm" margin-left="1.745cm" margin-right="1.745cm">
						<xsl:call-template name="layout-regions-TT">
							<xsl:with-param name="cols" select="'2'" />
						</xsl:call-template>
					</fo:simple-page-master>

					<!-- US THREE COLUMNS (Terms) -->
					<fo:simple-page-master master-name="threecolumns-TT"
						page-height="27.94cm" page-width="21.59cm" margin-top="5mm"
						margin-bottom="5mm" margin-left="5mm" margin-right="5mm">
						<xsl:call-template name="layout-regions-TT">
							<xsl:with-param name="cols" select="'3'" />
						</xsl:call-template>
					</fo:simple-page-master>
				</xsl:otherwise>

			</xsl:choose>
		</fo:layout-master-set>

	</xsl:template>

	<!-- STANDARD LAYOUT REGIONS -->
	<xsl:template name="layout-regions-TT">
		<xsl:param name="cols" select="'1'" />
		<fo:region-body margin-top="0.2mm" column-count="{$cols}"
			column-gap="0mm" margin-bottom="5mm" margin-left="0.25mm"
			margin-right="0.25mm" />
		<fo:region-before extent="0.2mm" region-name="header-TT" />
		<fo:region-after extent="5mm" region-name="footer-TT" />

		<fo:region-start extent="0.2mm" region-name="left" />
		<fo:region-end extent="0.2mm" region-name="right" />
	</xsl:template>


	<!-- TOUR PAGE LAYOUT-REGIONS -->
	<xsl:template name="layout-regions-tour-TT">
		<xsl:param name="cols" select="'1'" />

		<fo:region-body margin-top="0mm" column-count="{$cols}"
			column-gap="0mm" margin-bottom="15mm" margin-left="0.25mm"
			margin-right="0.25mm" />
		<fo:region-before extent="0mm" region-name="header-first-TT" />
		<fo:region-after extent="5mm" region-name="footer-TT" />
		<fo:region-start extent="0.2mm" region-name="left" />
		<fo:region-end extent="0.2mm" region-name="right" />
	</xsl:template>

	<xsl:template name="layout-regions-tour2-TT">
		<xsl:param name="cols" select="'1'" />

		<fo:region-body margin-top="21mm" column-count="{$cols}"
			column-gap="0mm" margin-bottom="15mm" margin-left="0.25mm"
			margin-right="0.25mm" />

		<fo:region-before extent="21mm" background-color="white"
			region-name="header-rest-TT" />

		<fo:region-after extent="5mm" region-name="footer-TT" />
		<fo:region-start extent="0.2mm" region-name="left" />
		<fo:region-end extent="0.2mm" region-name="right" />
	</xsl:template>

	<!-- COVER PAGE LAYOUT-REGION -->
	<xsl:template name="layout-regions-cover-TT">
		<fo:region-body margin-top="0mm" column-count="1"
			margin-bottom="0mm" margin-left="0mm" margin-right="0mm" />
	</xsl:template>


	<xsl:template name="footer-TT">  <!-- FOOTERS -->
		

		<fo:block-container height="15mm" overflow="hidden" >
			<fo:block />
			<fo:table table-layout="fixed" width="180mm">
				<fo:table-column column-width='178mm' />

				<fo:table-body color="black">
					<fo:table-row>
						<fo:table-cell>
							<fo:block padding-top="1mm" text-align="right"
								font-size="8pt" font-family="AvenirLTStd-Light">
								<xsl:value-of select="/brochure/title" />
								<xsl:text> - </xsl:text>
								<!-- dateFormat is a global variable with the format of Dates. See stylesheeet.xsl -->	
								<xsl:value-of select="$currentDate" />
								<!-- <xsl:value-of select="format-date(current-date(),$dateFormat)"/> -->
								<xsl:text> - Page </xsl:text>
								<fo:page-number />
								<xsl:text> of </xsl:text>
								<fo:page-number-citation ref-id="last-page"/> 
							</fo:block>
						</fo:table-cell>
					</fo:table-row>

				</fo:table-body>
			</fo:table>
		</fo:block-container>
	</xsl:template>

	<xsl:template name="side">
		<fo:block-container height="246.8mm"
			border-top-color="black" border-top-width="0.2mm" border-top-style="solid"
			background-color="black">
			<fo:block />
		</fo:block-container>
	</xsl:template>
</xsl:stylesheet>