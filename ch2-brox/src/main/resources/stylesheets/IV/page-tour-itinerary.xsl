<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.1"
  xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4"
  xmlns:di="http://www.ttsl.com/MarketVariationDepartureInfo/2010/09/1.1">


  <xsl:template name="Itinerary">

    <fo:block keep-together.within-page="always">
      <fo:block xsl:use-attribute-sets="at-itintag" text-align="left"
        padding-before="1.5mm" padding-after="1.5mm" margin-left="0mm"
        margin-bottom="2mm" margin-top="-2mm" margin-right="0mm"
        padding-left="4mm" padding-right="4mm">
        <fo:block font-size="12pt" font-weight="bold"
						xsl:use-attribute-sets="font-sans" margin-left="4mm" margin-right="4mm">
						My Itinerary
		</fo:block>
       
		</fo:block>

      <xsl:for-each
        select="ti:Itinerary/ti:ItinerarySegment[position() &lt; 5]">
        <xsl:call-template name="ItinerarySegment" />
      </xsl:for-each>
    </fo:block>

    <xsl:for-each select="ti:Itinerary/ti:ItinerarySegment[position() &gt;= 5]">
      <xsl:call-template name="ItinerarySegment" />
    </xsl:for-each>

  </xsl:template>

  <xsl:template name="ItinerarySegment">
    <!-- <xsl:template match="ti:ItinerarySegment"> -->
    <fo:block margin-left="1mm" margin-right="1mm"
      margin-bottom="2mm" font-size="9pt" padding-left="2mm"
      padding-right="2mm" xsl:use-attribute-sets="font-sans">

      <fo:block font-weight="bold" xsl:use-attribute-sets="txt-headcol"
        keep-with-next="always">

        <xsl:choose>
          <xsl:when test="@Duration = 1">
            Day<xsl:value-of select="@StartDay" />:
          </xsl:when>
          <xsl:otherwise>
            Days<xsl:value-of select="@StartDay" />-<xsl:value-of select="@StartDay + @Duration - 1" />:
          </xsl:otherwise>
        </xsl:choose>
        <xsl:value-of select="ti:Title" />
      </fo:block>

      <fo:block text-align="justify">
        <xsl:apply-templates select="ti:Text" />
      </fo:block>
    </fo:block>
  </xsl:template>


</xsl:stylesheet>