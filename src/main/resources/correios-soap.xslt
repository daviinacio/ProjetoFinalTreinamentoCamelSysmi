<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output omit-xml-declaration="no" indent="yes"/>

    <xsl:template match="/">
        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:cli="https://cliente.bean.master.sigep.bsb.correios.com.br/">
            <soapenv:Body>
               <cli:consultaCEP>
                  <cep><xsl:value-of select="cep"/></cep>
               </cli:consultaCEP>
            </soapenv:Body>
         </soapenv:Envelope>       
    </xsl:template>

</xsl:stylesheet>