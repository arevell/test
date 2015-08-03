<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" 
	xmlns:ti="http://www.ttsl.com/TourInfo/2010/08/2.4"
	version="2.0">

	<xsl:template match="Text|text|ti:text|ti:Text">
		<fo:block>
				<xsl:apply-templates/>
		</fo:block>
	</xsl:template>

	<xsl:template match="p|div|ti:p|ti:div">
		<fo:block><xsl:apply-templates/></fo:block>
	</xsl:template>
	
	<xsl:template match="br|ti:br">
		<fo:block margin-top="0.8mm"/>
	</xsl:template>
	
	<xsl:template match="b|strong|ti:b|ti:strong">
		<fo:inline font-weight="bold"><xsl:apply-templates/></fo:inline>
	</xsl:template>
	
	<xsl:template match="i|ti:i">
		<fo:inline font-style="italic"><xsl:apply-templates/></fo:inline>
	</xsl:template>
	
	<xsl:template match="ul|ti:ul">
		<fo:list-block provisional-label-separation="2mm"
      provisional-distance-between-starts="4mm">
			<xsl:apply-templates/>
		</fo:list-block>
	</xsl:template>
	
	<xsl:template match="ul/li|ti:ul/ti:li">
		<fo:list-item>
		  <fo:list-item-label end-indent="label-end()"><fo:block>*</fo:block></fo:list-item-label>  
		  <fo:list-item-body start-indent="body-start()"><fo:block><xsl:apply-templates/></fo:block></fo:list-item-body>
		 </fo:list-item>
		 
	</xsl:template>

	
	<!-- ordered list -->
	<xsl:template match="ol|ti:ol">
		<fo:list-block provisional-label-separation="1mm"
      provisional-distance-between-starts="4mm">
			<xsl:apply-templates/>
		</fo:list-block>
	</xsl:template>
	
	<!-- numbered items -->
	<xsl:template match="ol/li|ti:ol/ti:li">
		<fo:list-item>
		  <fo:list-item-label end-indent="label-end()"><fo:block> <xsl:number/>.</fo:block></fo:list-item-label>  
		  <fo:list-item-body start-indent="body-start()"><fo:block><xsl:apply-templates/></fo:block></fo:list-item-body>
		 </fo:list-item>
		 
	</xsl:template>
	
	
	
	<xsl:template match="hr|ti:hr">
		<fo:block text-align-last="justify">
		<fo:leader leader-pattern="rule"/>
		</fo:block>
	</xsl:template>

	<xsl:template match="a">
		<fo:basic-link external-destination="{@href}">
			<xsl:value-of select="."/>
		</fo:basic-link>
	</xsl:template>
	
	<xsl:template match="ti:a">
		<fo:basic-link external-destination="{@href}">
			<fo:inline color="#003366" border-bottom-style="solid" border-bottom-color="#003366">
				<xsl:value-of select="."/>
			</fo:inline>
		</fo:basic-link>
	</xsl:template>

</xsl:stylesheet>