<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:wfw="http://wellformedweb.org/CommentAPI/">	
	<xsl:output method="text" encoding="UTF-8" />
<!-- Main template -->
<xsl:template match="/">
<xsl:for-each select="/properties/node/map/node/map/entry">
	<xsl:value-of select="@key"/>=<xsl:value-of select="."/><xsl:text>
</xsl:text>
</xsl:for-each>
<xsl:for-each select="/properties/node/map/node/map/node/map/entry">
	<xsl:value-of select="@key"/>=<xsl:value-of select="."/><xsl:text>
</xsl:text>
</xsl:for-each>
<xsl:for-each select="/properties/node/map/entry">
	<xsl:value-of select="@key"/>=<xsl:value-of select="."/><xsl:text>
</xsl:text>
</xsl:for-each>
</xsl:template>
</xsl:stylesheet>
