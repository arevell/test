<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">


	<xsl:template name="layout">
		<xsl:param name="papersize" select="'A4'" />

		<fo:layout-master-set>
			<xsl:choose>

				<xsl:when test="$papersize='A4'">

					<!-- CONTIKI COVER PAGE -->
					<fo:simple-page-master master-name="cover-CH"
						page-height="29.7cm" page-width="21cm">
						<xsl:call-template name="layout-regions-cover-CH" />
					</fo:simple-page-master>
					
				
					<!-- CONTIKI STANDARD PAGE (Intro, Offers) -->
					<fo:simple-page-master master-name="standard-CH"
						page-height="29.7cm" page-width="21cm" margin-top="1.5cm"
						margin-bottom="1.5cm" margin-left="1.45cm" margin-right="1.45cm">
						<xsl:call-template name="layout-regions-CH" />
					</fo:simple-page-master>
					
					<!-- CONTIKI TOUR PAGES -->
					<fo:page-sequence-master master-name="tour-pages-CH">
						<fo:single-page-master-reference
							master-reference="tour-first-CH" />
						<fo:repeatable-page-master-reference
							master-reference="tour-rest-CH" />
					</fo:page-sequence-master>
					
					<fo:simple-page-master master-name="tour-first-CH"
						page-height="29.7cm" page-width="21cm" margin-top="1.4cm"
						margin-bottom="1.4cm" margin-left="1.45cm" margin-right="1.45cm">
						<xsl:call-template name="layout-regions-tour-CH">
							<xsl:with-param name="cols" select="'2'" />
						</xsl:call-template>
					</fo:simple-page-master>
					
					<fo:simple-page-master master-name="tour-rest-CH"
						page-height="29.7cm" page-width="21cm" margin-top="1.5cm"
						margin-bottom="1.5cm" margin-left="1.45cm" margin-right="1.45cm">
						<xsl:call-template name="layout-regions-tour2-CH">
							<xsl:with-param name="cols" select="'2'" />
						</xsl:call-template>
					</fo:simple-page-master>
					
					
					<!-- CONTIKI TWO COLUMNS (Contents) -->
					<fo:simple-page-master master-name="twocolumns-CH"
						page-height="29.7cm" page-width="21cm" margin-top="1.5cm"
						margin-bottom="1.5cm" margin-left="1.45cm" margin-right="1.45cm">
						<xsl:call-template name="layout-regions-CH">
							<xsl:with-param name="cols" select="'2'" />
						</xsl:call-template>
					</fo:simple-page-master>

					<!-- CONTIKI THREE COLUMNS -->
					<fo:simple-page-master master-name="threecolumns-CH"
						page-height="29.7cm" page-width="21cm" margin-top="5mm"
						margin-bottom="5mm" margin-left="5mm" margin-right="5mm">
						<xsl:call-template name="layout-regions-CH">
							<xsl:with-param name="cols" select="'3'" />
						</xsl:call-template>
					</fo:simple-page-master>
					
					<!-- CONTIKI THREE COLUMNS (Intro2 - "What You'll Get") -->
					<fo:simple-page-master master-name="threecolumns-intro2-CH"
						page-height="29.7cm" page-width="21cm" margin-top="5mm"
						margin-bottom="5mm" margin-left="6mm" margin-right="5mm">
						<xsl:call-template name="layout-regions-3col-intro2-CH"/>
					</fo:simple-page-master>
					
					<!-- CONTIKI FOUR COLUMNS (Questions, Booking Conditions) -->
					<fo:page-sequence-master master-name="fourcolumn-pages-CH">
						<fo:single-page-master-reference
							master-reference="fourcolumn-header-CH" />
						<fo:repeatable-page-master-reference
							master-reference="fourcolumn-header-CH" />
					</fo:page-sequence-master>
					
					<fo:simple-page-master master-name="fourcolumn-header-CH"
						page-height="29.7cm" page-width="21cm" margin-top="5mm"
						margin-bottom="5mm" margin-left="5mm" margin-right="5mm">
						<xsl:call-template name="layout-regions-4col-CH">
							<xsl:with-param name="cols" select="'4'" />
						</xsl:call-template>
					</fo:simple-page-master>
										
				</xsl:when>
				
				<xsl:otherwise>  <!-- US LETTER SIZE PAGES -->
				
					<!-- US LETTER SIZE CONTIKI COVER PAGE -->
					<fo:simple-page-master master-name="cover-CH"
						page-height="27.94cm" page-width="21.59cm" >
						<xsl:call-template name="layout-regions-cover-CH" />
					</fo:simple-page-master>
					
					<!-- CONTIKI US LETTER SIZE STANDARD PAGE (Intro, Offers) -->
					<fo:simple-page-master master-name="standard-CH"
						page-height="27.94cm" page-width="21.59cm" margin-top="1.5cm"
						margin-bottom="1cm" margin-left="1.745cm" margin-right="1.745cm">
						<xsl:call-template name="layout-regions-CH" />
					</fo:simple-page-master>
					
					<!-- US LETTER SIZE CONTIKI TOUR PAGES  -->
					<fo:page-sequence-master master-name="tour-pages-CH">
						<fo:single-page-master-reference
							master-reference="tour-first-CH" />
						<fo:repeatable-page-master-reference
							master-reference="tour-rest-CH" />
					</fo:page-sequence-master>
					
					<fo:simple-page-master master-name="tour-first-CH"
						page-height="27.94cm" page-width="21.59cm" margin-top="1.5cm"
						margin-bottom="1cm" margin-left="1.745cm" margin-right="1.745cm">
						<xsl:call-template name="layout-regions-tour-CH">
							<xsl:with-param name="cols" select="'2'" />
						</xsl:call-template>
					</fo:simple-page-master>
					
					<fo:simple-page-master master-name="tour-rest-CH"
						page-height="27.94cm" page-width="21.59cm" margin-top="1.5cm"
						margin-bottom="1cm" margin-left="1.745cm" margin-right="1.745cm">
						<xsl:call-template name="layout-regions-tour2-CH">
							<xsl:with-param name="cols" select="'2'" />
						</xsl:call-template>
					</fo:simple-page-master>
					
					<!-- CONTIKI US TWO COLUMNS (Contents) -->
					<fo:simple-page-master master-name="twocolumns-CH"
						page-height="27.94cm" page-width="21.59cm" margin-top="1.5cm"
						margin-bottom="1cm" margin-left="1.745cm" margin-right="1.745cm">
						<xsl:call-template name="layout-regions-CH">
							<xsl:with-param name="cols" select="'2'" />
						</xsl:call-template>
					</fo:simple-page-master>
					
					<!-- CONTIKI US THREE COLUMNS (Terms) -->
					<fo:simple-page-master master-name="threecolumns-CH"
						page-height="27.94cm" page-width="21.59cm" margin-top="5mm"
						margin-bottom="5mm" margin-left="5mm" margin-right="5mm">
						<xsl:call-template name="layout-regions-CH">
							<xsl:with-param name="cols" select="'3'" />
						</xsl:call-template>
					</fo:simple-page-master>

					<!-- CONTIKI US THREE COLUMNS (Intro2 - "What You'll Get") -->
					<fo:simple-page-master master-name="threecolumns-intro2-CH"
						page-height="27.94cm" page-width="21.59cm" margin-top="5mm"
						margin-bottom="5mm" margin-left="5mm" margin-right="5mm">
						<xsl:call-template name="layout-regions-3col-intro2-CH"/>
					</fo:simple-page-master>
					
					<!-- CONTIKI US FOUR COLUMNS (Questions, Booking Conditions) -->
					<fo:page-sequence-master master-name="fourcolumn-pages-CH">
						<fo:single-page-master-reference
							master-reference="fourcolumn-header-CH" />
						<fo:repeatable-page-master-reference
							master-reference="fourcolumn-header-CH" />
					</fo:page-sequence-master>
					
					<fo:simple-page-master master-name="fourcolumn-header-CH"
						page-height="29.94cm" page-width="21.59cm" margin-top="5mm"
						margin-bottom="5mm" margin-left="5mm" margin-right="5mm">
						<xsl:call-template name="layout-regions-4col-CH">
							<xsl:with-param name="cols" select="'4'" />
						</xsl:call-template>
					</fo:simple-page-master>
										
				</xsl:otherwise>

			</xsl:choose>
		</fo:layout-master-set>

	</xsl:template>

	<!--  CONTIKI STANDARD LAYOUT REGIONS  -->
	<xsl:template name="layout-regions-CH">
		<xsl:param name="cols" select="'1'" />
		<fo:region-body margin-top="0.2mm" column-count="{$cols}"
			column-gap="5mm" margin-bottom="20mm" margin-left="0.25mm"
			margin-right="0.25mm" />
		<fo:region-before extent="0.2mm" region-name="header-CH" />
		<fo:region-after extent="20mm" region-name="footer-CH" />
		
		<fo:region-start extent="0.2mm" region-name="left"/>
		<fo:region-end extent="0.2mm" region-name="right"/>
	</xsl:template>
	
	
	<!--  CONTIKI 3 COLUMN LAYOUT REGIONS WITH IMAGES BELOW COLUMNS  -->
	<xsl:template name="layout-regions-3col-intro2-CH">
		<fo:region-body margin-top="0.2mm" column-count="3"
			column-gap="-14mm" margin-bottom="155mm" margin-left="0.25mm"
			margin-right="0.25mm"/>
		<fo:region-before extent="0.2mm" region-name="header-CH" />
		<fo:region-after extent="145mm" region-name="footer-CH" />
		
		<fo:region-start extent="0.2mm" region-name="left"/>
		<fo:region-end extent="0.2mm" region-name="right"/>
	</xsl:template>
	
	
	<!--  CONTIKI TOUR PAGE LAYOUT-REGIONS -->
	<xsl:template name="layout-regions-tour-CH">
		<xsl:param name="cols" select="'1'" />
		<fo:region-body margin-top="21mm" column-count="{$cols}" column-gap="0mm" 
			            margin-bottom="20mm" margin-left="0.25mm" margin-right="0.25mm" />
		<fo:region-before extent="0mm" region-name="header-first-CH" />
		<fo:region-after extent="20mm" region-name="footer-CH" />
		<fo:region-start extent="0.2mm" region-name="left"/>
		<fo:region-end extent="0.2mm" region-name="right"/>
	</xsl:template>
	
	<xsl:template name="layout-regions-tour2-CH">
		<xsl:param name="cols" select="'1'" />
		<fo:region-body margin-top="21mm" column-count="{$cols}"
			column-gap="0mm" margin-bottom="20mm" margin-left="0.25mm"
			margin-right="0.25mm" />
		
		<fo:region-before extent="21mm" background-color="white"
			region-name="header-rest-CH" />
		
		<fo:region-after extent="20mm" region-name="footer-CH" />
		<fo:region-start extent="0.2mm" region-name="left"/>
		<fo:region-end extent="0.2mm" region-name="right"/>
	</xsl:template>
	
	
	<!--  CONTIKI QUESTIONS PAGE LAYOUT-REGIONS -->
	<xsl:template name="layout-regions-4col-CH">
		<xsl:param name="cols" select="'1'" />
		<fo:region-body margin-top="21mm" column-count="{$cols}" column-gap="0mm" 
			            margin-bottom="10mm" margin-left="0.25mm" margin-right="0.25mm" />
		<fo:region-before extent="0.2mm" region-name="fourcolumn-header-CH" />
		<fo:region-after extent="25mm" region-name="footer-4col-CH" />
		<fo:region-start extent="0.2mm" region-name="left"/>
		<fo:region-end extent="0.2mm" region-name="right"/>
	</xsl:template>
	
		
	<!--  CONTIKI COVER PAGE LAYOUT-REGION -->
	<xsl:template name="layout-regions-cover-CH">
		<fo:region-body margin-top="0mm" column-count="1"
			margin-bottom="0mm" margin-left="0mm" margin-right="0mm" />
	</xsl:template>
	

	<xsl:template name="footer-CH">  <!--  CONTIKI FOOTERS -->
		
		<fo:block-container margin-top="20mm" height="109mm" color="black" overflow="hidden" border-top-width="0.2mm">
			<fo:block/>
			<fo:table table-layout="fixed" width="180mm" >
				<fo:table-column column-width='50mm' />
				<fo:table-column column-width='128mm' />
				
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell>
							 <fo:block padding-top="1mm" text-align="left"
								font-size="10pt" font-family="FuturaStd-Bold" linefeed-treatment="preserve">
								<xsl:value-of select="/brochure/frontispiece/web"/> 
							</fo:block>
					</fo:table-cell>
						<fo:table-cell>
							<fo:block padding-top="1mm" text-align="right" font-size="10pt" font-family="FSAlbert-Light">
								<xsl:value-of select="/brochure/title" />
								<xsl:text>'s eBrochure</xsl:text>
								<xsl:text>&#x00A0;&#x00A0;&#x00A0;|&#x00A0;&#x00A0;&#x00A0;</xsl:text>
								<xsl:value-of select="$currentDateNumerical" />
								<xsl:text>&#x00A0;&#x00A0;&#x00A0;|&#x00A0;&#x00A0;&#x00A0;</xsl:text>
								<fo:page-number />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					
				</fo:table-body>
			</fo:table>
		</fo:block-container>
	</xsl:template>
	
	
	<xsl:template name="footer-4col-CH">  <!--  CONTIKI FOOTERS -->
		
		<fo:block-container margin-top="20mm" height="109mm" color="black" overflow="hidden" border-top-width="0.2mm"
			 background-color="white">
			<fo:block/>
			<fo:table table-layout="fixed" width="180mm" >
				<fo:table-column column-width='50mm' />
				<fo:table-column column-width='142mm' />
				
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell>
							 <fo:block padding-top="1mm" text-align="left" margin-left="3mm"
								font-size="10pt" font-family="FuturaStd-Bold" linefeed-treatment="preserve">
								<xsl:value-of select="/brochure/frontispiece/web"/> 
							</fo:block>
					</fo:table-cell>
						<fo:table-cell>
							<fo:block padding-top="1mm" text-align="right" font-size="10pt" font-family="FSAlbert-Light">
								<xsl:value-of select="/brochure/title" />
								<xsl:text>'s eBrochure</xsl:text>
								<xsl:text>&#x00A0;&#x00A0;&#x00A0;|&#x00A0;&#x00A0;&#x00A0;</xsl:text>
								<xsl:value-of select="$currentDateNumerical" />
								<xsl:text>&#x00A0;&#x00A0;&#x00A0;|&#x00A0;&#x00A0;&#x00A0;</xsl:text>
								<fo:page-number />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					
				</fo:table-body>
			</fo:table>
		</fo:block-container>
	</xsl:template>
	
	
	<xsl:template name="footer2-CH">  <!--  CONTIKI FOOTERS -->
		
		<fo:block-container margin-top="5mm" color="black" overflow="hidden" border-top-width="0.2mm">
			<fo:block/>
			<fo:table table-layout="fixed"  >
				<fo:table-column column-width='50mm' />
				<fo:table-column column-width='138mm' />
				
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell>
							 <fo:block padding-top="1mm" margin-left="10mm" text-align="left"
								font-size="10pt" font-family="FuturaStd-Bold" linefeed-treatment="preserve">
								<xsl:value-of select="/brochure/frontispiece/web"/> 
							</fo:block>
					</fo:table-cell>
						<fo:table-cell>
							<fo:block padding-top="1mm" text-align="right" font-size="10pt" font-family="FSAlbert-Light">
								<xsl:value-of select="/brochure/title" />
								<xsl:text>'s eBrochure</xsl:text>
								<xsl:text>&#x00A0;&#x00A0;&#x00A0;|&#x00A0;&#x00A0;&#x00A0;</xsl:text>
								<xsl:value-of select="$currentDateNumerical" />
								<xsl:text>&#x00A0;&#x00A0;&#x00A0;|&#x00A0;&#x00A0;&#x00A0;</xsl:text>
								<fo:page-number />
							</fo:block>
						</fo:table-cell>
					</fo:table-row>
					
				</fo:table-body>
			</fo:table>
		</fo:block-container>
	</xsl:template>
	
	
	<xsl:template name="footerWithBorder-CH">  <!--  CONTIKI FOOTERS -->
		
		<fo:block-container margin-top="20mm" height="109mm" color="black" overflow="hidden" border-top-width="0.2mm" 
		                    border-top-style="solid" >
			<fo:block/>
			<fo:table table-layout="fixed" width="180mm" >
				<fo:table-column column-width='50mm' />
				<fo:table-column column-width='128mm' />
				
				<fo:table-body>
					<fo:table-row>
						<fo:table-cell>
							 <fo:block padding-top="5mm" text-align="left"
								font-size="10pt" font-family="FuturaStd-Bold" linefeed-treatment="preserve">
								<xsl:value-of select="/brochure/frontispiece/web"/> 
							</fo:block>
					</fo:table-cell>
						<fo:table-cell>
							<fo:block padding-top="5mm" text-align="right" font-size="10pt" font-family="FSAlbert-Light">
								<xsl:value-of select="/brochure/title" />
								<xsl:text>'s eBrochure</xsl:text>
								<xsl:text>&#x00A0;&#x00A0;&#x00A0;|&#x00A0;&#x00A0;&#x00A0;</xsl:text>
								<xsl:value-of select="$currentDateNumerical" />
								<xsl:text>&#x00A0;&#x00A0;&#x00A0;|&#x00A0;&#x00A0;&#x00A0;</xsl:text>
								<fo:page-number />
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