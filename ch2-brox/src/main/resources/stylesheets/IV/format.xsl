<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:fo="http://www.w3.org/1999/XSL/Format" version="2.0">

	<!-- formating templates for dates etc -->
	<xsl:template name="format-date">

		<xsl:param name="date" />

		<xsl:variable name="yyyy" select="substring-before($date, '-')" />
		<xsl:variable name="mm-dd-time" select="substring-after($date, '-')" />
		<xsl:variable name="mm" select="substring-before($mm-dd-time, '-')"/>
		<xsl:variable name="dd-time" select="substring-after($mm-dd-time, '-')"/>
		<xsl:variable name="dd">
			<xsl:choose>
				<xsl:when test="contains($dd-time, 'T') or contains($dd-time, 'Z') or contains($dd-time, '+')">
					<xsl:value-of select="substring-before(translate($dd-time, 'TZ+', '---'), '-')" />
				</xsl:when>
				<xsl:otherwise>
					<xsl:value-of select="$dd-time" />
				</xsl:otherwise>
			</xsl:choose>
		</xsl:variable>
		<xsl:variable name="mmm">
			<xsl:choose>
				<xsl:when test="$mm = '01'">Jan</xsl:when>
				<xsl:when test="$mm = '02'">Feb</xsl:when>
				<xsl:when test="$mm = '03'">Mar</xsl:when>
				<xsl:when test="$mm = '04'">Apr</xsl:when>
				<xsl:when test="$mm = '05'">May</xsl:when>
				<xsl:when test="$mm = '06'">Jun</xsl:when>
				<xsl:when test="$mm = '07'">Jul</xsl:when>
				<xsl:when test="$mm = '08'">Aug</xsl:when>
				<xsl:when test="$mm = '09'">Sep</xsl:when>
				<xsl:when test="$mm = '10'">Oct</xsl:when>
				<xsl:when test="$mm = '11'">Nov</xsl:when>
				<xsl:when test="$mm = '12'">Dec</xsl:when>
			</xsl:choose>
		</xsl:variable>

		<xsl:value-of select="concat($dd, ' ', $mmm, ' ', $yyyy)"/>

	</xsl:template>
	
	<xsl:template name="format-price">
		<xsl:param name="amount"/>
		<xsl:param name="symbol" select="'£'"/>
		
		<xsl:value-of select="$symbol" /><xsl:value-of select='format-number($amount, "#.00")' />
	</xsl:template>

</xsl:stylesheet>