<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4" xmlns:svg="http://www.w3.org/2000/svg"
	version="2.0">


	<xsl:template name="page-contents">
		<fo:page-sequence master-reference="standard-CH">

			<fo:static-content flow-name="footer-CH">
				<xsl:call-template name="footer-CH" />
			</fo:static-content>


			<fo:flow flow-name="xsl-region-body">
				<fo:block-container id="contents-CH" position="absolute" top="0mm" left="85mm">
					<fo:block  >
						<fo:external-graphic content-height="96.5mm" content-width="scale-to-fit" width="100%" scaling="uniform">
							<xsl:attribute name="src">
								<xsl:value-of select="normalize-space(concat('url(', $apos, frontispiece/contents/image, $apos, ')'))" />
							</xsl:attribute>
						</fo:external-graphic>
					</fo:block>

					<fo:block background-color="#EFFBFB">
						<fo:block margin-left="7mm" margin-right="7mm" margin-top="10mm" padding-top="0mm" padding-bottom="0mm" margin-bottom="-3mm"
						         font-size="30pt" color="black" font-family="Trade Gothic LT Pro" >
							THE
						</fo:block>
						
						<fo:block margin-left="7mm" margin-right="7mm" font-size="42pt" color="black" margin-bottom="-4mm" margin-top="0mm"
							font-family="Veneer Clean" font-weight="bold">
							#NOREGRETS
						</fo:block>
	
						<fo:block margin-left="7mm" margin-right="7mm"
						          font-size="30pt" color="black" font-family="Trade Gothic LT Pro">
							PHILOSOPHY
						</fo:block>
						
						<fo:block margin-left="7mm" margin-right="7mm" margin-top="5mm" text-align="justify" font-size="11pt" color="#000000" font-family="FSAlbert-Light" 
							border-top-style="solid">
							<fo:block margin-top="5mm"/>
							<xsl:apply-templates select="frontispiece/intro/text" />
							<fo:block margin-top="5mm"  border-top-style="solid" margin-bottom="10mm"/>
						</fo:block>
					</fo:block>
					
				</fo:block-container>

				
				<!-- ********************************************* -->
				<!--           Table of Contents Section           -->

				<fo:block-container position="absolute" top="20mm" left="-0.5mm" right="350" z-index="1">

					<fo:block margin-left="0mm" margin-right="0mm" text-align="left">
						
						<fo:block margin-left="2mm" margin-bottom="10mm">
							<fo:external-graphic content-height="scale-to-fit" content-width="scale-to-fit" width="125%" scaling="uniform">
								<xsl:attribute name="src">
									<xsl:value-of select="normalize-space(concat('url(', $apos, frontispiece/contents/contents-image, $apos, ')'))" />
								</xsl:attribute>
							</fo:external-graphic>
						</fo:block>
	
						<fo:table width="75mm">
							<fo:table-column column-width="71mm" />
							<fo:table-column column-width="4mm" />

							<fo:table-body>
								<fo:table-row>
									<fo:table-cell display-align="center" number-columns-spanned="2">
										<fo:block font-size="12pt" color="black" font-family="FuturaStd-Bold" border-top-style="solid" 
										          padding-top="3mm" margin-bottom="3mm">
											<xsl:text>INTRODUCTION</xsl:text>
										</fo:block>
									</fo:table-cell>


								</fo:table-row>

								<!-- ********************************************* -->
								<!--             The Contiki Difference            -->
								<fo:table-row>
								
									<fo:table-cell>
										<fo:block text-align="left" margin-left="1mm"
											margin-right="0mm" font-size="10pt" font-family="FSAlbert-Light">
											<fo:basic-link internal-destination="intro1-CH">
												<xsl:value-of select="frontispiece/intro1/title" />
											</fo:basic-link>
										</fo:block>
									</fo:table-cell>

									<fo:table-cell>
										<fo:block font-size="11pt" font-family="FSAlbert-Light" text-align="right">
											<fo:page-number-citation ref-id="intro1-CH" />
										</fo:block>
									</fo:table-cell>
									
								</fo:table-row>


								<!-- ********************************************* -->
								<!--             What You'll Get Section           -->
								<fo:table-row>

									<fo:table-cell>
										<fo:block text-align="left" margin-left="1mm"
											margin-right="0mm" font-size="10pt" font-family="FSAlbert-Light">
											<fo:basic-link internal-destination="intro2-CH">
												<xsl:value-of select="frontispiece/intro2/toc-title" />
											</fo:basic-link>
										</fo:block>
									</fo:table-cell>

									<fo:table-cell>
										<fo:block font-size="10pt" font-family="FSAlbert-Light" text-align="right">
											<fo:page-number-citation ref-id="intro2-CH" />
										</fo:block>
									</fo:table-cell>
									
								</fo:table-row>

								<fo:table-row>

									<fo:table-cell>
										<fo:block>
											&#x00A0; <!-- space -->
										</fo:block>
									</fo:table-cell>

									<fo:table-cell>
										<fo:block>
											&#x00A0; <!-- space -->
										</fo:block>
									</fo:table-cell>

								</fo:table-row>


								<!-- ********************************************* -->
								<!--               Tour Info Section               -->

								<fo:table-row>

									<fo:table-cell display-align="center" number-columns-spanned="2">
										<fo:block font-size="12pt" color="black" font-family="FuturaStd-Bold" border-top-style="solid" 
										          padding-top="3mm" margin-bottom="3mm">
											<xsl:text>YOUR TRIPS</xsl:text>
										</fo:block>
									</fo:table-cell>

								</fo:table-row>

								<xsl:for-each select="ti:TourInfo">
									<xsl:variable name="mvcode" select="@MVCode" />
									
									<!-- tour name -->
									<fo:table-row>

										<fo:table-cell>
											<fo:block text-align="left" margin-left="1mm"  margin-bottom="2mm"
												margin-right="0mm" font-size="10pt" font-family="FSAlbert-Light">
												<fo:basic-link internal-destination="{@MVCode}">
													<xsl:value-of select="ti:TourName"/>
												</fo:basic-link>
											</fo:block>
										</fo:table-cell>

										<fo:table-cell>
											<fo:block font-size="10pt" font-family="FSAlbert-Light"  text-align="right">
												<fo:page-number-citation ref-id="{@MVCode}" />
											</fo:block>
										</fo:table-cell>
										
									</fo:table-row>
								</xsl:for-each>




								<fo:table-row>

									<fo:table-cell>
										<fo:block>
											&#x00A0; <!-- space -->
										</fo:block>
									</fo:table-cell>

									<fo:table-cell>
										<fo:block>
											&#x00A0; <!-- space -->
										</fo:block>
									</fo:table-cell>

								</fo:table-row>

								<!-- ********************************************* -->
								<!--              Other Stuff question             -->
								<fo:table-row>
									<fo:table-cell display-align="center" number-columns-spanned="2">
										<fo:block font-size="12pt" color="black" font-family="FuturaStd-Bold" border-top-style="solid" 
										          padding-top="3mm" margin-bottom="3mm">
											<xsl:text>OTHER STUFF</xsl:text>
										</fo:block>
									</fo:table-cell>


								</fo:table-row>

								<!-- ********************************************* -->
								<!--                 Got a question                -->
								<fo:table-row>

									<fo:table-cell>
										<fo:block text-align="left" margin-left="1mm"
											margin-right="0mm" font-size="11pt" font-family="FSAlbert-Light">
											<fo:basic-link internal-destination="questions-CH">
												Got a Question
										    </fo:basic-link>
										</fo:block>
									</fo:table-cell>

									<fo:table-cell>
										<fo:block font-size="11pt" font-family="FSAlbert-Light" text-align="right">
											<fo:page-number-citation ref-id="questions-CH" />
										</fo:block>
									</fo:table-cell>
									
								</fo:table-row>


								<!-- ********************************************* -->
								<!--           Booking Conditions Section          -->
								<fo:table-row>

									<fo:table-cell>
										<fo:block text-align="left" margin-left="1mm"
											margin-right="0mm" font-size="10pt" font-family="TMixLight">
											<fo:basic-link internal-destination="terms">
												Booking Conditions
											</fo:basic-link>
										</fo:block>
									</fo:table-cell>

									<fo:table-cell>
										<fo:block font-size="11pt" font-family="FSAlbert-Light" text-align="right">
											<fo:page-number-citation ref-id="terms" />
										</fo:block>
									</fo:table-cell>
									
								</fo:table-row>

								<fo:table-row>

									<fo:table-cell>
										<fo:block>
											&#x00A0; <!-- space -->
										</fo:block>
									</fo:table-cell>

									<fo:table-cell>
										<fo:block>
											&#x00A0; <!-- space -->
										</fo:block>
									</fo:table-cell>

								</fo:table-row>

								<fo:table-row>
									<fo:table-cell display-align="center" number-columns-spanned="2">
										<fo:block font-size="12pt" color="black" font-family="FSAlbert-Light" border-top-style="solid">
											<xsl:text> </xsl:text>
										</fo:block>
									</fo:table-cell>
								</fo:table-row>
										

							</fo:table-body>

						</fo:table>

					</fo:block>

				</fo:block-container>


			</fo:flow>
		</fo:page-sequence>

	</xsl:template>

	<xsl:template match="frontispiece/intro/text/strong">
		<fo:inline font-family="FSAlbert-Bold">    
			<xsl:value-of select="."/>
		</fo:inline>
	</xsl:template> 
	

</xsl:stylesheet>
