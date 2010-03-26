<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xak="http://www.atarix.org/xak" xmlns:xr="http://www.atarix.org/xmlRefinement" xmlns:xupdate="http://www.xmldb.org/xupdate" exclude-result-prefixes="xak">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes"   cdata-section-elements="xr:cdata"/>
	<xsl:template match="xak:refines">
		<xr:refine xmlns:xr="http://www.atarix.org/xmlRefinement" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xak="http://www.atarix.org/xak">
			<xsl:attribute name="name"><xsl:value-of select="@xak:artifact"/></xsl:attribute>
			<xsl:attribute name="version">2</xsl:attribute>
			<xsl:apply-templates select="*"/>
		</xr:refine>
	</xsl:template>
	<!-- module extension -->
	<xsl:template match="xak:extends[xak:super]">
		<!-- check if super is the same-->
			<xr:at>
				<xsl:attribute name="select">//*[@*[local-name()='module']='<xsl:value-of select="@xak:module"/>']</xsl:attribute>
				<xsl:comment>is an extension</xsl:comment>
				<xsl:if test="xak:super/preceding-sibling::*"> 
					<xr:prepend>
							<xsl:apply-templates select="xak:super/preceding-sibling::* | xak:super/preceding-sibling::text()"/>
					</xr:prepend>
				</xsl:if>
				<xsl:if test="xak:super/following-sibling::*">
					<xr:append>
						<xsl:apply-templates select="xak:super/following-sibling::*| xak:super/following-sibling::text()"/>
					</xr:append>
				</xsl:if>
			</xr:at>
	</xsl:template>
	<!-- module overriding -->
	<xsl:template match="xak:extends[not(xak:super)]">
		<xr:at select="/">
			<xr:delete>
				<xsl:attribute name="select">.//*[@module='<xsl:value-of select="@xak:module"/>']/*</xsl:attribute>
			</xr:delete>
		</xr:at>
		<xr:at>
			<xsl:attribute name="select">.//*[@module='<xsl:value-of select="@xak:module"/>']</xsl:attribute>
			<xsl:comment>is an overriding</xsl:comment>
			<xr:append>
				<xsl:apply-templates select="* | text()"/>
			</xr:append>
		</xr:at>
	</xsl:template>
	<xsl:template match="xak:super">
		<!--		<xsl:comment>do nothing</xsl:comment> -->
		<!-- copiar el valor del modulo -->
	</xsl:template>
	<xsl:template match="xak:cdata">
		<xr:cdata>
			<xsl:apply-templates select="text()"/>
		</xr:cdata>
	</xsl:template>
	<xsl:template match="*">
		<xsl:copy>
			<xsl:apply-templates select="* | @* | text() | node()"/>
		</xsl:copy>
	</xsl:template>
	<xsl:template match="@*">
		<xsl:copy>
			<xsl:value-of select="normalize-space(.)"/>
		</xsl:copy>
	</xsl:template>
	<xsl:template match="text()">
			<xsl:value-of select="normalize-space(.)"/>
	</xsl:template>
</xsl:stylesheet>
