<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:saxon="http://saxon.sf.net/" xmlns:xak="http://www.onekin.org/xak"  
xmlns:xmlns="" exclude-result-prefixes="xak">

	<xsl:template match="@xak:module"/>
	
	<xsl:template match="@xak:type"/>
	
	<xsl:template match="@xak:artifact"/>
	
	<xsl:template match="@* | node()">
		<xsl:copy copy-namespaces="no">
			<xsl:apply-templates select="@* | node()"/>
		</xsl:copy> 
	</xsl:template>

</xsl:stylesheet>
