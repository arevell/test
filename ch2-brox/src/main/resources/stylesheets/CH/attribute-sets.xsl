<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

	<!-- only color and font family here. size, style etc to go in main templates -->

	<!-- dark green band -->
	<xsl:attribute-set name="at-tourhead">
		<xsl:attribute name="background-color">#006666</xsl:attribute>
		<xsl:attribute name="color">white</xsl:attribute>
	</xsl:attribute-set>

	<!-- light green band -->
	<xsl:attribute-set name="at-tourtag">
		<xsl:attribute name="background-color">#BDCAB6</xsl:attribute>
		<xsl:attribute name="color">black</xsl:attribute>
	</xsl:attribute-set>
	
	<!-- white band -->
	<xsl:attribute-set name="at-plain">
		<xsl:attribute name="background-color">white</xsl:attribute>
		<xsl:attribute name="color">black</xsl:attribute>

	</xsl:attribute-set>
	
	<!-- lght grey bottom border -->
	<xsl:attribute-set name="contents-row">
		<xsl:attribute name="border-bottom-style">solid</xsl:attribute>
		<xsl:attribute name="border-bottom-width">0.1mm</xsl:attribute>
		
		<xsl:attribute name="border-color">#CCCCCC</xsl:attribute>
		<xsl:attribute name="margin-left">0mm</xsl:attribute>
		<xsl:attribute name="margin-right">0mm</xsl:attribute>
		<xsl:attribute name="padding-right">0mm</xsl:attribute>
	</xsl:attribute-set>

	<!-- light green band 2 -->
	<xsl:attribute-set name="at-itintag">
		<xsl:attribute name="background-color">#BDCAB6</xsl:attribute>
		<xsl:attribute name="color">#006666</xsl:attribute>
	</xsl:attribute-set>

	<!-- fonts -->
	<xsl:attribute-set name="font-serif">
		<xsl:attribute name="font-family">TrajanPro-Regular</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="font-sans">
		<xsl:attribute name="font-family">Helvetica</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="font-curly">
		<xsl:attribute name="font-family">Times</xsl:attribute>
		<xsl:attribute name="font-style">italic</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="txt-headcol">
		<xsl:attribute name="color">#006666</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="font-albert-light">
		<xsl:attribute name="font-family">FSAlbert-Light</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="font-veneer-clean">
		<xsl:attribute name="font-family">Veneer Clean</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name="font-fsalbert-bold">
		<xsl:attribute name="font-family">FSAlbert-Bold</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name ="font-tradegothiclt-pro">
		<xsl:attribute name="font-family">Trade Gothic LT Pro</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name ="font-futurastd-bold">
		<xsl:attribute name="font-family">Futura Std Book</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name ="font-futurastd-extrabold">
		<xsl:attribute name="font-family">Futura Std ExtraBold</xsl:attribute>
	</xsl:attribute-set>

	<xsl:attribute-set name ="font-futurastd-light">
		<xsl:attribute name="font-family">Futura Light</xsl:attribute>
	</xsl:attribute-set>

</xsl:stylesheet>