<?xml version="1.0"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="text"/>

    <xsl:template match="/">{
        <xsl:apply-templates select="*"/>
    }
    </xsl:template>

    <xsl:template match="*">
        "<xsl:value-of select="name()"/>" : <xsl:call-template name="propriedades"/>
    </xsl:template>

    <xsl:template match="*" mode="Array">
        <xsl:call-template name="propriedades"/>
    </xsl:template>

    <xsl:template name="propriedades">
        <xsl:variable name="elementoFilhoNome" select="name(*[1])"/>
        <xsl:choose>

            <xsl:when test="name(./*)=''">
                <xsl:choose>
                    <xsl:when test="count(@*)>0">{
                        "Atributos" : {
                            <xsl:for-each select="@*">
                                "<xsl:value-of select="name()"/>" : "<xsl:value-of select="."/>",
                            </xsl:for-each>
                            }
                    }
                    </xsl:when>
                    <xsl:otherwise>
                        "<xsl:value-of select="text()"/>"
                    </xsl:otherwise>
                </xsl:choose>
            </xsl:when>
            <xsl:when test="count(*[name()=$elementoFilhoNome]) > 1">{ 
                <xsl:if test="count(@*)>0">
                    "Atributos" : {
                    <xsl:for-each select="@*">
                           "<xsl:value-of select="name()"/>" : "<xsl:value-of select="."/>",
                    </xsl:for-each>
                    },
                </xsl:if>
                "<xsl:value-of select="$elementoFilhoNome"/>" :[
                    <xsl:apply-templates select="*" mode="Array"/>
                ]
            }
            </xsl:when>
            <xsl:otherwise>{  
                <xsl:if test="count(@*)>0">
                    "Atributos" : {
                    <xsl:for-each select="@*">
                           "<xsl:value-of select="name()"/>" : "<xsl:value-of select="."/>",
                    </xsl:for-each>
                    },
                </xsl:if>
                <xsl:apply-templates select="*"/>
            }
            </xsl:otherwise>
        </xsl:choose>
        <xsl:if test="following-sibling::*">,</xsl:if>
    </xsl:template>
</xsl:stylesheet>