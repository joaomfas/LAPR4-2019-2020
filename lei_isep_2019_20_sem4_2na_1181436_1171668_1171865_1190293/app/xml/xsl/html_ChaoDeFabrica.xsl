<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
        <head>
        <style>
            body {
                margin:0;
                margin-bottom:50px;
                overflow:hidden;
            
            }
            .navbar {
                overflow: hidden;
                background-color: #333;
                position: fixed;
                top: 0;
                width: 100%;
                height: auto;
                z-index:1;
                text-align: center;
            }

            .navbar a {
                float: left;
                display: block;
                color: #fff;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
                font-size: 18px;
                font-style:regular;
            }

            .navbar a:hover {
                background: #fff;
                color: black;
            }

            .container {
               padding: 16px;
                top: 0px;
                height:100%;
                position:relative;
                overflow-y: scroll;
            }

            .item{
                height:auto;
                width:auto; 
                position:relative;
                top:120px;
                padding:16px;
                vertical-aling:middle
                margin-top:30px;
                background: #F2F2F2;
              
            }
			
			.custom_table{
				text-align: center;
				vertical-align: top;
				table-layout:auto;
				width: 100%;
			}	
			.custom_table_ficha_prod{
			    border:4px;
				border-color:#70D3F7;
				text-align: center;
				vertical-align: top;
				table-layout:auto;
				width: 100%;
			}
			
			.custom_table_ficha_prod tr {
				vertical-align:top;
			}
			
			.custom_table tr {
				vertical-align:top;
			}
			
		  .custom_li {
			    margin-right:20px;
				text-align:left;
				margin-top:10px
		  }
          .navbar h1{
                height:auto;
                width:auto; 
                vertical-aling:middle
                text-align: center;
                color:#fff
          }

        </style>
        </head>
            <body>
            <div class="navbar">
                        <h1>Chão de Fábrica</h1>
                <div>
                    <xsl:for-each select="ChaoDeFabrica/*">
                        <xsl:variable name="secao">
                            <xsl:value-of select="name(.)"/> 
                        </xsl:variable>
                        <a href="#{$secao}"><xsl:value-of select="name(.)"/></a>
                    </xsl:for-each> 
                 </div>
                </div>

                <div class="container">
                    <xsl:for-each select="ChaoDeFabrica/*">
                        <xsl:variable name="refSecao">
                            <xsl:value-of select="name(.)"/> 
                        </xsl:variable>
                            <div  id="{$refSecao}"  class="item" style="padding-top:120px">
                                <h2><xsl:value-of select="name(.)"/></h2>
                                <xsl:choose>
                                    <xsl:when test="name(.)='LinhasProducao'">
                                        <xsl:for-each select="./*">
                                            <table class="custom_table" border="0" style="color:#0001FF;">
                                                <tr>
                                                    <th>
                                                        <h3 style="color:#7401DF; background=#7456fF; text-align: left; margin-top:16px">
															<xsl:value-of select="name(*/*)"/>->
															<xsl:value-of select="name(*)"/>
                                                            : <xsl:value-of select="*/text()"/>
                                                        </h3>
                                                        <table border="1" class="custom_table">
                                                            <tr bgcolor="#BCA9F5">
                                                                <xsl:for-each select="./*/*[1]/*">
                                                                    <th>
                                                                        <xsl:value-of select="name()"/>
                                                                    </th>
                                                                </xsl:for-each>
                                                            </tr>
                                                            <xsl:for-each select="./*/*">
                                                                <tr>
                                                                    <xsl:for-each select="*">
                                                                        <td>
                                                                            <xsl:value-of select="text()"/>
                                                                        </td>
                                                                    </xsl:for-each>
                                                                </tr>
                                                            </xsl:for-each>
                                                        </table>
                                                    </th>
                                                </tr>
                                            </table>
                                        </xsl:for-each>
                                    </xsl:when>
                                    <xsl:when test="name(.)='ExecucoesOrdemProducao'">
                                        <xsl:for-each select="*">
                                            <table border="0" class="custom_table">
                                                <tr>
                                                    <td>
                                                        <table border="0" class="custom_table">
                                                            <tr bgcolor="#99ffcc">
                                                                <th>
                                                                    <b style="font-size:18px"><xsl:value-of select="name(*[1])"/></b> :  <i><xsl:value-of select="*[1]"/></i>
                                                                </th>
                                                            </tr>
                                                            <tr bgcolor="#99ffcc">
                                                                <th>
                                                                     <b style="font-size:18px"><xsl:value-of select="name(*[2])"/></b> : <i><xsl:value-of select="*[2]"/></i>
                                                                </th>
                                                            </tr>
                                                            <xsl:for-each select="*">
                                                                <xsl:if test="* != 'CodExecucaoOrdemProducao' or * != 'CodOrdemProducao'">
                                                                    <tr style="text-align: left;">
                                                                        <th>
                                                                            <b style="color:#00b3b3;font-size:26px;line-height:50px"><xsl:value-of select="name(*)"/></b>
                                                                        </th>
                                                                    </tr>
                                                                    <tr>
                                                                        <table border="1" class="custom_table">
                                                                            <tr>
                                                                                <xsl:for-each select="*[1]/*">
                                                                                    <th  bgcolor="#00e673">
                                                                                        <xsl:value-of select="name()"/>
                                                                                    </th>
                                                                                </xsl:for-each>
                                                                            </tr>
                                                                            <xsl:for-each select="*">
                                                                                <tr>
                                                                                    <xsl:for-each select="*">
                                                                                        <td>
                                                                                            <xsl:value-of select="text()"/>
                                                                                        </td>
                                                                                    </xsl:for-each>
                                                                                </tr>
                                                                            </xsl:for-each>
                                                                        </table>
                                                                    </tr>
                                                                </xsl:if>
                                                            </xsl:for-each>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </xsl:for-each>
                                    </xsl:when>    
                                    <xsl:otherwise>
                                        <table border="1" class="custom_table">
                                            <tr bgcolor="#58D3F7">
                                                <xsl:for-each select="./*[1]/*">
                                                    <th>
                                                        <xsl:value-of select="name(.)"/>
                                                    </th>
                                                </xsl:for-each>
                                            </tr>
                                            <xsl:for-each select="./*">
                                                <tr>
                                                    <xsl:for-each select="*">
                                                        <xsl:choose>
                                                            <xsl:when test="name(.)='FichaTecnica'">
                                                                <td>
                                                                    <xsl:value-of select="@src"/>
                                                                </td>
                                                            </xsl:when>
                                                            <xsl:when test="name(.)='Encomendas'">
                                                                <td>
                                                                    <ol>
                                                                        <xsl:for-each select="*/*">
                                                                            <li class="custom_li">
                                                                                <b><xsl:value-of select="name()"/>:</b> 
                                                                                <i style="color:#DF013A">
                                                                                    <xsl:value-of select="text()"/>
                                                                                </i>
                                                                            </li>
                                                                        </xsl:for-each>
                                                                    </ol>
                                                                </td>
                                                            </xsl:when>
															<xsl:when test="name(.)='FichaProducao'">
															<td>
																<table  class="custom_table_ficha_prod">
																	<tr bgcolor="#58D3F7">
																		<th>
																			<xsl:value-of select="name(.)"/>
																		</th>
																	</tr>
																	<tr>
																	<td>
																		<ol>
																			<xsl:for-each select="*/*">
																				<li class="custom_li">
																					<b><xsl:value-of select="name(../*)"/></b>
																					<ul>
																					<xsl:for-each select="*">
																						<li>
																							<xsl:value-of select="name()"/>
																							<i style="color:#DF013A"> : <xsl:value-of select="text()"/></i>
																						</li>
																					</xsl:for-each>
																					</ul>
																				</li>
																			</xsl:for-each>
																		</ol>
																	</td>
																	</tr>
																</table>
																</td>
                                                            </xsl:when>
                                                            <xsl:otherwise>
                                                                <td>
                                                                    <xsl:value-of select="text()"/>
                                                                </td>
                                                            </xsl:otherwise>
                                                        </xsl:choose>
                                                    </xsl:for-each>
                                                </tr>
                                            </xsl:for-each>
                                        </table>
                                    </xsl:otherwise>
                                </xsl:choose>
                            </div>
                    </xsl:for-each>
                </div>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
