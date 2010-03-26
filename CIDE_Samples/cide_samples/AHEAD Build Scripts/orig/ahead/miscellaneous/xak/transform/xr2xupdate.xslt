<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:xr="http://www.atarix.org/xmlRefinement" xmlns:xupdate="http://www.xmldb.org/xupdate" exclude-result-prefixes="xr">
	<xsl:output method="xml" version="1.0" encoding="UTF-8" indent="yes" cdata-section-elements="xupdate:cdata"/>
	<xsl:template match="xr:refine">
		<xupdate:modifications version="1.0" xmlns:xupdate="http://www.xmldb.org/xupdate">
			<xsl:call-template name="copiarNamespaces">
				<xsl:with-param name="namespaces" select="namespace::*"/>
			</xsl:call-template>
			<xsl:apply-templates select="*"/>
		</xupdate:modifications>
	</xsl:template>
	<!-- ordered insertion operators   -->
	<xsl:template match="xr:at">
		<xsl:apply-templates select="*">
			<xsl:with-param name="parent" select="@select"/>
		</xsl:apply-templates>
	</xsl:template>
	<xsl:template match="xr:prepend">
		<xsl:param name="parent"/>
		<xupdate:insert-before select="{$parent}/*[position()=1]">
			<xsl:choose>
				<xsl:when test=". = text()">
					<xupdate:text>
						<xsl:apply-templates select="text()"/>
					</xupdate:text>
				</xsl:when>
				<xsl:otherwise>
					<xsl:apply-templates select="*"/>
<!--					<xsl:apply-templates select="* | text()"/> -->
				</xsl:otherwise>
			</xsl:choose>
		</xupdate:insert-before>
	</xsl:template>
	<xsl:template match="xr:append">
		<xsl:param name="parent"/>
		<xsl:if test="xr:copy-of">
			<xsl:apply-templates select="xr:copy-of" mode="variable"/>
		</xsl:if>
		<xupdate:append select="{$parent}">
			<xsl:choose>
				<xsl:when test=". = text()">
					<xupdate:text>
						<xsl:apply-templates select="text()"/>
					</xupdate:text>
				</xsl:when>
				<xsl:otherwise>
					<xsl:apply-templates select="*"/>
<!--					<xsl:apply-templates select="* | text()"/> -->
				</xsl:otherwise>
			</xsl:choose>
		</xupdate:append>
	</xsl:template>
	<xsl:template match="xr:insert-before">
		<xsl:param name="parent"/>
		<xupdate:insert-before select="{$parent}/{@select}">
			<xsl:choose>
				<xsl:when test=". = text()">
					<xupdate:text>
						<xsl:apply-templates select="text()"/>
					</xupdate:text>
				</xsl:when>
				<xsl:otherwise>
					<xsl:apply-templates select="*"/>
<!--					<xsl:apply-templates select="* | text()"/> -->
				</xsl:otherwise>
			</xsl:choose>
		</xupdate:insert-before>
	</xsl:template>
	<xsl:template match="xr:insert-after">
		<xsl:param name="parent"/>
		<xupdate:insert-after select="{$parent}/{@select}">
			<xsl:choose>
				<xsl:when test=". = text()">
					<xupdate:text>
						<xsl:apply-templates select="text()"/>
					</xupdate:text>
				</xsl:when>
				<xsl:otherwise>
					<xsl:apply-templates select="*"/>
<!--					<xsl:apply-templates select="* | text()"/> -->
				</xsl:otherwise>
			</xsl:choose>
		</xupdate:insert-after>
	</xsl:template>
	<!-- delete operator -->
	<xsl:template match="xr:delete">
		<xsl:param name="parent"/>
		<xupdate:remove select="{$parent}/{@select}"/>
	</xsl:template>
	<!-- override operator 
	el elemento a sobreescribir y el elemento a añadir tienen que tener el mismo nombre
-->
	<xsl:template match="xr:override">
		<xsl:param name="parent"/>
		<!-- El xupdate:update no permite insertar elementos o atributos 
     Solo sobrescribe texto -->
		<xsl:choose>
			<xsl:when test=". = text()">
				<xupdate:update select="{$parent}/{@select}">
					<xsl:apply-templates select="text()"/>
				</xupdate:update>
			</xsl:when>
			<xsl:otherwise>
				<xupdate:insert-after select="{$parent}/{@select}">
					<xsl:apply-templates select="*"/>
				</xupdate:insert-after>
				<xupdate:remove select="{$parent}/{@select}[1]"/>
			</xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	<xsl:template match="xr:copy-of" mode="variable">
		<xupdate:variable name="var" select="{@select}"/>
	</xsl:template>
	<xsl:template match="xr:copy-of">
		<xupdate:value-of select="$var"/>
	</xsl:template>
	<xsl:template match="xr:element">
		<xupdate:element name="{@name}">
			<xsl:apply-templates/>
		</xupdate:element>
	</xsl:template>
	<xsl:template match="xr:attribute">
		<xupdate:attribute name="{@name}">
			<xsl:apply-templates/>
		</xupdate:attribute>
	</xsl:template>
	<xsl:template match="xr:cdata">
		<xupdate:cdata>
			<xsl:apply-templates select="text()"/>
		</xupdate:cdata>
	</xsl:template>
	<xsl:template name="copiarNamespaces">
		<xsl:param name="namespaces"/>
		<xsl:for-each select="$namespaces">
			<xsl:namespace name="{name()}" >
				<xsl:value-of select="."/>
			</xsl:namespace>
		</xsl:for-each>
	</xsl:template>
	<xsl:template match="*">
		<xsl:copy>
			<xsl:apply-templates select="* | @* | text()"/>
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
