<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"  xmlns:p="http://www.dei.isep.ipp.pt/lprog">
  <xsl:template match="/">
    <html>
      <head>
        <meta charset="utf-8" />
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous" />
        <title>Relatório</title>
        <style>
          .header img {
            max-width: 100%;
          }
          .center {
            display: block;
            margin-left: auto;
            margin-right: auto;
            width: 50%;
          }
          h1 {
            text-align: center;
          }
          h3 {
            text-align: center;
          }
          h4 {
            text-align: center;
          }
      </style>
      </head>
      <body>
        <div class="header">
          <img alt="ISEP/DEI" class="center">
            <xsl:attribute name="src">
              <xsl:value-of select="p:relatório/p:páginaRosto/p:logotipoDEI">
              </xsl:value-of>
            </xsl:attribute>
          </img>
        </div>
        <div class="container">
          <h1>
                Relatório: <xsl:value-of select="p:relatório/p:páginaRosto/p:tema"/>
        </h1>
            <h3>
                <div>
                  Licenciatura em Engenharia Informática
                </div>
                <div>
                <xsl:value-of select="p:relatório/p:páginaRosto/p:disciplina/p:designação"/>
                  
                </div>
                <div>
                  Ano Letivo 2019/2020
                </div>
            </h3>
        <h2>Relatório elaborado por:</h2>
        <table border="1">
          <tr bgcolor="#FF7F00">
            <th style="text-align:center">Nome</th>
            <th style="text-align:center">Número</th>
            <th style="text-align:center">Mail</th>
          </tr>
          <xsl:for-each select="p:relatório/p:páginaRosto/p:autor">
            <tr>
              <td>
                <xsl:value-of select="p:nome" />
              </td>
              <td>
                <xsl:value-of select="p:número" />
              </td>
              <td>
                <xsl:value-of select="p:mail" />
              </td>
            </tr>
          </xsl:for-each>
        </table>
        <hr/>
        <h3>Índice:</h3>
        <xsl:for-each select="p:relatório/*[2]">
          <xsl:for-each select="*">
            <xsl:choose>
              <xsl:when test="name()='p:outrasSecções'">
                <xsl:for-each select="*">
                  <div>
                    <xsl:value-of select="./@tituloSecção"/>
                  </div>
                </xsl:for-each>
              </xsl:when>
              <xsl:otherwise>
                <div>
                  <xsl:value-of select="./@tituloSecção"/>
                </div>
              </xsl:otherwise>
            </xsl:choose>
          </xsl:for-each>
        </xsl:for-each>
        <hr/>
        <h3>
          <xsl:value-of select="p:relatório/p:corpo/p:introdução/@tituloSecção">
          </xsl:value-of>
        </h3>
        <xsl:for-each select="p:relatório/p:corpo/p:introdução/p:parágrafo">
          <a>
            <xsl:value-of select=".">
            </xsl:value-of>
            <br/>
          </a>
        </xsl:for-each>
        <hr/>
        <h4>
          <xsl:value-of select="p:relatório/p:corpo/p:outrasSecções/p:análise/@tituloSecção">
          </xsl:value-of>
        </h4>
        <xsl:for-each select="p:relatório/p:corpo/p:outrasSecções/p:análise/p:parágrafo">
          <a>
            <xsl:value-of select=".">
            </xsl:value-of>
            <br/>
          </a>
        </xsl:for-each>
        <hr/>
        <h4>
          <xsl:value-of select="p:relatório/p:corpo/p:outrasSecções/p:linguagem/@tituloSecção">
          </xsl:value-of>
        </h4>
        <xsl:for-each select="p:relatório/p:corpo/p:outrasSecções/p:linguagem/p:parágrafo">
          <a>
            <xsl:value-of select=".">
            </xsl:value-of>
            <br/>
          </a>
        </xsl:for-each>
        <hr/>
        <h4>
          <xsl:value-of select="p:relatório/p:corpo/p:outrasSecções/p:transformações/@tituloSecção">
          </xsl:value-of>
        </h4>
        <xsl:for-each select="p:relatório/p:corpo/p:outrasSecções/p:transformações/p:parágrafo">
          <a>
            <xsl:value-of select=".">
            </xsl:value-of>
            <br/>
          </a>
        </xsl:for-each>
        <hr/>
        <h3>
          <xsl:value-of select="p:relatório/p:corpo/p:conclusão/@tituloSecção">
          </xsl:value-of>
        </h3>
        <xsl:for-each select="p:relatório/p:corpo/p:conclusão/p:parágrafo">
          <a>
            <xsl:value-of select=".">
            </xsl:value-of>
            <br/>
          </a>
        </xsl:for-each>
        <hr/>
        <h3>
          <xsl:value-of select="p:relatório/p:corpo/p:referências/@tituloSecção">
          </xsl:value-of>
        </h3>
        <xsl:for-each select="p:relatório/p:corpo/p:referências/p:refWeb">
          <p>
            <xsl:attribute name="p:id">
              <xsl:value-of select="@p:idRef">
              </xsl:value-of>
            </xsl:attribute>
            <span style="font-size: small;">
              <font size="2">
                <xsl:value-of select="p:URL"/>
              </font>
              <font size="2">
                <xsl:value-of select="p:descrição"/>,
                <xsl:value-of select="p:consultadoEm"/>).
              </font>
            </span>
          </p>
        </xsl:for-each>
          <hr/>
          <h3>
            <xsl:value-of select="p:relatório/p:anexos/@tituloSecção">
            </xsl:value-of>
          </h3>
          <xsl:for-each select="p:relatório/p:anexos/p:parágrafo">
            <p>
              <strong>
                <xsl:value-of select=".">
                </xsl:value-of>
                <br/>
              </strong>
            </p>
          </xsl:for-each>
        </div>
      </body>
    </html>
  </xsl:template>
</xsl:stylesheet>
