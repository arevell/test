<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">


	<xsl:template name="layout">
		<xsl:param name="papersize" select="'A4'" />

		<fo:layout-master-set>
			<xsl:choose>

				<xsl:when test="$papersize='A4'">

					<!-- DEFAULT COVER PAGE -->
					<fo:simple-page-master master-name="cover"
						page-height="29.7cm" page-width="21cm" margin-top="1.5cm"
						margin-bottom="1.5cm" margin-left="1.45cm" margin-right="1.45cm">
						<xsl:call-template name="layout-regions-cover" />
					</fo:simple-page-master>
					
					<!-- TRAFALGAR COVER PAGE -->
					<fo:simple-page-master master-name="cover-TT"
						page-height="29.7cm" page-width="21cm">
						<xsl:call-template name="layout-regions-cover-TT" />
					</fo:simple-page-master>

					<!-- STANDARD PAGE (Intro, Offers) -->
					<fo:simple-page-master master-name="standard"
						page-height="29.7cm" page-width="21cm" margin-top="1.5cm"
						margin-bottom="1.5cm" margin-left="1.45cm" margin-right="1.45cm">
						<xsl:call-template name="layout-regions" />
					</fo:simple-page-master>

					<!-- TOUR PAGES -->
					<fo:page-sequence-master master-name="tour-pages">
						<fo:single-page-master-reference
							master-reference="tour-first" />
						<fo:repeatable-page-master-reference
							master-reference="tour-rest" />
					</fo:page-sequence-master>

					<fo:simple-page-master master-name="tour-first"
						page-height="29.7cm" page-width="21cm" margin-top="1.5cm"
						margin-bottom="1.5cm" margin-left="1.45cm" margin-right="1.45cm">
						<xsl:call-template name="layout-regions-tour" />
					</fo:simple-page-master>

					<fo:simple-page-master master-name="tour-rest"
						page-height="29.7cm" page-width="21cm" margin-top="1.5cm"
						margin-bottom="1.5cm" margin-left="1.45cm" margin-right="1.45cm">
						<xsl:call-template name="layout-regions-tour2" />
					</fo:simple-page-master>



					<!-- TWO COLUMNS (Contents) -->
					<fo:simple-page-master master-name="twocolumns"
						page-height="29.7cm" page-width="21cm" margin-top="1.5cm"
						margin-bottom="1.5cm" margin-left="1.45cm" margin-right="1.45cm">
						<xsl:call-template name="layout-regions">
							<xsl:with-param name="cols" select="'2'" />
						</xsl:call-template>
					</fo:simple-page-master>

					<!-- THREE COLUMNS (Terms) -->
					<fo:simple-page-master master-name="threecolumns"
						page-height="29.7cm" page-width="21cm" margin-top="1.5cm"
						margin-bottom="1.5cm" margin-left="1.45cm" margin-right="1.45cm">
						<xsl:call-template name="layout-regions">
							<xsl:with-param name="cols" select="'3'" />
						</xsl:call-template>
					</fo:simple-page-master>
				</xsl:when>

				<xsl:otherwise>
					<!-- LETTER -->
				
					<!-- DEFAULT LETTER COVER page -->
					<fo:simple-page-master master-name="cover"
						page-height="27.94cm" page-width="21.59cm" margin-top="1.5cm"
						margin-bottom="1cm" margin-left="1.745cm" margin-right="1.745cm">
						<xsl:call-template name="layout-regions-cover" />
					</fo:simple-page-master>
					
					<!-- TRAFALGAR LETTER COVER PAGE -->
					<fo:simple-page-master master-name="cover-TT"
						page-height="27.94cm" page-width="21.59cm" >
						<xsl:call-template name="layout-regions-cover-TT" />
					</fo:simple-page-master>

					<!-- STANDARD PAGE (Intro, Offers) -->
					<fo:simple-page-master master-name="standard"
						page-height="27.94cm" page-width="21.59cm" margin-top="1.5cm"
						margin-bottom="1cm" margin-left="1.745cm" margin-right="1.745cm">
						<xsl:call-template name="layout-regions" />
					</fo:simple-page-master>

					<!-- TOUR PAGES -->
					<fo:page-sequence-master master-name="tour-pages">
						<fo:single-page-master-reference
							master-reference="tour-first" />
						<fo:repeatable-page-master-reference
							master-reference="tour-rest" />
					</fo:page-sequence-master>

					<fo:simple-page-master master-name="tour-first"
						page-height="27.94cm" page-width="21.59cm" margin-top="1.5cm"
						margin-bottom="1cm" margin-left="1.745cm" margin-right="1.745cm">
						<xsl:call-template name="layout-regions-tour" />
					</fo:simple-page-master>

					<fo:simple-page-master master-name="tour-rest"
						page-height="27.94cm" page-width="21.59cm" margin-top="1.5cm"
						margin-bottom="1cm" margin-left="1.745cm" margin-right="1.745cm">
						<xsl:call-template name="layout-regions-tour2" />
					</fo:simple-page-master>

					<!-- TWO COLUMNS (Contents) -->
					<fo:simple-page-master master-name="twocolumns"
						page-height="27.94cm" page-width="21.59cm" margin-top="1.5cm"
						margin-bottom="1cm" margin-left="1.745cm" margin-right="1.745cm">
						<xsl:call-template name="layout-regions">
							<xsl:with-param name="cols" select="'2'" />
						</xsl:call-template>
					</fo:simple-page-master>


					<!-- THREE COLUMNS (Terms) -->
					<fo:simple-page-master master-name="threecolumns"
						page-height="27.94cm" page-width="21.59cm" margin-top="1.5cm"
						margin-bottom="1cm" margin-left="1.745cm" margin-right="1.745cm">
						<xsl:call-template name="layout-regions">
							<xsl:with-param name="cols" select="'3'" />
						</xsl:call-template>
					</fo:simple-page-master>
				</xsl:otherwise>


			</xsl:choose>
		</fo:layout-master-set>

	</xsl:template>

	<xsl:template name="layout-regions">
		<xsl:param name="cols" select="'1'" />

		<!--
			note that the region before/after etc are actually part of the region
			body, so this must have a margin as big as those regions, or content
			will overlap
		-->
		<fo:region-body margin-top="0.2mm" column-count="{$cols}"
			column-gap="0mm" margin-bottom="20mm" margin-left="0.25mm"
			margin-right="0.25mm" />
		<fo:region-before extent="0.2mm" background-color="black"
			region-name="header" />
		<fo:region-after extent="20mm" region-name="footer" />
			
		<fo:region-start extent="0.2mm" region-name="left"/>
		<fo:region-end extent="0.2mm" region-name="right"/>
	</xsl:template>

	<xsl:template name="layout-regions-tour">
		<xsl:param name="cols" select="'1'" />

		<fo:region-body margin-top="0mm" column-count="{$cols}"
			column-gap="0mm" margin-bottom="20mm" margin-left="0.25mm"
			margin-right="0.25mm" />
		<fo:region-before extent="0mm" background-color="black"
			region-name="header-first" />
		<fo:region-after extent="20mm" region-name="footer" />
		<fo:region-start extent="0.2mm" region-name="left"/>
		<fo:region-end extent="0.2mm" region-name="right"/>
	</xsl:template>

	<xsl:template name="layout-regions-tour2">
		<xsl:param name="cols" select="'1'" />

		<fo:region-body margin-top="21mm" column-count="{$cols}"
			column-gap="0mm" margin-bottom="20mm" margin-left="0.25mm"
			margin-right="0.25mm" />
			
		<fo:region-before extent="21mm" background-color="black"
			region-name="header-rest" />

		<fo:region-after extent="20mm" region-name="footer" />
			
		<fo:region-start extent="0.2mm" region-name="left"/>
		<fo:region-end extent="0.2mm" region-name="right"/>
	</xsl:template>

    <!--  DEFAULT COVER PAGE LAYOUT-REGION -->
	<xsl:template name="layout-regions-cover">
		<fo:region-body margin-top="5mm" column-count="1"
			margin-bottom="5mm" margin-left="5mm" margin-right="5mm" />
		<fo:region-before extent="5mm" background-color="red" />
		<fo:region-after extent="5mm" background-color="red"
			region-name="footer" />
		<fo:region-start extent="5mm" background-color="red" />
		<fo:region-end extent="5mm" background-color="red" />
	</xsl:template>
	
	<!--  TRAFALGAR COVER PAGE LAYOUT-REGION -->
	<xsl:template name="layout-regions-cover-TT">
		<fo:region-body margin-top="0mm" column-count="1"
			margin-bottom="0mm" margin-left="0mm" margin-right="0mm" />
	</xsl:template>

	<xsl:template name="borders">
		<fo:static-content flow-name="footer">
				<xsl:call-template name="footer" />
			</fo:static-content>
			
			<fo:static-content flow-name="left">
				<xsl:call-template name="side" />
			</fo:static-content>
			
			<fo:static-content flow-name="right">
				<xsl:call-template name="side" />
			</fo:static-content>
	
	</xsl:template>

	<!-- date/time/page number to go on each page -->
	<xsl:template name="footer">
		<fo:block-container height="19mm" overflow="hidden" border-top-color="black" border-top-width="0.2mm"
							border-top-style="solid" background-color="white">
								<fo:block/>
		<fo:table table-layout="fixed" width="180mm" >
			<fo:table-column column-width='50mm' />
			<fo:table-column column-width='128mm' />

			<fo:table-body>
				<fo:table-row>
					<fo:table-cell>
						<fo:block padding-top="1mm" text-align="left"
							font-style="italic" font-size="8pt" xsl:use-attribute-sets="font-sans" linefeed-treatment="preserve">
							<xsl:value-of select="/brochure/agent/text"/>
						</fo:block>
					</fo:table-cell>
					<fo:table-cell>
						<fo:block padding-top="1mm" text-align="right"
							font-style="italic" font-size="8pt" xsl:use-attribute-sets="font-sans">
							<xsl:value-of select="/brochure/title" />
							<xsl:text> | </xsl:text>
							<xsl:value-of select="$currentDate" />
							<!-- <xsl:value-of select="format-date(current-date(),'[D01] [MNn,3-3] [Y0001]')" /> -->
							<xsl:text> | </xsl:text>
							Page
							<fo:page-number />
							of
							<fo:page-number-citation ref-id="last-page" />
						</fo:block>
					</fo:table-cell>
				</fo:table-row>

			</fo:table-body>
		</fo:table>
		</fo:block-container>
	</xsl:template>
	
	<xsl:template name="side">
		
		<fo:block-container height="246.8mm" border-top-color="black" border-top-width="0.2mm"
							border-top-style="solid" background-color="black">
								<fo:block/>
		</fo:block-container>
		 
	</xsl:template>

</xsl:stylesheet>