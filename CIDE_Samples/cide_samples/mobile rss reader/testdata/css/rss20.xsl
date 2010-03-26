<?xml version="1.0" encoding="utf-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    xmlns:wfw="http://wellformedweb.org/CommentAPI/">	
	<xsl:output method="html" encoding="UTF-8" />
<!-- Main template -->
<xsl:template match="/">
     <xsl:element name="html">
      <xsl:value-of select="/rss"/>
     </xsl:element>
</xsl:template>
</xsl:stylesheet>
