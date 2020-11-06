<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text"/>
    <xsl:template match="/">
        <xsl:for-each select="./*">
            <xsl:text>****************** <xsl:value-of select ="name(.)"/></xsl:text>
            <xsl:text> ******************</xsl:text>
            <xsl:text>&#xa;&#xa;</xsl:text>
            <xsl:for-each select="*">
                <xsl:text>&#xa;</xsl:text>
                <xsl:text>&#xa;****************** <xsl:value-of select ="name(.)"/></xsl:text>
                <xsl:text> ******************</xsl:text>
                <xsl:text>&#xa;</xsl:text>
                <xsl:for-each select="*">
                    <xsl:text>&#xa;</xsl:text>
                    <xsl:text>------------------------------------------------------------</xsl:text>
                    <xsl:text>&#xa;&#xa;[+]  </xsl:text><xsl:value-of select ="name(.)"/>
                    <xsl:text>&#xa;</xsl:text>
                    <xsl:for-each select="*">
                        <xsl:choose>   
                            <xsl:when test="name(.)='FichaTecnica'">
                                <xsl:text>&#xa; » </xsl:text><xsl:value-of select ="name(.)"/>
                                <xsl:text> : <xsl:value-of select="@src"/></xsl:text>
                            </xsl:when>
                            <xsl:when test="name(.)='FichaProducao'">
                                <xsl:text>&#xa; » </xsl:text><xsl:value-of select ="name(.)"/>
                                <xsl:if test="text()!=''">
                                    <xsl:text> : <xsl:value-of select ="text()"/></xsl:text>
                                </xsl:if>
                                <xsl:for-each select="*">
                                    <xsl:text>&#xa;&#x9;[+]  </xsl:text><xsl:value-of select ="name(.)"/>
                                    <xsl:text>&#xa;</xsl:text>
                                    <xsl:for-each select="*">
                                        <xsl:text>&#xa;&#x9;&#x9;[+]  </xsl:text><xsl:value-of select ="name(.)"/>
                                        <xsl:text>&#xa;</xsl:text>
                                        <xsl:for-each select="*">
                                            <xsl:text>&#xa;&#x9;&#x9;» </xsl:text>
                                            <xsl:value-of select ="name(.)"/>: <xsl:value-of select ="text()"/>
                                        </xsl:for-each>
                                        <xsl:text>&#xa;</xsl:text>
                                    </xsl:for-each>
                                </xsl:for-each>
                            </xsl:when>
                            <xsl:otherwise>
                                <xsl:text>&#xa; » </xsl:text><xsl:value-of select ="name(.)"/>
                                <xsl:if test="text()!=''">
                                    <xsl:text> : <xsl:value-of select ="text()"/></xsl:text>
                                </xsl:if>
                                <xsl:for-each select="*">
                                    <xsl:text>&#xa;&#x9;[+]  </xsl:text>
                                    <xsl:value-of select ="name(.)"/>
                                     <xsl:text>&#xa;</xsl:text>
                                    <xsl:for-each select="*">
                                        <!--xsl:if test="text()!=''"-->
                                            <xsl:text>&#xa;&#x9; » </xsl:text>
                                            <xsl:value-of select ="name(.)"/>: <xsl:value-of select ="text()"/>
                                        <!--/xsl:if-->
                                    </xsl:for-each>
                                     <xsl:text>&#xa;</xsl:text>
                                </xsl:for-each>
                            </xsl:otherwise>
                        </xsl:choose>
                    </xsl:for-each>
                    <xsl:text>&#xa;</xsl:text>
                </xsl:for-each>
            </xsl:for-each>
        </xsl:for-each>
    </xsl:template>
</xsl:stylesheet>
